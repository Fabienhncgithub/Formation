/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Statut;
import model.User;
import java.util.List;
import model.Stagiaire;

public interface CentreDao {

    public List<Statut> getAllStatut();

    public List<Formation> getAllFormation();

    public List<Formateur> getAllFormateur();

    public List<Local> getAllLocal();

    public List<Formateur> getAllFormateurAvailable(Session session);

    public List<Formation> searchFormation(String search);

    public Formation getFormationyId(int idFormation);

    public Session getSessionbyId(int idSession);

    public User getUserbyId(int idUser);

    public Formateur getFormateurbyId(int idFormateur);

    public Local getLocalById(int idLocal);

    public Statut getStatutById(int idStatut);

    public Role getRoleById(int idRole);

    public Inscription getInscriptionbyId(int inscriptionId);

    public List<Inscription> getInscritpionPaiementNotification();

    public List<Inscription> listeInscriptionbySession(Session session);

    public boolean CreateNewSession(Session session, Formation formation);

    public void UpdateSession(Session session, Formation formation);

    public List<Session> listeSessionbyFormation(Formation formation);

    public List<Session> listeInformationsByFormateurs();

    public List<Session> listeSessionByIdFormateur(int idFormateur);

    public boolean validationPaiement(int sessionId, User user);

    public void validationStatutPaiment(int inscriptionId);

    public List<Stagiaire> resultListStagiaireBySession(int sessionId);

    public void cleanDb();

    public User getUserByEmail(String email);

    public Formateur getFormateurBySession(int idSession);

    public List<Formateur> getFormateurAvailable(Session session, Formation formation);

    public List<Local> getLocalAvailable(Session session, Formation formation);

    public void createNewFormation(Formation formation);

    public void updateFormation(Formation formation);

    public boolean createLocaux(Local local);

    public boolean updateLocaux(Local local);

    public boolean deleteLocaux(Local local);

    public boolean createStatut(Statut statut);

    public boolean updateStatut(Statut statut);

}
