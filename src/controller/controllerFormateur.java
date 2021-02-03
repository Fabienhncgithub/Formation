/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import static controller.ControllerInterface.*;

/**
 *
 * @author fabien
 */
class controllerFormateur {

    public void formateurChoices(User user) {
        vueFormateur.choices(user);
    
          int menuchoice = 2;
        switch (controller.checkMenuChoice(menuchoice)) {
            case 1:
                vueSession.resultsListSession(facade.getCentre().listeSessionByIdFormateur(user.getIdUser()));
                formateurChoices(user);
                break;
            case 2:
                controllerAcceuil.firstMenu();
                break;
        
    }
    
}}
