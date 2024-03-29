/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DAO.AbstractDaoFactory;
import model.DAO.CentreDao;

/**
 *
 * @author Fabien
 */
public class Statut {
    
    private int idStatut;
    private String nomStatut;
    private double discount;
    
    

    public Statut(int idStatut, String nomStatut, double discount) {
        this.idStatut = idStatut;
        this.nomStatut = nomStatut;
        this.discount = discount;
    }

    public Statut() {
    }

    public int getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
      public boolean createStatut(Statut statut) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.createStatut(statut);
    }

    public boolean updateStatut(Statut statut) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.updateStatut(statut);
    }
    
}
