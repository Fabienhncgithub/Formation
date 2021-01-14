/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Formation;
import model.Session;
import java.util.List;

/**
 *
 * @author Fabien
 */
public class VueSession {

    public static void inputSessionId() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le code session");
    }

    public VueSession() {
    }

    public void resultsListSession(List<Session> listSession) {
        for (Session s : listSession) {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Formation: " + s.getFormation().getNomFormation() + " \n"+ " \n" + "idFormateur: " + s.getIdformateur()+ " \n" + " \n" + "idLocal: " + s.getIdLocal().getNomLocal()+ " \n" + " \n" + "dateDébut: " + s.getDateDebut() + " \n"+ " \n" + "dateFin: " + s.getDateFin());
        }
    }

    public void zeroSession() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Il n'y pas de session pour cette formation");
    }

    public void crudSelectedSession() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 créer la session");
        System.out.println("");
        System.out.println("Tapez 2 modifier la session");
        System.out.println("");
        System.out.println("Tapez 3 pour supprimer la session");
        System.out.println("");
        System.out.println("Tapez 4 pour revenir au menu précédent");
    }

    public void error() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Ce choix n'existe pas");
    }

}
