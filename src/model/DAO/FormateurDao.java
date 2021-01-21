/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.Formateur;

/**
 *
 * @author Fabien
 */
public interface FormateurDao {

     void registerFormateur(Formateur formateur);

     void updateFormateur(Formateur formateur);

     boolean deleteFormateur(Formateur aThis);

 
    
}
