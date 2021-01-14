/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DAO.CentreDao;
import model.DAO.AbstractDaoFactory;
import java.util.List;


/**
 *
 * @author Fabien
 */
public class Centre {

    public Centre() {
    }

    public List<Statut> getAllStatut() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDAO = factory.createCentreDao();
        return centreDAO.getAllStatut();
    }

    public List<Formation> getAllFormation() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDAO = factory.createCentreDao();
        return centreDAO.getAllFormation();
    }

    public List<Formation> searchFormation(String search) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDAO = factory.createCentreDao();
        return centreDAO.searchFormation(search);
    }

    public Session getSessionbyId(int idSession) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getSessionbyId(idSession);
    }

    public Formation getFormationbyId(int idFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getFormationyId(idFormation);
    }

    public User getUserbyId(int idUser) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getUserbyId(idUser);
    }

    public Formateur getFormateurbyId(int idFormateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getFormateurbyId(idFormateur);
    }

    public Local getLocalById(int idLocal) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getLocalById(idLocal);
    }

    public Statut getStatutById(int idStatut) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getStatutById(idStatut);
    }

    public Role getRoleById(int idRole) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getRoleById(idRole);
    }

    public void createNewFormation(Formation formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        centreDao.createNewFormation(formation);
    }

    public void updateFormation(Formation formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        centreDao.updateFormation(formation);
    }

    public List<Formateur> getAllFormateur() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getAllFormateur();
    }
    
    
    public List<Session>listeSessionbyFormation(Formation formation){
       AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.listeSessionbyFormation(formation);
    
    }

   

 

 

}