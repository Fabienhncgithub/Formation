/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.ControllerInterface.facade;
import java.util.List;
import model.Formation;
import model.Session;
import model.User;

/**
 *
 * @author Fabien
 */
public class Controller implements ControllerInterface {

    public User usr = null;

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public Controller() {
    }

    public void checkInt() {
        while (!sc.hasNextInt()) {
            vueAcceuil.errorInput();
            sc.nextLine();
        }
    }

    public void retourMenuAdmin(int input, User user) {
        if (input == 0) {
            controllerAdmin.adminChoices(user);
        }
    }

    public void retourMenuAcceuil(String input, User user) {
        if (input.equals('0')) {
            controllerAcceuil.firstMenu();
        }
    }

    public void getAllSessionByFormation(User user, Formation formation) {
        List<Session> ListeSession = facade.getCentre().listeSessionbyFormation(formation);
        vueSession.resultsListSession(ListeSession);
    }

    public void getAllFormation(User user) {
        vueFormation.resultsListFormation(facade.getCentre().getAllFormation());
    }

    public void getChoiceSession(User user) {
        List<Formation> formationsList = facade.getCentre().getAllFormation();
        vueFormation.resultsListFormation(formationsList);

        int sessionId;
        do {
            vueFormation.inputFormationId();
            sc.nextLine();
            controller.checkInt();
            sessionId = sc.nextInt();
            controller.retourMenuAdmin(sessionId, user);
        } while (facade.getCentre().getSessionbyId(sessionId) == null);
        controller.getAllSessionByFormation(user, facade.getCentre().getFormationbyId(sessionId));
    }

    //vérifie si le choix est bien dans le menu
    public int checkMenuChoice(int i) {
        vueAcceuil.choice();
        controller.checkInt();
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > i) {
            vueAcceuil.error();
            controller.checkInt();
            menuchoice = sc.nextInt();
        }
        return menuchoice;
    }

    public int checkFormateurById() {
        int formateurId;
        do {
            vueAdmin.inputFormateurId();
            formateurId = sc.nextInt();
        } while (facade.getCentre().getFormateurbyId(formateurId) == null);
        return formateurId;
    }

}
