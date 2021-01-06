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

public interface CentreDao {

    List<Statut> getAllStatut();

    List<Formation> getAllFormation();

    List<Formation> searchFormation(String search);



    Formation getFormationyId(int idFormation);

    Session getSessionbyId(int idSession);

    User getUserbyId(int idUser);

    Formateur getFormateurbyId(int idFormateur);

    Local getLocalById(int idLocal);

    Statut getStatutById(int idStatut);

    Role getRoleById(int idRole);

    void createNewFormation(Formation formation);

    void updateFormation(Formation formation);

    List<Formateur> getAllFormateur();

     List<Inscription> listeInscriptionbySession(Session session);

     List<Session> listeSessionbyFormation(Formation formation);






 



}
