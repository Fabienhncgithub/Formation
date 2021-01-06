/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import model.Inscription;
import model.Stagiaire;

/**
 *
 * @author Fabien
 */
public interface StagiaireDao {

     List<Inscription> getListeInscriptionbyUser(Stagiaire stagiaire);

     void registerUserToSession(Stagiaire stagiaire, int idSession);

  
    
}
