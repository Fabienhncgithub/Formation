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
    private Session idSession;
    private User idUser;
    private int statutPaiement;
    private int notificationPaiement;
    private boolean annule;

    public Inscription(Session idSession, User idUser, int statutPaiement, int notificationPaiement, boolean annule) {
        this.idSession = idSession;
        this.idUser = idUser;
        this.statutPaiement = statutPaiement;
        this.notificationPaiement = notificationPaiement;
        this.annule = annule;
    }


    public Inscription() {
    }



    public Session getIdSession() {
        return idSession;
    }

    public void setIdSession(Session idSession) {
        this.idSession = idSession;
    }

    
    

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }


    public int getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(int statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    public int getNotificationPaiement() {
        return notificationPaiement;
    }

    public void setNotificationPaiement(int notificationPaiement) {
        this.notificationPaiement = notificationPaiement;
    }

    public boolean getAnnule() {
        return annule;
    }

    public void setAnnule(boolean annule) {
        this.annule = annule;
    }
    
    
    
    
}
