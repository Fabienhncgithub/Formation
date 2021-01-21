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

    public List<Formation> searchFormation(String search);

    public Formation getFormationyId(int idFormation);

    public Session getSessionbyId(int idSession);

    public User getUserbyId(int idUser);

    public Formateur getFormateurbyId(int idFormateur);

    public Local getLocalById(int idLocal);

    public Statut getStatutById(int idStatut);

    public Role getRoleById(int idRole);

    public void createNewFormation(Formation formation);

    public void updateFormation(Formation formation);

    public List<Formateur> getAllFormateur();

    public List<Inscription> listeInscriptionbySession(Session session);

    public List<Session> listeSessionbyFormation(Formation formation);

    public List<Session> listeInformationsByFormateurs();

    public boolean CreateNewSession(Session session, Formation formation);

    public List<Local> getAllLocal();

    public List<Formateur> getAllFormateurAvailable(Session session);

    public boolean validationPaiement(int sessionId, User user);

    public List<Inscription> getInscritpionPaiementNotification();

    public void validationStatutPaiment(int inscriptionId);

    public List<Stagiaire> resultListStagiaireBySession(int sessionId);

    public void cleanDb();

    public User getUserByEmail(String email);

    public Formateur getFormateurBySession(int idSession);

    public List<Session> listeSessionByIdFormateur(int idFormateur);

    public List<Formateur> getFormateurAvailable(Session session, Formation formation);

    public Inscription getInscriptionbyId(int inscriptionId);



}
