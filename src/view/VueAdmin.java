/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Formateur;
import model.User;
import java.util.List;
import model.Inscription;
import model.Local;
import model.Session;

/**
 *
 * @author Fabien
 */
public class VueAdmin {

    public void choices(User user, List<Inscription> inscritpionPaiementNotification) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Bonjour " + user.getNom() + " , vous êtes connecté en tant que administrateur");
        System.out.println("");
        System.out.println("");
        System.out.println("Tapez 1 pour valider les paiements d'inscription en attente (" + inscritpionPaiementNotification.size() + ")");
        System.out.println("");
        System.out.println("Tapez 2 pour sortir la liste des stagiaires inscrit à une session avec le prix et son statut");
        System.out.println("");
        System.out.println("Tapez 3 pour communiquer à chaque formateur les prestations qu'il doit assurer(jour, local, formation)");
        System.out.println("");
        System.out.println("Tapez 5 pour sortir pour chaque formation la liste des sessions planifiées et le nombre de places encore dispo");
        System.out.println("");
        System.out.println("Tapez 6 pour gérer les formations et les sessions");
        System.out.println("");
        System.out.println("Tapez 7 pour gérer les formateurs");
        System.out.println("");
        System.out.println("Tapez 8 pour afficher les personnes inscrites à une session donnée");
        System.out.println("");
        System.out.println("Tapez 9 pour vérifier s'il reste encore des places libre pour une session. ");
        System.out.println("");
        System.out.println("Tapez 10 pour rechercher le formateur d'une session");
        System.out.println("");
        System.out.println("Tapez 11 pour gèrer les locaux");
        System.out.println("");
        System.out.println("Tapez 12 pour gèrer les statuts professionels des étudiants");
        System.out.println("");
        System.out.println("Tapez 13 Quitter");

    }

    public void error(User user) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Erreur! ce choix n'éxiste pas");
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
        System.out.println("Tapez 3 pour modifier ou supprimer un formateur");
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
            System.out.println("Code session: " + s.getIdSession() + " \n" + " \n" + "Formateur: " + s.getIdformateur().getNom() + " \n" + " \n" + "Date de début: " + s.getDateDebut() + " \n" + " \n" + "Date de fin: " + s.getDateFin() + " \n" + " \n" + "Local: " + s.getIdLocal().getNomLocal());
        }
    }

    public void inputPaiement() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez le code session a valider");
    }

    public void errorDeleteFormateur() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Il n'est pas possible le de supprimer ce formateur car il donne encore des cours");
    }

    public void FormateurNotAvailable() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Il n'y a pas de formateur disponible pour cette date");
    }

    public void menuListCrudLocaux() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 pour créer un nouveau local");
        System.out.println("");
        System.out.println("Tapez 2 pour modifier le nom d'un local");
        System.out.println("");
        System.out.println("Tapez 3 pour supprimer un local");
        System.out.println("");
        System.out.println("Tapez 4 our revenir au meu précédent");
    }

    public void inputNomLocal() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le nom du nouveau local");
    }

    public void errorDoubleLocal() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Ce local existe déjà!");
    }

    public void resulListAllLocal(List<Local> allLocal) {
        for (Local l : allLocal) {
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Code local: " + l.getIdLocal() + " \n" + " \n" + "Nom local: " + l.getNomLocal());
        }
    }

    public void inputIdLocal() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le code du local");
    }

    public void errorSupprime() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Il n'est pas possible de supprimer ce local");
    }

    public void menuListCrudStatut() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 pour créer un nouveau statut");
        System.out.println("");
        System.out.println("Tapez 2 pour modifier le nom d'un statut et son discount");
        System.out.println("");
        System.out.println("Tapez 3 pour revenir au meu précédent");
    }

    public void inputNomStatut() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le nom du statut");
    }

    public void inputdiscountStatut() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Entrez le discount accordé pour ce statut");

    }

    public void errorDoubleStatut() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Ce statut existe déjà!");
    }

    public void inputIdStatut() {
       System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez le code du statut");
    }

}
