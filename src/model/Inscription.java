/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabien
 */
public class Inscription {
    private int idInscription;
    private boolean statutPaiement;
    private boolean notificationPaiement;
    private double prix;
    private boolean annule;
   

    public Inscription(int idInscription, boolean statutPaiement, boolean notificationPaiement, double prix) {
        this.idInscription = idInscription;
        this.statutPaiement = statutPaiement;
        this.notificationPaiement = notificationPaiement;
        this.prix = prix;
        this.annule = annule;
    }

    /*TODO public inscription(int idInscription, boolean est payé, boolean notification paiement, double prix, boolean annule)*/
    
    

    public Inscription() {
    }

    public int getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
    }

    public boolean getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(boolean statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public boolean getNotificationPaiement() {
        return notificationPaiement;
    }

    public void setNotificationPaiement(boolean notificationPaiement) {
        this.notificationPaiement = notificationPaiement;
    }



    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    

    public boolean getAnnule() {
        return annule;
    }

    public void setAnnule(boolean annule) {
        this.annule = annule;
    }
    
    
    
    
}
