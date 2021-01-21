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
        System.out.println("");
        System.out.println("Bonjour " + user.getNom() + " , vous êtes connecté");
        System.out.println("");
        System.out.println("Tapez 1 pour modifier vos informations personnelles");
        System.out.println("");
        System.out.println("Tapez 2 pour vous inscrire à une session donnée d'une formation donnée");
        System.out.println("");
        System.out.println("Tapez 3 pour annuler une de vos inscriptions");
        System.out.println("");
        System.out.println("tapez 4 pour consultez la liste de vos inscriptions ou valider un paiment");
        System.out.println("");
        System.out.println("tapez 5 pour quitter");
    }

    public void stagiaireChoicesError(User user) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Erreur! ce choix n'éxiste pas");
        stagiaireChoices(user);
    }

    public void StatutInscription(User user, Session session) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Bravo, " + user.getNom() + "est inscrit à la session " + session.getIdSession() + "qui débute le " + session.getDateDebut());
    }

    public void resultsListInscription(List<Inscription> inscriptionsList) {
        String statut;
        String statutPaiement;
        String notificationPaiement;
        for (Inscription i : inscriptionsList) {
            if (!i.getAnnule()) {
                statut = "inscrit";
            } else {
                statut = "annulé";
            }
            if ((i.getNotificationPaiement())) {
                notificationPaiement = " Aucun paiment de votre part";
            } else {
                notificationPaiement = " En attente de confirmation de paiement par le système";
            }

            if (i.getStatutPaiement()) {
                statutPaiement = " impayé";
            } else {
                statutPaiement = " payé";
                notificationPaiement = " payé";
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("Numero d'inscription: " + i.getIdInscription() + " \n" + " \n" + "Prix: " + i.getPrix() + " \n" + " \n" + "Statut paiement: " + statutPaiement + " \n" + " \n" + "notification de paiement: " + notificationPaiement + " \n" + " \n" + "Statut inscription: " + statut);
        }
    }

    public void zeroInscription() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Vous avez aucune inscription");
    }

    public void erreurDoubleInscription() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Vous êtes déjà inscrit à cette session");
    }

    public void formationAnule() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Vous avez annulé votre inscription à cette session");
    }

    public void formationNotAnule() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Vous n'avez pas annulé votre inscription à cette session");
    }

    public void validationPaiement() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 pour valider votre paiement pour une de vos inscriptions");
        System.out.println("");
        System.out.println("Tapez 2 pour revenir au menu précédent");
    }
}
