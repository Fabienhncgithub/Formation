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
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Stagiaire;
import model.Statut;

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
        String sql = "SELECT session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin, formation.idFormation, formation.nomFormation, formation.prix, formation.duree, formation.participantMax, formation.participantMin, user.idUser, user.nom, user.prenom, user.adresse, user.email, user.`password`, user.role, user.statut, role.idRole, role.nomRole, statut.idStatut, statut.nomStatut, local.idLocal, local.nomLocal, inscription.statutPaiement, inscription.notificationPaiement FROM session JOIN formation ON session.idFormation = formation.idFormation JOIN inscription ON session.idSession  = inscription.idSession JOIN user ON user.idUser = inscription.idUser JOIN role ON role.idRole = user.role JOIN statut on statut.idStatut = user.statut JOIN local ON local.idLocal = session.idLocal WHERE inscription.idUser = ?";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, stagiaire.getIdUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscription = new Inscription(
                        new Session(rs.getInt("idSession"),
                        new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getDouble("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getInt("participantMin")),
                        
                        new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                        new Role(rs.getInt("idRole"), rs.getString("nomRole"))),
                        new Local(rs.getInt("idLocal"),rs.getString("nomLocal")),
                        rs.getDate("dateDebut"),
                        rs.getDate("dateFin")),
                         new Stagiaire(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                         new Role(rs.getInt("idRole"), rs.getString("nomRole")),
                         new Statut(rs.getInt("idStatut"), rs.getString("nomStatut"))),
                        rs.getInt("statutPaiement"),
                        rs.getInt("notificationPaiement"));
                listInscription.add(inscription);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlStagiaireDAO, method getListeInscriptionbyUser(Stagiaire stagiaire): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listInscription;
    }

    @Override
    public boolean registerUserToSession(Stagiaire stagiaire, int idSession) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean result = false;
        int statutPaiement = 0;
        int notificationPaiement = 0;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idSession FROM inscription WHERE idUser = ?  AND idSession = ?";
        String sql2 = "INSERT INTO inscription(idSession, idUser, statutPaiement, notificationPaiement) VALUES (?, ?,?,?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, stagiaire.getIdUser());
            ps.setInt(2, idSession);
            rs = ps.executeQuery();
            
            if(!rs.next()){
            result = true;
            ps = c.prepareStatement(sql2);
            ps.setInt(1, idSession);
            ps.setInt(2, stagiaire.getIdUser());
            ps.setInt(3, statutPaiement);
            ps.setInt(4, notificationPaiement);
            ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method registerUserToSession(Stagiaire stagiaire, int idSession): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;

    }
}
