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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Inscription;
import model.Local;
import model.Session;
import model.Statut;
import org.mindrot.jbcrypt.BCrypt;
import static controller.ControllerInterface.*;

class ControllerAdmin {

    public ControllerAdmin() {
    }

    public void adminChoices(User user) {
        vueAdmin.choices(user, facade.getCentre().getInscritpionPaiementNotification());

        int menuchoice = 6;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                validationPaiment(user);
                break;
            case 2:
                crudFormation(user);
                break;
            case 3:
                crudFormateur(user);
                break;
            case 4:
                crudLocaux(user);
                break;
            case 5:
                crudStatut(user);
                break;
            case 6:
                controllerAcceuil.firstMenu();
                break;
        }

    }

    public void crudFormation(User user) {
        vueFormation.choicesCrud();
        int menuchoice = 10;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                controller.getAllFormation(user);
                crudFormation(user);
                break;
            case 2:
                createFormation(user);
                break;
            case 3:
                selectFormation(user);
                break;
            case 4:
                ListeStagiaireBySession(user);
                break;
            case 5:
                showInformationToFormateur(user);
                break;
            case 6:
                showSessionbyFormateur(user);
                break;
            case 7:
                verificationNbrInscriptions(user);
                break;
            case 8:
                searchFormateur(user);
                break;
            case 9:
                showUserBySession(user);
                break;
            case 10:
                adminChoices(user);
                break;
        }
    }

    public void crudSelectedFormation(User user, Formation formation) {
        vueFormation.crudSelectedFormation();

        int menuchoice = 5;
        switch (controller.checkMenuChoice(menuchoice)) {
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
        int id = formation.getIdFormation();
        sc.nextLine();

        boolean sameId = false;
        String nomFormation;
        do {

            do {
                vueFormation.inputFormationNom();
                nomFormation = sc.nextLine();

            } while (nomFormation == null || nomFormation.trim().isEmpty());

            if (facade.getCentre().getFormationByNom(nomFormation) != null) {
                Formation fmt = facade.getCentre().getFormationByNom(nomFormation);
                if (fmt.getIdFormation() == id) {
                    sameId = true;
                }
            } else {
                sameId = true;
            }
        } while (!sameId);
        formation.setNomFormation(nomFormation);
        int prix = 0;
        do {
            vueFormation.inputFormationPrix();
            controller.checkInt();
            prix = sc.nextInt();
        } while (prix < 0);
        formation.setPrix(prix);
        vueFormation.inputFormationDuree();
        int duree = sc.nextInt();
        formation.setDuree(duree);
        int participantMax = 0;
        do {
            vueFormation.inputFormationParticipantMax();
            controller.checkInt();
            participantMax = sc.nextInt();
        } while (0 > participantMax);
        formation.setParticipantMax(participantMax);
        return formation;
    }

    public void updateFormation(User user, Formation formation) {
        formation = inputFormation(user, formation);
        facade.getCentre().updateFormation(formation);
        crudFormation(user);

    }

    public void selectFormation(User user) {
        controller.getAllFormation(user);
        int idFormation;
        do {
            vueFormation.inputFormationId();
            idFormation = sc.nextInt();
            controller.retourMenuAdmin(idFormation, user);
        } while (facade.getCentre().getFormationbyId(idFormation) == null);

        crudSelectedFormation(user, facade.getCentre().getFormationbyId(idFormation));
    }

    public void deleteFormation(User user, Formation formation) {
       
        if (!user.deleteFormation(formation)) {
         
            vueAdmin.SessionStillAvailable();
            crudSelectedFormation(user, formation);

        } else {
            vueAcceuil.success();
            crudSelectedFormation(user, formation);

        }
    }

    public void crudFormateur(User user) {
        vueAdmin.choicesFormateurCrud();

        int menuchoice = 5;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                getAllFormateur(user);
                crudFormateur(user);
                break;
            case 2:
                menuEnseigneFormation(user);
                break;
            case 3:
                createFormateur(user);
                break;
            case 4:
                getAllFormateur(user);
                selectFormateur(user, controller.checkFormateurById());
                break;
            case 5:
                adminChoices(user);
                break;
        }

    }

    public void createFormateur(User user) {
        Formateur formateur = new Formateur();
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
        int id = formateur.getIdUser();
        User usr;
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

            } while (!email.matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") || email == null || email.trim().isEmpty());

            if (facade.getCentre().getUserByEmail(email) != null) {
                usr = facade.getCentre().getUserByEmail(email);
                if (usr.getIdUser() == id) {
                    sameId = true;
                }
            } else {
                sameId = true;
            }
        } while (!sameId);
        user.setEmail(email.trim());

        vueAcceuil.newUserPassword();
        String password = sc.next();
        String hasedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hasedPassword);

        formateur.updateFormateur();
        crudFormateur(user);
    }

    private void deleteFormateur(User user, Formateur formateur) {
        if (formateur.deleteFormateur()) {
            vueAcceuil.success();
        } else {
            vueAdmin.errorDeleteFormateur();
        }
        crudFormateur(user);
    }

    public void crudSelectedSession(User user, Formation formation) {
        vueSession.crudSelectedSession();

        int menuchoice = 5;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                rudSelectedSession(user, formation);
                break;
            case 2:
                createSession(user, formation);
                break;
            case 3:
                adminChoices(user);
                break;
        }
    }

    private void showInformationToFormateur(User user) {
        List<Session> informationsFormateurList = facade.getCentre().listeInformationsByFormateurs();
        vueAdmin.resultsListInformationsFormateur(informationsFormateurList);
        adminChoices(user);
    }

    private void createSession(User user, Formation formation) {
        Session session = new Session();

        Date date = null;
        vueSession.newDateDebut();
        String dateDebut = sc.next();
        sc.nextLine();

        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            formatter.setLenient(false);
            date = formatter.parse(dateDebut);

            if (date.before(new Date())) {
                session.setDateDebut(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        session.setDateF(date, formation.getDuree());

        if (facade.getCentre().getFormateurAvailable(session, formation).isEmpty() || facade.getCentre().getLocalAvailable(session, formation).isEmpty()) {
            vueAdmin.FormateurNotAvailable();
            crudSelectedSession(user, formation);
        }

        int idFormateur;
        do {
            vueSession.newFormateur();
            List<Formateur> listFormateur = facade.getCentre().getFormateurAvailable(session, formation);
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

        if (facade.getCentre().CreateNewSession(session, formation)) {
            vueAcceuil.success();
            crudSelectedSession(user, formation);

        } else {
            vueSession.formateurNotAvailable();
            crudSelectedSession(user, formation);
        }
    }

    private void showUserBySession(User user) {
        controller.getAllFormation(user);
        int formationId;
        do {
            vueFormation.inputFormationId();
            controller.checkInt();
            formationId = sc.nextInt();
            controller.retourMenuAdmin(formationId, user);
        } while (facade.getCentre().getFormationbyId(formationId) == null);
    }

    private void validationPaiment(User user) {
        if (!facade.getCentre().getInscritpionPaiementNotification().isEmpty()) {
            List<Inscription> listeInscriptionNotificationPaiment = facade.getCentre().getInscritpionPaiementNotification();
            vueStagiaire.resultsListInscription(listeInscriptionNotificationPaiment);
            int inscriptionId;
            do {
                vueAdmin.inputPaiement();
                sc.nextLine();
                controller.checkInt();
                inscriptionId = sc.nextInt();
                controller.retourMenuAdmin(inscriptionId, user);

            } while (facade.getCentre().getInscriptionbyId(inscriptionId) == null);

            facade.getCentre().validationStatutPaiment(inscriptionId);
            vueAcceuil.success();
            adminChoices(user);
        } else {
            vueAcceuil.zeroInscription();
            adminChoices(user);
        }
    }

    private void ListeStagiaireBySession(User user) {
        vueSession.menuListStagiaireBySession();

        int menuchoice = 3;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                resultListStagiaireBySession(user);
                break;
            case 2:
                SearchByFormationSession(user);
                break;
            case 3:
                adminChoices(user);
                break;
        }
    }

    private void resultListStagiaireBySession(User user) {
        int sessionId;
        do {
            vueSession.inputSessionId();
            sc.nextLine();
            controller.checkInt();
            sessionId = sc.nextInt();
            controller.retourMenuAdmin(sessionId, user);
        } while (facade.getCentre().getSessionbyId(sessionId) == null);
        vueSession.resulListStagiaireBySession(facade.getCentre().resultListStagiaireBySession(sessionId));
        ListeStagiaireBySession(user);
    }

    private void SearchByFormationSession(User user) {
        controller.getChoiceSession(user);
        resultListStagiaireBySession(user);
    }

    private void searchFormateur(User user) {
        controller.getAllFormation(user);
        int idFormation;
        do {
            vueFormation.inputFormationId();
            controller.checkInt();
            idFormation = sc.nextInt();
        } while (facade.getCentre().getFormationbyId(idFormation) == null);
        controller.getAllSessionByFormation(user, facade.getCentre().getFormationbyId(idFormation));
        int idSession;
        do {
            vueSession.inputSessionId();
            controller.checkInt();
            idSession = sc.nextInt();
        } while (facade.getCentre().getSessionbyId(idSession) == null);
        vueSession.formateurBysession(facade.getCentre().getFormateurBySession(idSession));
        adminChoices(user);
    }

    private void showSessionbyFormateur(User user) {
        int idFormateur;
        do {
            vueAdmin.inputFormateurId();
            controller.checkInt();
            idFormateur = sc.nextInt();
        } while (facade.getCentre().getFormateurbyId(idFormateur) == null);
        vueSession.resultsListSession(facade.getCentre().listeSessionByIdFormateur(idFormateur));
        adminChoices(user);
    }

    private void rudSelectedSession(User user, Formation formation) {
        controller.getAllSessionByFormation(user, formation);
        int idSession;
        do {
            vueSession.inputSessionId();
            controller.checkInt();
            idSession = sc.nextInt();
        } while (facade.getCentre().getSessionbyId(idSession) == null);
        vueSession.rudSelectedSession();

        int menuchoice = 3;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                updateSession(user, idSession, formation);
                break;
            case 2:
                deleteSession(user, idSession, formation);
                break;
            case 3:
                adminChoices(user);
                break;
        }

    }

    private void updateSession(User user, int idSession, Formation formation) {

        Session session = new Session();
        session.setIdSession(idSession);

        Date date = null;
        vueSession.newDateDebut();
        String dateDebut = sc.next();
        sc.nextLine();

        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            formatter.setLenient(false);
            date = formatter.parse(dateDebut);
            session.setDateDebut(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        session.setDateF(date, formation.getDuree());

        if (facade.getCentre().getFormateurAvailable(session, formation).isEmpty() || facade.getCentre().getLocalAvailable(session, formation).isEmpty()) {
            vueAdmin.FormateurNotAvailable();
            crudSelectedSession(user, formation);
        }

        int idFormateur;
        do {
            vueSession.newFormateur();
            List<Formateur> listFormateur = facade.getCentre().getFormateurAvailable(session, formation);
            vueSession.resultListformateur(listFormateur);
            controller.checkInt();
            idFormateur = sc.nextInt();
        } while (facade.getCentre().getFormateurbyId(idFormateur) == null);
        session.setIdformateur(facade.getCentre().getFormateurbyId(idFormateur));

        int idLocal;
        do {
            vueSession.newLocal();
            List<Local> lisLocal = facade.getCentre().getAllLocal();
            vueSession.resultListLocal(lisLocal);
            controller.checkInt();
            idLocal = sc.nextInt();
        } while (facade.getCentre().getLocalById(idLocal) == null);
        session.setIdLocal(facade.getCentre().getLocalById(idLocal));

        facade.getCentre().UpdateSession(session, formation);
        vueAcceuil.success();
        crudSelectedSession(user, formation);

    }

    private void deleteSession(User user, int idSession, Formation formation) {
        facade.getCentre().getSessionbyId(idSession).deleteSession();
        rudSelectedSession(user, formation);
    }

    private void verificationNbrInscriptions(User user) {
        List<Formation> formationsList = facade.getCentre().getAllFormation();
        vueFormation.resultsListFormation(formationsList);

        int FormationId;
        do {
            vueFormation.inputFormationId();
            sc.nextLine();
            controller.checkInt();
            FormationId = sc.nextInt();
            controller.retourMenuAdmin(FormationId, user);
        } while (facade.getCentre().getFormationbyId(FormationId) == null);
        controller.getAllSessionByFormation(user, facade.getCentre().getFormationbyId(FormationId));
        int idSession;
        do {
            vueSession.inputSessionId();
            controller.checkInt();
            idSession = sc.nextInt();
        } while (facade.getCentre().getSessionbyId(idSession) == null);
        int inscriptionPlace = facade.getCentre().getSessionbyId(idSession).getListeInscriptionbySession().size();
        int nbrMax = facade.getCentre().getFormationbyId(FormationId).getParticipantMax();
        int libre = nbrMax - inscriptionPlace;
        vueSession.nbrPlaceLibre(libre, idSession);
        adminChoices(user);
    }

    private void crudLocaux(User user) {
        vueAdmin.resulListAllLocal(facade.getCentre().getAllLocal());
        vueAdmin.menuListCrudLocaux();

        int menuchoice = 4;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                createLocaux(user);
                break;
            case 2:
                updateLocaux(user);
                break;
            case 3:
                deleteLocaux(user);
                break;
            case 4:
                adminChoices(user);
                break;
        }
    }

    private void createLocaux(User user) {
        Local local = new Local();
        vueAdmin.inputNomLocal();
        sc.nextLine();
        String nomLocal = sc.nextLine();
        local.setNomLocal(nomLocal);
        if (facade.getLocal().createLocaux(local)) {
            vueAcceuil.success();
            crudLocaux(user);
        } else {
            vueAdmin.errorDoubleLocal();
            crudLocaux(user);
        }
    }

    private void updateLocaux(User user) {
        Local local = new Local();
        int idLocal;
        do {
            vueAdmin.inputIdLocal();
            controller.checkInt();
            sc.nextLine();
            idLocal = sc.nextInt();
        } while (facade.getCentre().getLocalById(idLocal) == null);

        boolean sameId = false;
        String nomLocal = sc.nextLine();
        do {

            do {
                vueAdmin.inputNomLocal();
                nomLocal = sc.nextLine();

            } while (nomLocal == null || nomLocal.trim().isEmpty());

            if (facade.getCentre().getLocalByNom(nomLocal) != null) {
                Local lcl = facade.getCentre().getLocalByNom(nomLocal);
                if (lcl.getIdLocal() == idLocal) {
                    sameId = true;
                }
            } else {
                sameId = true;
            }
        } while (!sameId);

        local.setNomLocal(nomLocal);
        if (facade.getLocal().updateLocaux(local)) {
            vueAcceuil.success();
            crudLocaux(user);
        } else {
            vueAdmin.errorDoubleLocal();
            crudLocaux(user);
        }
    }

    private void deleteLocaux(User user) {
        Local local = new Local();
        int idLocal;
        do {
            vueAdmin.inputIdLocal();
            controller.checkInt();
            idLocal = sc.nextInt();
        } while (facade.getCentre().getLocalById(idLocal) == null);
        local.setIdLocal(idLocal);
        if (facade.getLocal().deleteLocaux(local)) {
            vueAcceuil.success();
            crudLocaux(user);
        } else {
            vueAdmin.errorSupprime();
        }

    }

    private void crudStatut(User user) {
        vueAcceuil.resultListStatut(facade.getCentre().getAllStatut());
        vueAdmin.menuListCrudStatut();

        int menuchoice = 3;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                createStatut(user);
                break;
            case 2:
                updateStatut(user);
                break;
            case 3:
                adminChoices(user);
                break;
        }
    }

    private void createStatut(User user) {
        Statut statut = new Statut();
        vueAdmin.inputNomStatut();
        sc.nextLine();
        String nomStatut = sc.nextLine();
        statut.setNomStatut(nomStatut);
        vueAdmin.inputdiscountStatut();
        Double discount = sc.nextDouble();
        statut.setDiscount(discount);
        if (facade.getStatut().createStatut(statut)) {
            vueAcceuil.success();
            crudStatut(user);
        } else {
            vueAdmin.errorDoubleStatut();
            crudStatut(user);
        }
    }

    private void updateStatut(User user) {
        Statut statut = new Statut();
        int idStatut;
        do {
            vueAdmin.inputIdStatut();
            controller.checkInt();
            idStatut = sc.nextInt();
        } while (facade.getCentre().getStatutById(idStatut) == null);
        statut.setIdStatut(idStatut);
        vueAdmin.inputNomStatut();
        sc.nextLine();
        String nomStatut = sc.nextLine();
        statut.setNomStatut(nomStatut);
        Double discount;
        do {
            vueAdmin.inputdiscountStatut();
            discount = sc.nextDouble();
        } while (discount < 0 || discount > 100);
        statut.setDiscount(discount);
        if (facade.getStatut().updateStatut(statut)) {
            vueAcceuil.success();
            crudStatut(user);
        } else {
            vueAdmin.errorDoubleStatut();
            crudStatut(user);

        }
    }

    private void menuEnseigneFormation(User user) {
        getAllFormateur(user);

        int idFormateur;
        do {
            vueAdmin.inputFormateurId();
            controller.checkInt();
            idFormateur = sc.nextInt();
        } while (facade.getCentre().getFormateurbyId(idFormateur) == null);
        vueAdmin.menuEnseigneFormation();

        int menuchoice = 3;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                addFormationFormateur(user, idFormateur);
                break;
            case 2:
                deleteFormationFormateur(user, idFormateur);
                break;
            case 3:
                crudFormateur(user);
                break;
        }

    }

    private void addFormationFormateur(User user, int idFormateur) {
        controller.getAllFormation(user);
        int idFormation;
        do {
            vueFormation.inputFormationId();
            controller.checkInt();
            idFormation = sc.nextInt();
        } while (facade.getCentre().getFormationbyId(idFormation) == null);
        if (facade.getCentre().addFormationToFormateur(idFormation, idFormateur)) {
            vueAcceuil.success();
            crudFormateur(user);
        } else {
            vueAdmin.ErrorDoubleFormationForFormateur();
            crudFormateur(user);
        }
    }

    private void deleteFormationFormateur(User user, int idFormateur) {

        if (facade.getCentre().getFormationByFormateur(idFormateur).isEmpty()) {
            vueFormateur.NotFormationForFormateur();
            crudFormateur(user);
        } else {
            vueFormation.resultsListFormation(facade.getCentre().getFormationByFormateur(idFormateur));
            int idFormation;
            do {
                vueFormation.inputFormationIdtoDelete();
                controller.checkInt();
                idFormation = sc.nextInt();
            } while (facade.getCentre().getFormationbyId(idFormation) == null);
            if (facade.getCentre().deleteFormationToFormateur(idFormation, idFormateur)) {
                vueAcceuil.success();
                crudFormateur(user);
            } else {
                vueAdmin.ErrorDoubleFormationForFormateur();
                crudFormateur(user);
            }
        }
    }

}
