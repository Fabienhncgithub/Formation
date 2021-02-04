/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.ControllerInterface.facade;
import static controller.ControllerInterface.sc;
import model.Formation;
import model.Inscription;
import model.Role;
import model.Stagiaire;
import model.Statut;
import model.User;
import view.VueAcceuil;
import view.VueFormation;
import view.VueSession;
import view.VueStagiaire;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import static controller.ControllerInterface.*;

class ControllerStagiaire {

    public ControllerStagiaire() {
    }

    public void registerUser(VueAcceuil vueAcceuil) {
        User user = new Stagiaire();
        sc.nextLine();
        String nom = null;
        do {
            vueAcceuil.newUserNom();
            nom = sc.nextLine();
        } while (nom == null || nom.trim().isEmpty());
        user.setNom(nom.trim());

        String prenom = null;
        do {
            vueAcceuil.newUserPrenom();
            prenom = sc.nextLine();
        } while (prenom == null || prenom.trim().isEmpty());
        user.setPrenom(prenom.trim());

        String adresse = null;
        do {
            vueAcceuil.newUseradresse();
            adresse = sc.nextLine();
        } while (adresse == null || adresse.trim().isEmpty());
        user.setAdresse(adresse.trim());

        String email = null;
        do {
            vueAcceuil.newUseremail();
            email = sc.nextLine();
        } while (!email.matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") || email == null || email.trim().isEmpty() || facade.getCentre().getUserByEmail(email) != null);
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
            controller.checkInt();
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

        int menuchoice = 5;
        switch (controller.checkMenuChoice(menuchoice)) {

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
                sc.nextLine();
                controllerAcceuil.firstMenu();
                break;
        }
    }

    public void modificationUser(Stagiaire user) {
        VueAcceuil VueAcceuil = new VueAcceuil();
        User usr = null;
        int id = user.getIdUser();

        sc.nextLine();
        String nom = null;
        do {
            vueAcceuil.newUserNom();
            nom = sc.nextLine();
        } while (nom == null || nom.trim().isEmpty());
        user.setNom(nom.trim());

        String prenom = null;
        do {
            vueAcceuil.newUserPrenom();
            prenom = sc.nextLine();
        } while (prenom == null || prenom.trim().isEmpty());
        user.setPrenom(prenom.trim());

        String adresse = null;
        do {
            vueAcceuil.newUseradresse();
            adresse = sc.nextLine();
        } while (adresse == null || adresse.trim().isEmpty());
        user.setAdresse(adresse.trim());

        boolean sameId = false;
        String email = null;
        do {

            do {
                vueAcceuil.newUseremail();
                email = sc.nextLine();

            } while (email == null || email.trim().isEmpty());

            if (facade.getCentre().getUserByEmail(email) != null) {
                usr = facade.getCentre().getUserByEmail(email);
                if (usr.getIdUser() == id) {
                    sameId = true;
                }
            } else {
                sameId = true;
            }
        } while (!email.matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") || !sameId);
        user.setEmail(email.trim());

        vueAcceuil.newUserPassword();
        String password = sc.next();
        String hasedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hasedPassword);
        user.modificationUser(id, nom, prenom, adresse, email, hasedPassword);
        loginStagiaire(user);
    }

    public void SelectFormation(Stagiaire user) {
        VueFormation vueFormation = new VueFormation();
        VueSession vueSession = new VueSession();
        controller.getAllFormation(user);
        int selFormation;
        Formation formation = null;
        do {
            vueFormation.inputFormationId();
            sc.nextLine();
            controller.checkInt();
            selFormation = sc.nextInt();

        } while (facade.getCentre().getFormationbyId(selFormation) == null);
        formation = facade.getCentre().getFormationbyId(selFormation);

        if (formation.listeSessionbyFormation().isEmpty()) {
            vueSession.zeroSession();
        } else {
            controller.getAllSessionByFormation(user, formation);
            int selSession = 0;
            do {
                vueSession.inputSessionId();
                while (!sc.hasNextInt()) {
                    vueAcceuil.errorInput();
                    sc.nextLine();
                }
                selSession = sc.nextInt();
            } while (facade.getCentre().getSessionbyId(selSession) == null);
//vérifie si place disponible pour cette formation
         //   if (formation.getParticipantMax() > facade.getCentre().getSessionbyId(selSession).getListeInscriptionbySession().size()) {

                if (!user.registerUserToSession(selSession)) {
                    vueStagiaire.erreurDoubleInscription();
                } else {
                    vueStagiaire.StatutInscription(user, facade.getCentre().getSessionbyId(selSession));
                }
         //   } else {
           //     VueStagiaire.nbrPlaceMax();
            //}
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

        int menuchoice = 2;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                int inscriptionId;
                do {
                    vueSession.inputSessionId();
                    controller.checkInt();
                    inscriptionId = sc.nextInt();
                } while (facade.getCentre().getInscriptionbyId(inscriptionId) == null);
                
                if (facade.getCentre().validationPaiement(inscriptionId, stagiaire)) {
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
