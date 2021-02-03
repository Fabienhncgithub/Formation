package model.DAO.Mysql;

import model.Admin;
import model.DAO.UserDao;
import model.Formateur;
import model.Formation;
import model.Role;
import model.Stagiaire;
import model.Statut;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.DAO.Mysql.MySqlDaoFactory;

public class MySqlUserDao implements UserDao {

    private static MySqlUserDao instance;

    private MySqlUserDao() {
    }

    public static MySqlUserDao getInstance() {
        if (instance == null) {
            instance = new MySqlUserDao();
        }
        return instance;
    }

    @Override
    public void registerUser(User user) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "INSERT INTO user(nom, prenom, adresse, email, password, role, statut) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, user.getNom());
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getAdresse());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole().getIdRole());
            ps.setInt(7, user.getStatut().getIdStatut());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method insertUser(User u): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public User login(String email) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User u = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT u.idUser, u.nom, u.prenom, u.adresse, u.email, u.password, u.statut, u.role, u.supprime,r.idRole, r.nomRole, s.idStatut, s.nomStatut, s.discount from user u, role r, statut s WHERE u.role =  r.idRole AND u.email = ? limit 1";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                int role = rs.getInt("role");
                if (role == 1) {
                    u = new Stagiaire(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            new Role(rs.getInt("role"), rs.getString("role")),
                            new Statut(rs.getInt("statut"), rs.getString("statut"), rs.getDouble("discount")));

                } else if (role == 2) {
                    u = new Admin(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            new Role(rs.getInt("role"), rs.getString("role")));
                } else if (role == 3) {
                    u = new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            new Role(rs.getInt("role"), rs.getString("role")), rs.getBoolean("supprime"));
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method login(String email, String password): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return u;
    }

    @Override
    public void modificationUser(int id, String nom, String prenom, String adresse, String email, String password) {
        Connection c;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE user SET nom = ? ,prenom=? ,adresse=? ,email=? , password=? where idUser = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, adresse);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setInt(6, id);
            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("MySqlUserDAO, method modificationUser(int id, String nom, String prenom, String adresse, String email, String password: \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public boolean deletelInscription(User user, int idSession) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;

        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT inscription.annule, inscription.idSession, inscription.idUser FROM session JOIN inscription ON session.idSession = inscription.idSession WHERE inscription.idSession = ? AND inscription.idUser = ?";
        String sql2 = "UPDATE inscription SET annule = 1 where inscription.idSession = ? and inscription.idUser = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idSession);
            ps.setInt(2, user.getIdUser());
            rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
                ps = c.prepareStatement(sql2);
                ps.setInt(1, idSession);
                ps.setInt(2, user.getIdUser());
                ps.executeUpdate();

                ps = c.prepareStatement(sql2);
                ps.setInt(1, idSession);
                ps.executeUpdate();

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method deletelInscription(User user, int idSession): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public boolean deleteFormation(Formation formation) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idFormation FROM formation WHERE idFormation = ? AND idFormation NOT IN(SELECT idFormation FROM session)";

        String sql1 = "SELECT idSession FROM `session` as sess WHERE `idFormation` = ? "
                + " AND DATEDIFF(sess.dateDebut,CURRENT_DATE)>0 AND  sess.IdSession not in(select idSession from inscription) ";

        String sql2 = "UPDATE formation SET supprime = 1 WHERE idFormation = ? ";

        // String sql3 = "UPDATE session SET supprime = 1 WHERE session.idSession = ? ";
        try {

            ps = c.prepareStatement(sql);
            ps.setInt(1, formation.getIdFormation());
            rs = ps.executeQuery();

            if (rs.next()) {
                ps = c.prepareStatement(sql2);
                ps.setInt(1, formation.getIdFormation());
                ps.executeUpdate();
                 result = true;

            } else {

                ps = c.prepareStatement(sql1);
                ps.setInt(1, formation.getIdFormation());
                rs = ps.executeQuery();

                while (rs.next()) {
                    result = true;

//                     int idSession = rs.getInt(1);
//                    ps = c.prepareStatement(sql3);
//                    ps.setInt(1, idSession);
//                    ps.executeUpdate();

                    ps = c.prepareStatement(sql2);
                    ps.setInt(1, formation.getIdFormation());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method deleteFormation(Formation formation): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

        return result;
    }

}
