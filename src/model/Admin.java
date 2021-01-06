/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fabien
 */
public class Admin extends User{

    public Admin(int idUser, String nom, String prenom, String adresse, String email, String password, Role role) {
        super(idUser, nom, prenom, adresse, email, password, role);
    }
    

    public Admin(String nom, String prenom, String adresse, String email, String password, Role role) {
        super(nom, prenom, adresse, email, password, role);
    }

    public Admin(String email, String password) {
        super(email, password);
    }

    public Admin() {
    }

  
    
    
}
