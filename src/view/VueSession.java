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
import model.Inscription;
import model.Local;
import model.Stagiaire;

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
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        if (listSession.size() == 0) {
            System.out.println("Il n'y a pas encore de session");
        } else {
            for (Session s : listSession) {

                System.out.println("Code Session: " + s.getIdSession()
                        + " \n" + " \n" + "Formateur: " + s.getIdformateur().getNom()
                        + " \n" + " \n" + "Local: " + s.getIdLocal().getNomLocal()
                        + " \n" + " \n" + "date de début: " + s.getDateDebut()
                        + " \n" + " \n" + "date de fin: " + s.getDateFin());
            }
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

    public void menuListStagiaireBySession() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 pour encoder directement le code de la  session");
        System.out.println("");
        System.out.println("Tapez 2 pour rechercher la session d'une formation");
        System.out.println("");
        System.out.println("Tapez 3 pour revenir au menu précédent");
    }

    public void resulListStagiaireBySession(List<Stagiaire> resultListStagiaireBySession) {
        if (resultListStagiaireBySession.size() == 0) {
            System.out.println("Il n'y a pas de stagiaire inscrit pour cette session");
        } else {
            for (Stagiaire i : resultListStagiaireBySession) {
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.println("");

                System.out.println("Nom Stagiaire: " + i.getNom() + " \n" + " \n" + "Prenom Stagiaire: " + i.getPrenom() + " \n" + " \n" + "Statut: " + i.getStatut().getNomStatut());
            }
        }
    }

    public void formateurBysession(Formateur formateurBySession) {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Formateur : " + formateurBySession.getNom() + "  " + formateurBySession.getPrenom());

    }

}
