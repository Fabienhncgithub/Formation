/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Inscription;
import model.Session;
import model.User;
import java.util.List;

/**
 *
 * @author Fabien
 */
public class VueStagiaire {

    public void stagiaireChoices(User user) {
                      System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Bonjour " + user.getNom() + " , vous êtes connecté");
        System.out.println("");
        System.out.println("Tapez 1 pour modifier vos informations personnelles");
        System.out.println("");
        System.out.println("Tapez 2 pour vous inscrire à une session donnée d'une formation donnée");
        System.out.println("");
        System.out.println("Tapez 3 pour annuler une de vos inscriptions");
        System.out.println("");
        System.out.println("tapez 4 pour consultez la liste de vos inscriptions");
        System.out.println("");
        System.out.println("tapez 5 pour quitter");
    }

    public void stagiaireChoicesError(User user) {
        System.out.println("Erreur! ce choix n'éxiste pas");
        System.out.println("");
        stagiaireChoices(user);
    }

    public void StatutInscription(User user, Session session) {
        System.out.println("Bravo, " + user.getNom() + "est inscrit à la session " + session.getIdSession() + "qui débute le " + session.getDateDebut());
    }

    public void resultsListInscription(List<Inscription> inscriptionsList) {
        for (Inscription i : inscriptionsList) {
              System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("Nom formation: " + i.getIdSession().getFormation().getNomFormation() + " \n"  + " \n" + "Local: " + i.getIdSession().getIdLocal().getNomLocal() + " \n" + " \n" + "Date de début: "+ i.getIdSession().getDateDebut() + " \n" + " \n" + "Date de fin: "+ i.getIdSession().getDateFin()+ " \n" + " \n" + "Statut paiement: " + i.getStatutPaiement() + " \n"+ " \n" + "notificationPaiement: " + i.getNotificationPaiement());
        }
    }

    public void zeroInscription() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Vous avez aucune inscription");
    }

    public void erreurDoubleInscription() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Vous êtes déjà inscrit à cette session");
    }
}
