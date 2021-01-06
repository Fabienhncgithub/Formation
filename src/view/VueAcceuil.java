/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Formation;
import model.Statut;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Fabien
 */
public class VueAcceuil {

    public void choices() {
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("Tapez 1 Voir les formations");
        System.out.println("");
        System.out.println("Tapez 2 Se connecter");
        System.out.println("");
        System.out.println("Tapez 3 S'inscrire");
        System.out.println("");
        System.out.println("Tapez 4 Quitter");
    }

    public void error() {
        System.out.println("Erreur! ce choix n'éxiste pas");
        System.out.println("");
        choices();
    }

    public void newUserName() {
        System.out.println("entrer votre nom");

    }

    public void newUserStatut() {
        System.out.println("Entrez votre statut");
    }

    public void login() {
        System.out.println("Entrez votre Login");
    }

    public void newUserNom() {
        System.out.println("Entrez votre nom");
    }

    public void newUserPrenom() {
        System.out.println("Entrez votre prenom");
    }

    public void newUseradresse() {
        System.out.println("Entrez votre adresse");
    }

    public void newUseremail() {
        System.out.println("Entrez votre email");
    }

    public void newUserPassword() {
        System.out.println("Entrez votre mot de passe");
    }

    public void resultListStatut(List<Statut> listStatut) {
        Iterator<Statut> statutsitr = listStatut.iterator();
        while (statutsitr.hasNext()) {
            Statut s = statutsitr.next();
            System.out.println(s.getIdStatut() + " - " + s.getNomStatut());
        }
    }

    public void success() {
        System.out.println("");
        System.out.println("Opération(s) efffectuée(s) avec succès");
    }

    public void exit() {
        System.out.println("Tapez 0 pour quitter");
    }

}
