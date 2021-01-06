/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.DAO.UserDao;
import java.util.List;
import model.DAO.AbstractDaoFactory;
import model.DAO.Mysql.MySqlUserDao;

/**
 *
 * @author Fabien
 */
public abstract class User {

    private int idUser;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
    private Role role;
    private Statut statut;



    
    
    
    

    public User(int idUser, String nom, String prenom, String adresse, String email, String password, Role role, Statut statut) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = role;
        this.statut = statut;
    }

    public User(int idUser, String nom, String prenom, String adresse, String email, String password, Role role) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    public User(String nom, String prenom, String adresse, String email, String password, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public void registerUser() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao m = factory.createUserDao();
        m.registerUser(this);
    }

    public static User login(String login, String password) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao m = factory.createUserDao();
        return m.login(login, password);
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    public void modificationUser(int idUser, String nom, String prenom, String adresse, String email, String password) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        userDao.modificationUser(idUser, nom, prenom, adresse, email, password);
    }





    public void deletelInscription(int idSession) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        userDao.deletelInscription(this, idSession);
    }

    public void deleteFormation(Formation formation) {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao userDao = factory.createUserDao();
        userDao.deleteFormation(formation);
    }

 



}
