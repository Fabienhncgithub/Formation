/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;

/**
 *
 * @author fabien
 */
class controllerFormateur implements ControllerInterface {

    public void formateurChoices(User user) {
        vueFormateur.choices(user);
        int menuchoice = sc.nextInt();
        while (menuchoice < 1 || menuchoice > 2) {
            vueAdmin.error(user);
            menuchoice = sc.nextInt();
        }
        switch (menuchoice) {
            case 1:
                vueSession.resultsListSession(facade.getCentre().listeSessionByIdFormateur(user.getIdUser()));
                formateurChoices(user);
                break;
            case 2:
                controllerAcceuil.firstMenu();
                break;
        
    }
    
}}
