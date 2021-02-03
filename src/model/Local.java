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
public class Local {

    private int idLocal;
    private String nomLocal;

    public Local(int idLocal, String nomLocal) {
        this.idLocal = idLocal;
        this.nomLocal = nomLocal;
    }

    public Local() {
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public boolean createLocaux(Local local) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.createLocaux(local);
    }

    public boolean updateLocaux(Local local) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.updateLocaux(local);
    }

    public boolean deleteLocaux(Local local) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.deleteLocaux(local);
    }

}
