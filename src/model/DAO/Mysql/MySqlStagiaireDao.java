/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DAO.StagiaireDao;
import model.Inscription;
import model.Stagiaire;

/**
 *
 * @author Fabien
 */
public class MySqlStagiaireDao implements StagiaireDao {

    private static MySqlStagiaireDao instance;

    private MySqlStagiaireDao() {
    }

    public static MySqlStagiaireDao getInstance() {
        if (instance == null) {
            instance = new MySqlStagiaireDao();
        }
        return instance;
    }

    @Override
    public List<Inscription> getListeInscriptionbyUser(Stagiaire stagiaire) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Inscription> listInscription = new ArrayList<>();
        String sql = "SELECT * FROM inscription WHERE idUser =?";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, stagiaire.getIdUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscription = new Inscription(
                        MySqlCentreDao.getInstance().getUserbyId(rs.getInt("idUser")),
                        rs.getInt("statutPaiement"),
                        rs.getInt("notificationPaiement")
                );
                listInscription.add(inscription);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method listeInscription(int idUser): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listInscription;
    }

    @Override
    public void registerUserToSession(Stagiaire stagiaire, int idSession) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        int statutPaiement = 0;
        int notificationPaiement = 0;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "INSERT INTO inscription(idSession, idUser, statutPaiement, notificationPaiement) VALUES (?, ?,?,?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idSession);
            ps.setInt(2, stagiaire.getIdUser());
            ps.setInt(3, statutPaiement);
            ps.setInt(4, notificationPaiement);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method registerUserToSession(Stagiaire stagiaire, int idSession): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

    }
}
