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

    public List<Session> listeSessionbyFormation(Formation formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.listeSessionbyFormation(formation);

    }

    public List<Session> listeInformationsByFormateurs() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.listeInformationsByFormateurs();
    }

    public boolean CreateNewSession(Session session, Formation formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.CreateNewSession(session, formation);

    }

    public List<Local> getAllLocal() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getAllLocal();

    }

    public List<Formateur> getAllFormateurAvailable(Session session) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getAllFormateurAvailable(session);
    }

    public boolean validationPaiement(int sessionId, User user) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.validationPaiement(sessionId, user);
    }

    public List<Inscription> getInscritpionPaiementNotification() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getInscritpionPaiementNotification();
    }

    public void validationStatutPaiment(int inscriptionId) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        centreDao.validationStatutPaiment(inscriptionId);
    }

    public List<Stagiaire> resultListStagiaireBySession(int sessionId) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.resultListStagiaireBySession(sessionId);

    }

    public void cleanDb() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        centreDao.cleanDb();
    }

    public User getUserByEmail(String login) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getUserByEmail(login);

    }

    public Formateur getFormateurBySession(int idSession) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getFormateurBySession(idSession);
    }

    public List<Session> listeSessionByIdFormateur(int idFormateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.listeSessionByIdFormateur(idFormateur);
    }

    public List<Formateur> getFormateurAvailable(Session session, Formation formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getFormateurAvailable(session, formation);
    }

    public Inscription getInscriptionbyId(int inscriptionId) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getInscriptionbyId(inscriptionId);
    }

    public List<Local> getLocalAvailable(Session session, Formation formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getLocalAvailable(session, formation);
    }

    public void UpdateSession(Session session, Formation formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        centreDao.UpdateSession(session, formation);
    }

    public boolean addFormationToFormateur(int idFormation, int idFormateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.addFormationToFormateur(idFormation, idFormateur);
    }

    public List<Formation> getFormationByFormateur(int idFormateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getFormationByFormateur(idFormateur);
    }

    public boolean deleteFormationToFormateur(int idFormation, int idFormateur) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.deleteFormationToFormateur(idFormation, idFormateur);
    }

    public Formation getFormationByNom(String nomFormation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getFormationByNom(nomFormation);
    }

    public Local getLocalByNom(String nomLocal) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        return centreDao.getLocalByNom(nomLocal);
    }

    public Object getInscriptionbyIdUser(int inscriptionId, User user) {
     AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
    return centreDao.getInscriptionbyIdUser(inscriptionId, user);
    }
}
