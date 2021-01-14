/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Formation;
import java.util.List;

/**
 *
 * @author Fabien
 */
public class VueFormation {

    public void errorSearchFormation() {
        System.out.println("Cette formation n'éxista pas");
    }

    public void cataloguesChoices() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 Afficher la titre des formations disponible");
        System.out.println("");
        System.out.println("Tapez 2 Faire une recherche dans la liste de formation");
        System.out.println("");
        System.out.println("Tapez 3 Quitter");
    }

    public void newFormation() {
    }

    public void resultsListFormation(List<Formation> formationsList) {
        for (Formation f : formationsList) {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("idFormation: " + f.getIdFormation() + " \n" + "Formation: " + f.getNomFormation() + "\n" + "Prix: " + f.getPrix() + " €" + " \n" + "Duree: " + f.getDuree() + " jours" + " \n" + "Participants max: " + f.getParticipantMax() + " \n" + "Participant min: " + f.getParticipantMin());
        }
    }

    public void choicesCrud() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Tapez 1 pour la création d'une formation");
        System.out.println("");
        System.out.println("Tapez 2 pour l'affichage des formations");
        System.out.println("");
        System.out.println("Tapez 3 pour gérer une formation ou une session");
        System.out.println("");
        System.out.println("Tapez 4 pour revenir au menu précédent");
    }

    public void error() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Erreur! ce choix n'éxiste pas");
        System.out.println("Réessayer avec une selection correcte");
        
    }

    public void inputFormationNom() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Entrez le nom de la formation");
    }

    public void inputFormationId() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le code de la formation");

    }

    public void inputFormationPrix() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("encodez le prix de la formation");
    }

    public void inputFormationDuree() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("encodez le temps en jour de formation de la formation");
    }

    public void inputFormationParticipantMin() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("encodez le nombre minimum de participant");
    }

    public void inputFormationParticipantMax() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("encodez le nombre maximum de participant");
    }

    public void crudSelectedFormation() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Tapez 1 modifier la formation");
        System.out.println("Tapez 2 pour supprimer la formations");
        System.out.println("Tapez 3 pour la création oumidifcation une session à cette formation");
        System.out.println("Tapez 4 pour créer une session pour cette formation");
        System.out.println("Tapez 5 pour revenir au menu précédent");
    }

    public void zeroFormation() {
        System.out.println("cette formation n'existe pas");
    }
}
