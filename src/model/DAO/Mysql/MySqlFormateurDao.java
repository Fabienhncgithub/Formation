/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.Mysql;

import model.DAO.Mysql.MySqlDaoFactory;
import model.DAO.FormateurDao;
import model.Formateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Fabien
 */
public class MySqlFormateurDao implements FormateurDao {

    private static MySqlFormateurDao instance;

    private MySqlFormateurDao() {
    }

    public static MySqlFormateurDao getInstance() {
        if (instance == null) {
            instance = new MySqlFormateurDao();
        }
        return instance;
    }

    @Override
    public void registerFormateur(Formateur formateur) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "INSERT INTO user(nom, prenom, adresse, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, formateur.getNom());
            ps.setString(2, formateur.getPrenom());
            ps.setString(3, formateur.getAdresse());
            ps.setString(4, formateur.getEmail());
            ps.setString(5, formateur.getPassword());
            ps.setInt(6, formateur.getRole().getIdRole());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method registerFormateur(Formateur formateur): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public void updateFormateur(Formateur formateur) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE user SET nom = ? ,prenom=? ,adresse=? ,email=? , password=? where idUser = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, formateur.getNom());
            ps.setString(2, formateur.getPrenom());
            ps.setString(3, formateur.getAdresse());
            ps.setString(4, formateur.getEmail());
            ps.setString(5, formateur.getPassword());
            ps.setInt(6, formateur.getIdUser());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method updateFormateur(Formateur formateur): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public void deleteFormateur(Formateur formateur) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
       
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE user SET supprime = 1 where idUser = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, formateur.getIdUser());
            ps.executeUpdate();
            
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method deleteFormateur(Formateur formateur): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

}
