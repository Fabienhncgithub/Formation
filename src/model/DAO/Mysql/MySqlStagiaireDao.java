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
        String sql = "SELECT idInscription,statutPaiement,notificationPaiement,prix WHERE inscription.idUser = ? AND inscription.annule = false";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, stagiaire.getIdUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscription = new Inscription(rs.getInt("idInscription"),rs.getBoolean("statutPaiement"),rs.getBoolean("notificationPaiement"),rs.getDouble("prix"));
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
        

        
        String sql1 = "SELECT idSession FROM inscription WHERE idUser = ?  AND idSession = ? AND annule = 0";
        String sql2 = "SELECT DISTINCT  prix FROM formation JOIN session ON formation.IdFormation = session.IdFormation WHERE session.IdSession = ? ";
        String sql3 = "SELECT discount FROM statut WHERE statut.idStatut = ? ";
        String sql4 = "INSERT INTO inscription(idSession, idUser, statutPaiement, notificationPaiement,prix) VALUES (?, ?,?,?,?)";

        try {
            ps = c.prepareStatement(sql1);
            ps.setInt(1, stagiaire.getIdUser());
            ps.setInt(2, idSession);
            rs = ps.executeQuery();

            if (!rs.next()) {
                result = true;
                
                
                ps = c.prepareStatement(sql2);
                ps.setInt(1,idSession);
                rs = ps.executeQuery();
                rs.next();
                Integer prix = rs.getInt(1);
                
                ps = c.prepareStatement(sql3);
                ps.setInt(1,stagiaire.getStatut().getIdStatut());
                rs = ps.executeQuery();
                rs.next();
                Double discount = rs.getDouble(1);
                
                      float prixTotal = (float)(prix-((prix/100)*discount)); 
                
                ps = c.prepareStatement(sql4);
                ps.setInt(1, idSession);
                ps.setInt(2, stagiaire.getIdUser());
                ps.setInt(3, statutPaiement);
                ps.setInt(4, notificationPaiement);
                ps.setDouble(5, prixTotal);
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
//        String inscriptionExist = " select IdInscription from inscrire where IdUtilisateur = ? and IdSession = ? ";
//        String prix = " SELECT distinct  `Prix` FROM `formation` join session on formation.IdFormation = session.IdFormation where session.IdSession = ? ";
//        String reduction = " select reduction from status where status.idstatus = ? ";
//        String sql = "INSERT INTO `inscrire` (`idUtilisateur`, `idSession`,`prix`) values(?,?,?)";
//
//        try {
//            c = MySqlDaoFactory.getInstance().getConnection();
//            ps = c.prepareStatement(inscriptionExist);
//            ps.setInt(1, stagiaire.getIdUtilisateur());
//            ps.setInt(2, session.getIdSession());
//            rs = ps.executeQuery();
//
//            if (!rs.next()) {
//                ps = c.prepareStatement(prix);
//                ps.setInt(1,session.getIdSession());
//                rs = ps.executeQuery();
//                rs.next();
//                Integer price = rs.getInt(1);
//                
//                ps = c.prepareStatement(reduction);
//                ps.setInt(1,stagiaire.getStatus().getIdStatus());
//                rs = ps.executeQuery();
//                rs.next();
//                Double reduc = rs.getDouble(1);
//                
//                float total = (float)(price-((price/100)*reduc)); 
//                System.out.println(reduc);
//                System.out.println(price);
//                System.out.println(total);
//                ps = c.prepareStatement(sql);
//                ps.setInt(1, stagiaire.getIdUtilisateur());
//                ps.setInt(2, session.getIdSession());
//                ps.setFloat(3, total);
//                ps.executeUpdate();
//                ajoutOK = true;
//            }