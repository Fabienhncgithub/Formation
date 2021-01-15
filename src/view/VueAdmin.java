/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Formateur;
import model.User;
import java.util.List;
import model.Session;

/**
 *
 * @author Fabien
 */
public class VueAdmin {

    public void choices(User user) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Bonjour " + user.getNom() + " , vous êtes connecté en tant que administrateur");
        System.out.println("");
        System.out.println("");
        System.out.println("Tapez 1 pour valider les paiements d'inscription en attente");
        System.out.println("");
        System.out.println("Tapez 2 pour sortir la liste des stagiaires inscrit à une session avec le prix et son statut");
        System.out.println("");
        System.out.println("Tapez 3 pour communiquer à chaque formateur les prestations qu'il doit assurer(jour, local, formation)");
        System.out.println("");
        System.out.println("Tapez 4 pour obtenir les dates des jours prévus pour une session");
        System.out.println("");
        System.out.println("Tapez 5 pour sortir pour chaque formation la liste des sessions planifiées et le nombre de places encore dispo");
        System.out.println("");
        System.out.println("Tapez 6 pour gérer les formations et les sessions");
        System.out.println("");
        System.out.println("Tapez 8 pour gérer les formateurs");
        System.out.println("");
        System.out.println("Tapez 9 pour afficher les personnes inscrites à une session donnée");
        System.out.println("");
        System.out.println("Tapez 10 pour vérifier s'il reste encore des places libre pour une session. ");
        System.out.println("");
        System.out.println("Tapez 11 pour vérifier dans quel local une session se donne");
        System.out.println("");
        System.out.println("Tapez 12 pour rechercher le formateur d'une session");
        System.out.println("");
        System.out.println("Tapez 13 pour gèrer les locaux");
        System.out.println("");
        System.out.println("Tapez 14 pour gèrer les statuts professionels des étudiants");
        System.out.println("");
        System.out.println("Tapez 15 pour nettoyer la base de donées (sessions de il y a 1 année)");
        System.out.println("");
        System.out.println("Tapez 16 Quitter");

    }

    public void error(User user) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Erreur! ce choix n'éxiste pas");
        choices(user);
    }

    public void choiceSelection() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("selectionner le numero");
    }

    public void modificationFormationChoices() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 liste des formations");
        System.out.println("");
        System.out.println("Tapez 2 ajouter une formation");
        System.out.println("");
        System.out.println("Tapez 3 modifier une formation");
        System.out.println("");
        System.out.println("Tapez 4 our revenir au menu précédent");
    }

    public void modificationFormationError() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Erreur! ");
        System.out.println("");
        modificationFormationChoices();
    }

    public void newFormation() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le formation et ensuite prix et durée en jours: ");
    }

    public void research() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le nom de la formation: ");
    }

    public void createNomFormation() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le nom de la ");
    }

    public void choicesFormateurCrud() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 pour l'affichage des formateurs");
        System.out.println("");
        System.out.println("Tapez 2 pour la création d'un formateur");
        System.out.println("");
        System.out.println("Tapez 3 pour modifier un formateur");
        System.out.println("");
        System.out.println("Tapez 4 pour revenir au menu précédent");
    }

    public void error() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("choix inexistant, réessayez");
    }

    public void resultsListFormateur(List<Formateur> listFormateur) {
        for (Formateur f : listFormateur) {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("idFormateur: " + f.getIdUser() + " \n" + " \n" + "Nom formateur: " + f.getNom() + " \n" + " \n" + "Role: " + f.getRole().getNomRole());
        }
    }

    public void choicesFormateurCrudModif() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 pour modifier le formateur");
        System.out.println("");
        System.out.println("Tapez 2 pour supprimer le formateur");
        System.out.println("");
        System.out.println("Tapez 3 pour revenir au menu précédent");
    }

    public void inputFormateurId() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez le code du formateur");
    }

    public void resultsListInformationsFormateur(List<Session> informationsFormateurList) {
        for (Session s : informationsFormateurList) {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Formation: " + s.getFormation().getNomFormation() + " \n" + " \n" + "Formateur: " + s.getIdformateur().getNom() + " \n" + " \n" + "Date de début: " + s.getDateDebut() + " \n" + " \n" + "Date de fin: " + s.getDateFin() + " \n" + " \n" + "Local: " + s.getIdLocal().getNomLocal());
        }
    }
}
