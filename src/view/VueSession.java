/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Formation;
import model.Session;
import java.util.List;
import model.Formateur;
import model.Local;

/**
 *
 * @author Fabien
 */
public class VueSession {

    public void inputSessionId() {
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
            System.out.println("Formation: " + s.getFormation().getNomFormation() 
                    + " \n" + " \n" + "idFormateur: " + s.getIdformateur().getNom()
                    + " \n" + " \n" + "idLocal: " + s.getIdLocal().getNomLocal() 
                    + " \n" + " \n" + "dateDébut: " + s.getDateDebut() 
                    + " \n" + " \n" + "dateFin: " + s.getDateFin());
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
        System.out.println("Tapez 1 pour voir les sessions de cette formation");
        System.out.println("");
        System.out.println("Tapez 2 créer la session");
        System.out.println("");
        System.out.println("Tapez 3 modifier la session");
        System.out.println("");
        System.out.println("Tapez 4 pour supprimer la session");
        System.out.println("");
        System.out.println("Tapez 5 pour revenir au menu précédent");
    }

    public void error() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Ce choix n'existe pas");
    }

    public void newFormateur() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Selectionnez le code du formateur valide");
    }

    public void resultListformateur(List<Formateur> listFormateur) {
        for (Formateur f : listFormateur) {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Code formateur: " + f.getIdUser() + " \n" + " \n" + "Nom formateur: " + f.getNom() + " " + f.getPrenom());
        }
    }

    public void newLocal() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Selectionnez un code local valide: ");
    }

    public void resultListLocal(List<Local> listLocal) {
        for (Local l : listLocal) {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Code local: " + l.getIdLocal() + " \n" + " \n" + "Nom local: " + l.getNomLocal());
        }
    }

    public void newDateDebut() {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Encodez une date de début valide dd-MM-yyyy: ");
        }
        public void newDateFin() {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Encodez une date de fin valide dd-MM-yyyy: ");
        }

    public void formateurNotAvailable() {
             System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Ce prof n'est pas disponible pour ces dates");
    }

}
