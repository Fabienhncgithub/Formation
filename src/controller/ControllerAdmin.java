/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Formateur;
import model.Formation;
import model.Role;
import model.User;
import view.VueAcceuil;
import view.VueAdmin;
import view.VueFormation;
import com.sun.deploy.uitoolkit.impl.fx.ui.resources.ResourceManager;
import java.util.ArrayList;
import java.util.List;
import model.Session;
import view.VueSession;

/**
 *
 * @author Fabien
 */
class ControllerAdmin implements ControllerInterface {

    private VueAdmin vueAdmin = new VueAdmin();
    private VueAcceuil vueAcceuil = new VueAcceuil();
    private VueFormation vueFormation = new VueFormation();
    private VueSession vueSession = new VueSession();

    public ControllerAdmin(VueAdmin vueAdmin, VueAcceuil vueAcceuil, VueFormation vueFormation, VueSession vueSession) {
        this.vueAdmin = vueAdmin;
        this.vueAcceuil = vueAcceuil;
        this.vueFormation = vueFormation;
        this.vueSession = this.vueSession;
    }

    ControllerAdmin() {
    }

    void adminChoices(User user) {
        vueAdmin.choices(user);
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 15) {
            vueAdmin.error(user);
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:
                crudFormation(user);
                break;
            case 7:

                break;
            case 8:
                crudFormateur(user);
                break;
            case 9:

                break;
            case 10:
                //           verificationNbrInscriptions();
                break;
            case 11:

                break;
            case 12:

                break;
            case 13:

                break;
            case 14:

                break;
            case 15:

                break;
            case 16:
                controllerAcceuil.firstMenu();
                break;
        }
    }

    public void crudFormation(User user) {
        VueAdmin menuAdmin = new VueAdmin();
        vueFormation.choicesCrud();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 4) {
            vueFormation.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                getAllFormation(user);
                break;
            case 2:
                createFormation(user);
                break;
            case 3:
                selectFormation(user);
                break;
            case 4:
                menuAdmin.choices(user);
                break;
        }
    }

    public void crudSelectedFormation(User user, Formation formation) {
        VueAdmin menuAdmin = new VueAdmin();
        vueFormation.crudSelectedFormation();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 5) {
            vueFormation.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                updateFormation(user, formation);
                break;
            case 2:
                deleteFormation(user, formation);
                break;
            case 3:
                CrudSession(user, formation);
                break;
            case 4:
            //    createSession(user, formation);
                break;
            case 5:
                menuAdmin.choices(user);
                break;

        }
    }

    public void createFormation(User user) {
        Formation formation = new Formation();
        formation = inputFormation(user, formation);
        facade.getCentre().createNewFormation(formation);
        vueAcceuil.success();
        crudFormation(user);
    }

    public Formation inputFormation(User user, Formation formation) {
        vueFormation.inputFormationNom();
        String nomFormation = sc.next();
        formation.setNomFormation(nomFormation);
        int prix = 0;
        do {
            vueFormation.inputFormationPrix();
            prix = sc.nextInt();
        } while (prix < 0);
        formation.setPrix(prix);
        vueFormation.inputFormationDuree();
        int duree = sc.nextInt();
        formation.setDuree(duree);
        int participantMin = 0;
        do {
            vueFormation.inputFormationParticipantMin();
            participantMin = sc.nextInt();
        } while (participantMin < 0);
        formation.setParticipantMin(participantMin);
        int participantMax = 0;
        do {
            vueFormation.inputFormationParticipantMax();
            participantMax = sc.nextInt();
        } while (participantMin > participantMax);
        formation.setParticipantMax(participantMax);
        return formation;
    }

    public void getAllFormation(User user) {
        vueFormation.resultsListFormation(facade.getCentre().getAllFormation());
        crudFormation(user);
    }

    public void updateFormation(User user, Formation formation) {
        formation = inputFormation(user, formation);
        facade.getCentre().updateFormation(formation);

    }

    public void selectFormation(User user) {
        vueFormation.resultsListFormation(facade.getCentre().getAllFormation());
        vueFormation.inputFormationId();
        int idFormation = sc.nextInt();
        crudSelectedFormation(user, facade.getCentre().getFormationbyId(idFormation));
    }

    public void deleteFormation(User user, Formation formation) {
        user.deleteFormation(formation);
        selectFormateur(user);
    }

    public void crudFormateur(User user) {
        vueAdmin.choicesFormateurCrud();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 4) {
            vueAdmin.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                getAllFormateur(user);
                crudFormateur(user);
                break;
            case 2:
                createFormateur(user);
                break;
            case 3:
                selectFormateur(user);
                break;
            case 4:
                vueAdmin.choices(user);
                break;
        }

    }

    public void createFormateur(User user) {
        Formateur formateur = new Formateur();
        vueAcceuil.newUserNom();
        String nom = sc.next();
        formateur.setNom(nom);
        vueAcceuil.newUserPrenom();
        String prenom = sc.next();
        formateur.setPrenom(prenom);
        vueAcceuil.newUseradresse();
        String adresse = sc.next();
        formateur.setAdresse(adresse);
        vueAcceuil.newUseremail();
        String email = sc.next();
        formateur.setEmail(email);
        vueAcceuil.newUserPassword();
        String password = sc.next();
        formateur.setPassword(password);
        formateur.setRole(new Role(3, "formateur"));
         formateur.registerFormateur();
       
        
        crudFormateur(user);
    }

    public void getAllFormateur(User user) {
        vueAdmin.resultsListFormateur(facade.getCentre().getAllFormateur());

    }

    private void selectFormateur(User user) {
        getAllFormateur(user);
        vueAdmin.choicesFormateurCrudModif();

        vueAdmin.inputFormateurId();
        int idFormateur = sc.nextInt();
        Formateur formateur = facade.getCentre().getFormateurbyId(idFormateur);
//verifier si role = 3 Formateur

        vueAdmin.choicesFormateurCrudModif();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 3) {
            vueAdmin.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                updateFormateur(user, formateur);
                break;
            case 2:
                deleteFormateur(user, formateur);
                break;
            case 3:
                crudFormateur(user);
                break;
        }
    }

    public void updateFormateur(User user, Formateur formateur) {
        vueAcceuil.newUserNom();
        String nom = sc.next();
        formateur.setNom(nom);
        vueAcceuil.newUserPrenom();
        String prenom = sc.next();
        formateur.setPrenom(prenom);
        vueAcceuil.newUseradresse();
        String adresse = sc.next();
        formateur.setAdresse(adresse);
        vueAcceuil.newUseremail();
        String email = sc.next();
        formateur.setEmail(email);
        vueAcceuil.newUserPassword();
        String password = sc.next();
        formateur.setPassword(password);
        formateur.updateFormateur();
    }

    private void deleteFormateur(User user, Formateur formateur) {
        formateur.deleteFormateur();
        selectFormateur(user);
    }

    private void CrudSession(User user, Formation formation) {

        if (facade.getCentre().listeSessionbyFormation(formation).isEmpty()) {
            vueSession.zeroSession();
        } else {
            vueSession.resultsListSession(facade.getCentre().listeSessionbyFormation(formation));
        }
        VueSession.inputSessionId();
        int idSession = sc.nextInt();

        Session selectedSession = facade.getCentre().getSessionbyId(idSession);
        crudSelectedSession(user, formation, selectedSession);

    }

    public void crudSelectedSession(User user, Formation formation, Session session) {
        VueAdmin menuAdmin = new VueAdmin();
        vueSession.crudSelectedSession();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 5) {
            vueSession.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                //createSession(user, formation);
                break;
            case 2:
              //  updateSession(user, session);
                break;
            case 3:
                deleteSession(user, session);
                break;
            case 5:
                menuAdmin.choices(user);
                break;

        }
    }

    private void deleteSession(User user, Session session) {
        session.deleteSession();
        System.out.println("DELETE DONE");
    }

  
}
