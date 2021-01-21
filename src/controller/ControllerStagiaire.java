/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.cj.jdbc.interceptors.SessionAssociationInterceptor;
import static controller.ControllerInterface.facade;
import static controller.ControllerInterface.sc;
import model.Formation;
import model.Inscription;
import model.Role;
import model.Session;
import model.Stagiaire;
import model.Statut;
import model.User;
import view.VueAcceuil;
import view.VueAdmin;
import view.VueFormation;
import view.VueSession;
import view.VueStagiaire;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Fabien
 */
class ControllerStagiaire implements ControllerInterface {

    private VueStagiaire vueStagiaire = new VueStagiaire();
    private VueAcceuil vueAcceuil = new VueAcceuil();

    public ControllerStagiaire(VueStagiaire vueStagiaire, VueAcceuil vueAcceuil) {
        this.vueStagiaire = vueStagiaire;
        this.vueAcceuil = vueAcceuil;
    }

    public ControllerStagiaire() {
    }

    public void registerUser(VueAcceuil vueAcceuil) {
        User user = new Stagiaire();

        vueAcceuil.newUserNom();
        sc.nextLine();
        String nom = sc.nextLine();
        user.setNom(nom.trim());
        vueAcceuil.newUserPrenom();
        String prenom = sc.nextLine();
        user.setPrenom(prenom.trim());
        vueAcceuil.newUseradresse();
        String adresse = sc.nextLine();
        user.setAdresse(adresse.trim());
        
        String email;
        do {
            vueAcceuil.newUseremail();
            email = sc.nextLine();
        } while (facade.getCentre().getUserByEmail(email) != null);
        
        user.setEmail(email.trim());
        vueAcceuil.newUserPassword();
        String password = sc.next();
        String hasedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hasedPassword);
        user.setRole(new Role(1, "stagiaire"));
        vueAcceuil.newUserStatut();
        List<Statut> listStatut = facade.getCentre().getAllStatut();
        int idStatut = 0;
        do {
            vueAcceuil.resultListStatut(listStatut);
            idStatut = sc.nextInt();
            vueAcceuil.newUserStatut();
        } while (facade.getCentre().getStatutById(idStatut) == null);
        user.setStatut(new Statut(idStatut, listStatut.get(idStatut).getNomStatut(), listStatut.get(idStatut).getDiscount()));
        user.registerUser();
        controllerAcceuil.firstMenu();
    }

    void loginStagiaire(Stagiaire user) {
        VueStagiaire vueStagiaire = new VueStagiaire();
        vueStagiaire.stagiaireChoices(user);
        controller.checkInt();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 5) {
            vueAcceuil.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                modificationUser(user);
                break;
            case 2:
                SelectFormation(user);
                break;
            case 3:
                deleteInscription(user);
                break;
            case 4:
                inscriptionsByUser(user);
                break;
            case 5:
                controllerAcceuil.firstMenu();
                break;
        }
    }

    public void modificationUser(Stagiaire user) {
        VueAcceuil VueAcceuil = new VueAcceuil();
        int id = user.getIdUser();
        VueAcceuil.newUserNom();

        while (!sc.hasNext("[A-Za-z]*")) {
            vueAcceuil.errorInputString();
            sc.nextLine();
        }
        String nom = sc.next();
        VueAcceuil.newUserPrenom();
        while (!sc.hasNext("[A-Za-z]*")) {
            vueAcceuil.errorInputString();
            sc.nextLine();
        }
        String prenom = sc.nextLine();
        VueAcceuil.newUseradresse();
        String adresse = sc.nextLine();
        VueAcceuil.newUseremail();
        String email = sc.nextLine();
        VueAcceuil.newUserPassword();
        String password = sc.nextLine();
        String hasedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.modificationUser(id, nom, prenom, adresse, email, hasedPassword);
        loginStagiaire(user);
    }

    public void SelectFormation(Stagiaire user) {
        VueFormation vueFormation = new VueFormation();
        VueSession vueSession = new VueSession();
        vueFormation.resultsListFormation(facade.getCentre().getAllFormation());
        int selFormation;
        Formation formation = null;
        do {
            vueFormation.inputFormationId();
            sc.nextLine();
            controller.checkInt();
            selFormation = sc.nextInt();
            formation = facade.getCentre().getFormationbyId(selFormation);
        } while (formation == null);

        if (formation.listeSessionbyFormation().isEmpty()) {
            vueSession.zeroSession();
            SelectFormation(user);
        } else {
            vueSession.resultsListSession(formation.listeSessionbyFormation());
            int selSession = 0;
            do {
                vueSession.inputSessionId();

                while (!sc.hasNextInt()) {
                    vueAcceuil.errorInput();
                    sc.nextLine();
                }
                selSession = sc.nextInt();
            } while (facade.getCentre().getSessionbyId(selSession) == null);
            //if(formation.getParticpantMax>session.getListInscription.size()){}
            // formation.getParticipantMax()>
            if (!user.registerUserToSession(selSession)) {
                vueStagiaire.erreurDoubleInscription();
            } else {
                vueStagiaire.StatutInscription(user, facade.getCentre().getSessionbyId(selSession));
            }
        }

        loginStagiaire(user);
    }

    public void inscriptions(Stagiaire stagiaire) {
        List<Inscription> listInscription = stagiaire.getListeInscriptionbyUser();
        if (listInscription.isEmpty()) {
            vueStagiaire.zeroInscription();
            loginStagiaire(stagiaire);
        } else {
            vueStagiaire.resultsListInscription(listInscription);
        }
    }

    public void inscriptionsByUser(Stagiaire stagiaire) {
        inscriptions(stagiaire);
        vueStagiaire.validationPaiement();

        controller.checkInt();

        int choice = sc.nextInt();
        while (choice < 1 || choice > 2) {
            vueAcceuil.error();
            choice = sc.nextInt();
        }
        switch (choice) {
            case 1:
                int sessionId;
                do {
                    vueSession.inputSessionId();
                    sessionId = sc.nextInt();
                } while (facade.getCentre().getSessionbyId(sessionId) == null);
                if (facade.getCentre().validationPaiement(sessionId, stagiaire)) {
                    vueAcceuil.success();
                    loginStagiaire(stagiaire);
                } else {
                    vueAcceuil.errorDoubleNotification();
                    loginStagiaire(stagiaire);
                }
                break;
            case 2:
                loginStagiaire(stagiaire);
                break;

        }

    }

    public void deleteInscription(Stagiaire stagiaire) {
        inscriptions(stagiaire);
        vueSession.inputSessionId();
        vueAcceuil.exit();
        int idSession = sc.nextInt();
        if (idSession == 0) {
            loginStagiaire(stagiaire);
        } else if (stagiaire.deletelInscription(idSession)) {
            vueStagiaire.formationAnule();
            loginStagiaire(stagiaire);
        } else {
            vueStagiaire.formationNotAnule();
            loginStagiaire(stagiaire);
        }
    }

}
