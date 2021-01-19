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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Inscription;
import model.Local;
import model.Session;
import view.VueSession;
import view.VueStagiaire;

/**
 *
 * @author Fabien
 */
class ControllerAdmin implements ControllerInterface {

    private VueAdmin vueAdmin = new VueAdmin();
    private VueAcceuil vueAcceuil = new VueAcceuil();
    private VueFormation vueFormation = new VueFormation();
    private VueSession vueSession = new VueSession();
    private VueStagiaire vueStagiaire = new VueStagiaire();

    public ControllerAdmin(VueAdmin vueAdmin, VueAcceuil vueAcceuil, VueFormation vueFormation, VueSession vueSession) {
        this.vueAdmin = vueAdmin;
        this.vueAcceuil = vueAcceuil;
        this.vueFormation = vueFormation;
        this.vueSession = this.vueSession;
    }

    ControllerAdmin() {
    }

    public void adminChoices(User user) {
        vueAdmin.choices(user, facade.getCentre().getInscritpionPaiementNotification());
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 15) {
            vueAdmin.error(user);
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                validationPaiment(user);
                break;
            case 2:
                listStagiaireBySessionPriceStatut(user);
                break;
            case 3:
                showInformationToFormateur(user);
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
                showUserBySession(user);
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
                adminChoices(user);
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
                crudSelectedSession(user, formation);
                break;
            case 4:
                adminChoices(user);
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
        sc.nextLine();

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
        crudFormation(user);

    }

    public void selectFormation(User user) {
        vueFormation.resultsListFormation(facade.getCentre().getAllFormation());
        int idFormation;
        do {
            vueFormation.inputFormationId();
            idFormation = sc.nextInt();
              controller.retourMenuAdmin(idFormation, user);
        } while (facade.getCentre().getFormationbyId(idFormation) == null);

        crudSelectedFormation(user, facade.getCentre().getFormationbyId(idFormation));
    }

    public void deleteFormation(User user, Formation formation) {
        user.deleteFormation(formation);
        selectFormation(user);
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
                getAllFormateur(user);
                int formateurId;
                do {
                    vueAdmin.inputFormateurId();
                    formateurId = sc.nextInt();
                    
                } while (facade.getCentre().getFormateurbyId(formateurId) == null);
                selectFormateur(user, formateurId);
                break;
            case 4:
                adminChoices(user);
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

    private void selectFormateur(User user, int formateurId) {
        Formateur formateur = facade.getCentre().getFormateurbyId(formateurId);

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
        crudFormateur(user);
    }

    private void deleteFormateur(User user, Formateur formateur) {
        formateur.deleteFormateur();
        crudFormateur(user);
    }

//    private void CrudSession(User user, Formation formation) {
//        
//        if (facade.getCentre().listeSessionbyFormation(formation).isEmpty()) {
//            vueSession.zeroSession();
//            crudSelectedFormation(user, formation);
//            
//        } else {
//            vueSession.resultsListSession(facade.getCentre().listeSessionbyFormation(formation));
//        }
//        VueSession.inputSessionId();
//        int idSession = sc.nextInt();
//        
//        Session selectedSession = facade.getCentre().getSessionbyId(idSession);
//        crudSelectedSession(user, formation, selectedSession);
//        
//    }
    public void crudSelectedSession(User user, Formation formation) {
        VueAdmin menuAdmin = new VueAdmin();
        vueSession.crudSelectedSession();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 5) {
            vueSession.error();
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                getAllSessionByFormation(user, formation);
            case 2:
                createSession(user, formation);
                break;
            case 3:
                /*TODO a reprendre ici*/
                //updateSession(user, formation);
                break;
            case 4:
                // deleteSession(user, session);
                break;
            case 5:
                adminChoices(user);
                break;

        }
    }

    private void deleteSession(User user, Session session) {
        session.deleteSession();
    }

    private void showInformationToFormateur(User user) {
        /*TODO verifier la requete*/

        List<Session> informationsFormateurList = facade.getCentre().listeInformationsByFormateurs();
        vueAdmin.resultsListInformationsFormateur(informationsFormateurList);

        adminChoices(user);

    }

    private void createSession(User user, Formation formation) {
        Session session = new Session();
        session.setFormation(formation);

        int idFormateur;
        do {
            vueSession.newFormateur();
            List<Formateur> listFormateur = facade.getCentre().getAllFormateur();
            vueSession.resultListformateur(listFormateur);
            idFormateur = sc.nextInt();
        } while (facade.getCentre().getFormateurbyId(idFormateur) == null);
        session.setIdformateur(facade.getCentre().getFormateurbyId(idFormateur));

        int idLocal;
        do {
            vueSession.newLocal();
            List<Local> lisLocal = facade.getCentre().getAllLocal();
            vueSession.resultListLocal(lisLocal);
            idLocal = sc.nextInt();
        } while (facade.getCentre().getLocalById(idLocal) == null);
        session.setIdLocal(facade.getCentre().getLocalById(idLocal));

        String dateDebut;
        boolean result = false;
        do {
            vueSession.newDateDebut();
            dateDebut = sc.next();
            sc.nextLine();
            //Regular expression to accept date in MM-DD-YYY format
            String regex = "^(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])-[0-9]{4}$";
            result = dateDebut.matches(regex);
        } while (!result);
        Date date = null;

        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            formatter.setLenient(false);
            date = formatter.parse(dateDebut);
            session.setDateDebut(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String dateFin;
        boolean res = false;
        Date datef = null;
        int verifDate;
        do {
            vueSession.newDateFin();
            dateFin = sc.next();
            sc.nextLine();
            //Regular expression to accept date in MM-DD-YYY format
            String regex = "^(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])-[0-9]{4}$";
            result = dateDebut.matches(regex);
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                formatter.setLenient(false);
                datef = formatter.parse(dateFin);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            verifDate = datef.compareTo(date);
            
        } while (!result || verifDate < 0);

        session.setDateFin(datef);

        if (facade.getCentre().CreateNewSession(session)) {
            vueAcceuil.success();
            crudSelectedSession(user, formation);

        } else {
            vueSession.formateurNotAvailable();
            crudSelectedSession(user, formation);
        }
    }

    private void showUserBySession(User user) {
        vueFormation.resultsListFormation(facade.getCentre().getAllFormation());
        int formationId;
        do {
            vueFormation.inputFormationId();
            formationId = sc.nextInt();
            controller.retourMenuAdmin(formationId, user);
        } while (facade.getCentre().getFormationbyId(formationId) == null);
        // facade.getCentre().get
    }

    private void getAllSessionByFormation(User user, Formation formation) {
        List<Session> ListeSession = facade.getCentre().listeSessionbyFormation(formation);
        vueSession.resultsListSession(ListeSession);
    }

    private void validationPaiment(User user) {
        List<Inscription> listeInscriptionNotificationPaiment = facade.getCentre().getInscritpionPaiementNotification();
        vueStagiaire.resultsListInscription(listeInscriptionNotificationPaiment);
        int sessionId;
        do {

            vueAdmin.inputPaiement();
            sc.nextLine();
            controller.checkInt();
            sessionId = sc.nextInt();
            controller.retourMenuAdmin(sessionId, user);
        } while (facade.getCentre().getSessionbyId(sessionId) == null);
        facade.getCentre().validationStatutPaiment(sessionId);
        adminChoices(user);
    }

    private void listStagiaireBySessionPriceStatut(User user) {
         List<Inscription>listeInscriptionStagiaire = facade.getCentre().resultListStagiaireBySession();
         
    }

}
