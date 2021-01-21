/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DAO.FormateurDao;
import model.DAO.AbstractDaoFactory;


/**
 *
 * @author Fabien
 */
public class Formateur extends User {
    private boolean supprime;

    public Formateur(int idUser, String nom, String prenom, String adresse, String email, String password, Role role, boolean supprime) {
        super(idUser, nom, prenom, adresse, email, password, role);
        this.supprime = supprime;
    }

 

    public Formateur() {
    }

    public void registerFormateur() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FormateurDao formateurDao = factory.createFormateurDao();
        formateurDao.registerFormateur(this);
    }

    public void updateFormateur() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FormateurDao formateurDao = factory.createFormateurDao();
        formateurDao.updateFormateur(this);
    }

    public boolean deleteFormateur() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FormateurDao formateurDao = factory.createFormateurDao();
        return formateurDao.deleteFormateur(this);
    }

}
