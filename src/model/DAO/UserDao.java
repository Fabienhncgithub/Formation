/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.Formation;
import model.Inscription;
import model.User;
import java.util.List;

/**
 *
 * @author Fabien
 */
public interface UserDao {

    void registerUser(User user);

    User login(String login, String password);

    void modificationUser(int idUser, String nom, String prenom, String adresse, String email, String password);




    public boolean deletelInscription(User user, int idSession);

    void deleteFormation(Formation formation);




}
