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
public class Facade {


    private Centre centre;
    private Formation formation;
    private Session session;
    private User user;
    private Admin admin;
    private Stagiaire stagiaire;
    private Formateur formateur;
    private Role role;
    private Statut statut;
    private Inscription inscription;
    private Local local;

    public Facade() {
        this.centre = new Centre();
        this.formation = new Formation();
        this.session = new Session();
        this.admin = new Admin();
        this.stagiaire = new Stagiaire();
        this.formateur = new Formateur();
        this.role = new Role();
        this.statut = new Statut();
        this.inscription = new Inscription();
        this.local = new Local();
    }

 

    public Centre getCentre() {
        return centre;
    }

    public Formation getFormation() {
        return formation;
    }

    public Session getSession() {
        return session;
    }

    public User getUser() {
        return user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public void setInscription(Inscription inscription) {
        this.inscription = inscription;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

 
    
    

}
