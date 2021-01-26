package controller;

import model.Admin;
import model.Formation;
import model.Stagiaire;
import model.User;
import view.VueAcceuil;
import view.VueFormation;
import java.util.List;
import model.DAO.AbstractDaoFactory;
import model.DAO.Mysql.MySqlDaoFactory;
import model.Formateur;
import org.mindrot.jbcrypt.BCrypt;
import static controller.ControllerInterface.*;

public class ControllerAcceuil {

    private User usr = null;

    public ControllerAcceuil() {
    }

    public void firstMenu() {
        AbstractDaoFactory.setFactory(MySqlDaoFactory.getInstance());
        cleanDB();
        vueAcceuil.choices();

        int menuchoice = 4;
        switch (controller.checkMenuChoice(menuchoice)) {
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
        } while (email.isEmpty() || email == null || facade.getCentre().getUserByEmail(email) == null);
        do {
            vueAcceuil.newUserPassword();
            password = sc.next();
            sc.nextLine();
        } while (password.isEmpty() || password == null || !BCrypt.checkpw(password, facade.getCentre().getUserByEmail(email).getPassword()));

        controllerAcceuil.setUsr(User.login(email));

        if (controllerAcceuil.getUsr() instanceof Stagiaire) {
            controllerStagiaire.loginStagiaire((Stagiaire) controllerAcceuil.getUsr());
        } else if (controllerAcceuil.getUsr() instanceof Admin) {
            controllerAdmin.adminChoices(controllerAcceuil.getUsr());
        } else if (controllerAcceuil.getUsr() instanceof Formateur) {
            controllerFormateur.formateurChoices(controllerAcceuil.getUsr());
        } else {
            vueAcceuil.errorlogin();
            loginUser();
        }
    }

    public void searchCatalogue() {
        vueFormation.cataloguesChoices();
        
        int menuchoice = 3;
        switch (controller.checkMenuChoice(menuchoice)) {
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
    //Nettoyer la base de données (suppression des données relatives aux sessions > 365 jours)

    public void cleanDB() {
        facade.getCentre().cleanDb();

    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

}
