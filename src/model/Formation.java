/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DAO.CentreDao;
import java.util.List;
import model.DAO.AbstractDaoFactory;

/**
 *
 * @author Fabien
 */
public class Formation {

    private int idFormation;
    private String nomFormation;
    private double prix;
    private int duree;
    private int participantMax;

    private boolean supprime;
    private List<Session> listeSession;

    
    /*TOTO SUPPRIME BOOLEAN SUPPRIME*/
    public Formation(int idFormation, String nomFormation, double prix, int duree, int participantMax, boolean supprime) {
        this.idFormation = idFormation;
        this.nomFormation = nomFormation;
        this.prix = prix;
        this.duree = duree;
        this.participantMax = participantMax;
    }

    public Formation(String nomFormation, double prix, int duree, int participantMax) {
        this.nomFormation = nomFormation;
        this.prix = prix;
        this.duree = duree;
        this.participantMax = participantMax;
    }

    public Formation() {
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getParticipantMax() {
        return participantMax;
    }

    public void setParticipantMax(int participantMax) {
        this.participantMax = participantMax;
    }



    public List<Session> getListeSession() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listeSession = centreDao.listeSessionbyFormation(this);
        return listeSession;
    }

    public void setListeSession(List<Session> listeSession) {
        this.listeSession = listeSession;
    }

    public boolean isSupprime() {
        return supprime;
    }

    public void setSupprime(boolean supprime) {
        this.supprime = supprime;
    }

    public List<Session> listeSessionbyFormation() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listeSession = centreDao.listeSessionbyFormation(this);
        return listeSession;
    }

}
