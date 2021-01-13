/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.DAO.AbstractDaoFactory;
import model.DAO.StagiaireDao;

public class Stagiaire extends User {

    private List<Inscription> listeInscription;

    public Stagiaire(int idUser, String nom, String prenom, String adresse, String email, String password, Role role, Statut statut) {
        super(idUser, nom, prenom, adresse, email, password, role, statut);
    }

    public Stagiaire(String email, String password) {
        super(email, password);
    }

    public Stagiaire() {
    }

    public List<Inscription> getListeInscription() {
        return listeInscription;
    }

    public void setListeInscription(List<Inscription> listeInscription) {
        this.listeInscription = listeInscription;
    }

    public List<Inscription> getListeInscriptionbyUser() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
        listeInscription = stagiaireDao.getListeInscriptionbyUser(this);
        return listeInscription;
    }

    public boolean registerUserToSession(int idSession) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        StagiaireDao stagiaireDao = factory.createStagiaireDao();
       return stagiaireDao.registerUserToSession(this, idSession);
    }

}
