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
public class Local {
    private int idLocal;
    private String nomLocal;

    public Local(int idLocal, String nomLocal) {
        this.idLocal = idLocal;
        this.nomLocal = nomLocal;
    }

    public Local() {
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }
    
    
    
    
    
    
}
