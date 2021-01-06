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
    private int participantMin = 0;
    private List<Session> listeSession;

    public Formation(int idFormation, String nomFormation, double prix, int duree, int participantMax, int participantMin) {
        this.idFormation = idFormation;
        this.nomFormation = nomFormation;
        this.prix = prix;
        this.duree = duree;
        this.participantMax = participantMax;
        this.participantMin = participantMin;
    }

    public Formation(String nomFormation, double prix, int duree, int participantMax, int participantMin) {
        this.nomFormation = nomFormation;
        this.prix = prix;
        this.duree = duree;
        this.participantMax = participantMax;
        this.participantMin = participantMin;
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

    public int getParticipantMin() {
        return participantMin;
    }

    public void setParticipantMin(int participantMin) {
        this.participantMin = participantMin;
    }

    public List<Session> listeSessionbyFormation() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listeSession = centreDao.listeSessionbyFormation(this);
        return listeSession;
    }

}
