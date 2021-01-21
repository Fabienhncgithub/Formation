/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Admin;
import model.Formation;
import model.Stagiaire;
import model.User;
import view.VueAcceuil;
import view.VueAdmin;
import view.VueFormation;
import java.util.List;
import model.DAO.AbstractDaoFactory;
import model.DAO.Mysql.MySqlDaoFactory;
import org.mindrot.jbcrypt.BCrypt;
import view.VueStagiaire;

/**
 *
 * @author Fabien
 */
public class ControllerAcceuil implements ControllerInterface {

    private VueAdmin vueAdmin = new VueAdmin();
    private VueAcceuil vueAcceuil = new VueAcceuil();
    private VueStagiaire vueStagiaire = new VueStagiaire();
    private VueFormation vueFormation = new VueFormation();
    private User usr = null;

    public ControllerAcceuil(VueAdmin vueAdmin, VueAcceuil vueAcceuil, VueFormation vueFormation) {
        this.vueAdmin = vueAdmin;
        this.vueAcceuil = vueAcceuil;
        this.vueFormation = vueFormation;
    }

    public ControllerAcceuil() {
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public void firstMenu() {
        AbstractDaoFactory.setFactory(MySqlDaoFactory.getInstance());
        cleanDB();

        VueAcceuil vueAcceuil = new VueAcceuil();
        vueAcceuil.choices();

        controller.checkInt();

        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 4) {
            vueAcceuil.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                searchCatalogue();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                controllerStagiaire.registerUser(vueAcceuil);
                break;
            case 4:
                System.exit(0);
                break;
        }
    }

    public void loginUser() {
        User user = null;
        String email;
        String password;
        do {
            vueAcceuil.login();
            email = sc.next();
            sc.nextLine();
//            if (Integer.parseInt(email) == 0) {
//                firstMenu();
//            }
        } while (email.isEmpty() || email == null || facade.getCentre().getUserByEmail(email) == null);

        do {
            vueAcceuil.newUserPassword();
            password = sc.next();
            sc.nextLine();
            if (Integer.parseInt(password) == 0) {
                firstMenu();
            }
        } while (password.isEmpty() || password == null || !BCrypt.checkpw(password, facade.getCentre().getUserByEmail(email).getPassword()));

        
        controllerAcceuil.setUsr(User.login(email));

        if (controllerAcceuil.getUsr() instanceof Stagiaire) {
            controllerStagiaire.loginStagiaire((Stagiaire) controllerAcceuil.getUsr());
        } else if (controllerAcceuil.getUsr() instanceof Admin) {
            controllerAdmin.adminChoices(controllerAcceuil.getUsr());
        } else {
            vueAcceuil.errorlogin();
            loginUser();
        }
    }

    public void searchCatalogue() {
        vueFormation.cataloguesChoices();
        controller.checkInt();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 3) {
            vueFormation.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                List<Formation> formationsList = facade.getCentre().getAllFormation();
                vueFormation.resultsListFormation(formationsList);
                break;
            case 2:
                searchFormation(vueFormation);
                break;
            case 3:
                firstMenu();
                break;

        }
        firstMenu();
    }

    public void searchFormation(VueFormation vueFormation) {
        vueFormation.inputFormationNom();
        String search = sc.next();
        List<Formation> formationsList = facade.getCentre().searchFormation(search);
        do {
            vueFormation.errorSearchFormation();
            vueFormation.inputFormationNom();
            search = sc.next();
        } while (formationsList == null);
        formationsList = facade.getCentre().searchFormation(search.toUpperCase());
        vueFormation.resultsListFormation(formationsList);
        firstMenu();
    }

    public void cleanDB() {
        facade.getCentre().cleanDb();

    }

}
