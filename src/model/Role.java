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
public class Role {
    private int idRole;
    private String nomRole;

    public Role(int idRole, String nomRole) {
        this.idRole = idRole;
        this.nomRole = nomRole;
    }
    
    public Role(){}

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
    
    
    
    
}
