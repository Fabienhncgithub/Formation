/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.User;

/**
 *
 * @author fabien
 */
public class VueFormateur {

    public void choices(User user) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Bonjour " + user.getNom() + " , vous êtes connecté en tant que formateur");
        System.out.println("");
        System.out.println("");
        System.out.println("Tapez 1 pour afficher la liste des sessions");
        System.out.println("");
        System.out.println("Tapez 2 Quitter");  }

    public void NotFormationForFormateur() {
         System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Il n'y a pas de formation pour ce formateur");}
    
}
