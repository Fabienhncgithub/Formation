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
public class Statut {
    
    private int idStatut;
    private String nomStatut;
    
    

    public Statut(int idStatut, String nomStatut) {
        this.idStatut = idStatut;
        this.nomStatut = nomStatut;
    }

    public Statut() {
    }

    public int getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }
    
}
