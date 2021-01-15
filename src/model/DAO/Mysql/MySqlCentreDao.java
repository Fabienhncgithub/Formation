/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.Mysql;

import model.Admin;
import model.DAO.CentreDao;
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Stagiaire;
import model.Statut;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DAO.Mysql.MySqlDaoFactory;

/**
 * s
 *
 * @author Fabien
 */
public class MySqlCentreDao implements CentreDao {

    private static MySqlCentreDao instance;

    private MySqlCentreDao() {
    }

    public static MySqlCentreDao getInstance() {
        if (instance == null) {
            instance = new MySqlCentreDao();
        }
        return instance;
    }

    @Override
    public List<Statut> getAllStatut() {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Statut> listStatut = new ArrayList<>();

        String sql = "SELECT * FROM statut";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Statut statut = new Statut(rs.getInt("idStatut"), rs.getString("nomStatut"));
                listStatut.add(statut);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlAnnuaireDAO, method getFormationsList(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listStatut;
    }

    @Override
    public List<Formation> getAllFormation() {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Formation> listFormation = new ArrayList<>();

        String sql = "SELECT idFormation, nomFormation, prix, duree, participantMax, participantMin, supprime FROM formation where supprime = 0";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation formation = new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getInt("prix"), rs.getInt("duree"), rs.getInt("participantMin"), rs.getInt("participantMax"), rs.getBoolean("supprime"));
                listFormation.add(formation);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getAllFormation(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listFormation;
    }

    @Override
    public List<Formation> searchFormation(String search) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Formation> listFormation = new ArrayList<>();

        String sql = "SELECT * FROM formation WHERE nomFormation LIKE ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, search);
            rs = ps.executeQuery();

            while (rs.next()) {

                Formation formation = new Formation(rs.getString("nomFormation"), rs.getInt("prix"), rs.getInt("duree"), rs.getInt("participantMin"), rs.getInt("participantMax"));
                listFormation.add(formation);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method searchFormation(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listFormation;
    }

    @Override
    public Session getSessionbyId(int idSession) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Session session = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        String sql = "SELECT session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin, session.supprime,formation.idFormation, formation.nomFormation, formation.prix, formation.duree, formation.participantMax, formation.participantMin, formation.supprime FROM session JOIN formation ON session.idFormation = formation.idFormation where session.idSession = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idSession);
            rs = ps.executeQuery();
            if (rs.next()) {
                session = new Session(
                        rs.getInt("idSession"),
                        new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getDouble("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getInt("participantMin"), rs.getBoolean("supprime")),
                        getFormateurbyId(rs.getInt("idFormateur")),
                        getLocalById(rs.getInt("idLocal")),
                        rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getBoolean("supprime"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, getSessionbyId(int idSession): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return session;
    }

    @Override
    public List<Session> listeSessionbyFormation(Formation formation) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Session> listSession = new ArrayList<>();
        String sql = "SELECT session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin, session.supprime, formation.idFormation, formation.nomFormation, formation.prix, formation.duree, formation.participantMax, formation.participantMin, formation.supprime FROM session JOIN formation ON session.idFormation = formation.idFormation where formation.idFormation = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, formation.getIdFormation());
            rs = ps.executeQuery();
            while (rs.next()) {
                Session session = new Session(rs.getInt("idSession"),
                        new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getDouble("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getInt("participantMin"), rs.getBoolean("supprime")),
                        getFormateurbyId(rs.getInt("idFormateur")),
                        getLocalById(rs.getInt("idLocal")),
                        rs.getDate("dateDebut"),
                        rs.getDate("dateFin"),
                        rs.getBoolean("supprime"));
                listSession.add(session);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method listeSessionbyFormation(Formation formation): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listSession;
    }

    @Override
    public Formation getFormationyId(int idFormation) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Formation formation = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idFormation, nomFormation, prix, duree, participantMax, participantMin, supprime  FROM formation where idFormation = ? AND supprime = 0";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormation);
            rs = ps.executeQuery();
            if (rs.next()) {
                formation = new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getDouble("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getInt("participantMin"), rs.getBoolean("supprime"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getFormationyId(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return formation;
    }

    @Override
    public User getUserbyId(int idUser) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User u = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idUser, nom, prenom, adresse, email, password, role, statut FROM user where idUser = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                int role = rs.getInt("role");
                if (role == 1) {
                    u = new Stagiaire(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            getRoleById(rs.getInt("role")),
                            getStatutById(rs.getInt("statut")));

                } else if (role == 2) {
                    u = new Admin(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            getRoleById(rs.getInt("role")));

                } else if (role == 3) {
                    u = new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            getRoleById(rs.getInt("role")));

                } else {
                    System.out.println("no role");
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getUserbyId(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return u;
    }

    @Override
    public Local getLocalById(int idLocal) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Local local = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT * FROM local where idLocal = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idLocal);
            rs = ps.executeQuery();
            if (rs.next()) {
                local = new Local(rs.getInt("idLocal"), rs.getString("nomLocal"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getLocalById(int idLocal)): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return local;
    }

    @Override
    public Formateur getFormateurbyId(int idFormateur) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Formateur u = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT * FROM user WHERE idUser = ? AND role = 3";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormateur);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                        getRoleById(rs.getInt("role")));

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getFormateurbyId(int idFormateur) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return u;
    }

    @Override
    public Statut getStatutById(int idStatut) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Statut statut = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT * FROM statut where idStatut = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idStatut);
            rs = ps.executeQuery();
            if (rs.next()) {
                statut = new Statut(rs.getInt("idStatut"), rs.getString("nomStatut"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getStatutById): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return statut;
    }

    @Override
    public Role getRoleById(int idRole) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Role role = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT * FROM role where idRole = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idRole);
            rs = ps.executeQuery();
            if (rs.next()) {
                role = new Role(rs.getInt("idRole"), rs.getString("nomRole"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getRoleById(int idRole)): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return role;
    }

    @Override
    public void createNewFormation(Formation formation) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "INSERT INTO formation(nomFormation, prix, duree, participantMax, participantMin) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, formation.getNomFormation());
            ps.setDouble(2, formation.getPrix());
            ps.setInt(3, formation.getDuree());
            ps.setInt(4, formation.getParticipantMax());
            ps.setInt(5, formation.getParticipantMin());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method createNewFormation(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public void updateFormation(Formation formation) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE formation SET nomFormation = ? ,prix=? ,duree=? ,participantMax=?, participantMin=? where idFormation = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, formation.getNomFormation());
            ps.setDouble(2, formation.getPrix());
            ps.setInt(3, formation.getDuree());
            ps.setInt(4, formation.getParticipantMax());
            ps.setInt(5, formation.getParticipantMin());
            ps.setInt(6, formation.getIdFormation());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method createNewFormation(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

    }

    @Override
    public List<Formateur> getAllFormateur() {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Formateur formateur = null;
        List<Formateur> listFormateur = new ArrayList<>();
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT * FROM user";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int role = rs.getInt("role");
                if (role == 3) {
                    formateur = new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            new Role(rs.getInt("role"), rs.getString("role")));
                    listFormateur.add(formateur);
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getRoleById(int idRole)): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listFormateur;
    }

    @Override
    public List<Inscription> listeInscriptionbySession(Session session) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Inscription inscription = null;
        List<Inscription> listInscription = new ArrayList<>();
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT * FROM Inscription WHERE idSession = ? ";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                inscription = new Inscription();
                listInscription.add(inscription);

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getRoleById(int idRole)): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listInscription;
    }

    @Override
    public List<Session> listeInformationsByFormateurs() {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        List<Session> listInformationsByFormateur = new ArrayList<>();
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin, session.supprime,formation.idFormation, formation.nomFormation, formation.prix, formation.duree, formation.participantMax, formation.participantMin, formation.supprime,user.idUser, user.nom, user.prenom, user.adresse, user.email, user.`password`, user.role, user.statut, role.idRole, role.nomRole, statut.idStatut, statut.nomStatut, local.idLocal, local.nomLocal  FROM session JOIN formation ON session.idFormation = formation.idFormation JOIN user ON user.idUser = session.idFormateur JOIN role ON role.idRole = user.role JOIN statut on statut.idStatut = user.statut JOIN local ON local.idLocal = session.idLocal";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Session session = new Session(rs.getInt("idSession"),
                        new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getDouble("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getInt("participantMin"), rs.getBoolean("supprime")),
                        new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                                new Role(rs.getInt("idRole"), rs.getString("nomRole"))),
                        new Local(rs.getInt("idLocal"), rs.getString("nomLocal")),
                        rs.getDate("dateDebut"),
                        rs.getDate("dateFin"),
                        rs.getBoolean("supprime"));
                listInformationsByFormateur.add(session);

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getRoleById(int idRole)): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listInformationsByFormateur;

    }

    @Override
    public boolean CreateNewSession(Session session) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql1 = "SELECT idSession, idFormation, idformateur, idLocal, dateDebut, dateFin, supprime FROM session WHERE idFormateur = ? AND ? NOT BETWEEN dateDebut AND dateFin AND ? NOT BETWEEN dateDebut AND dateFin";

        String sql2 = "INSERT INTO formation(nomFormation, prix, duree, participantMax, participantMin) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql2);
            // ps.setInt(1, formation.get);

            ps = c.prepareStatement(sql2);
            ps.setString(1, session.getFormation().getNomFormation());
            ps.setDouble(2, session.getFormation().getPrix());
            ps.setInt(3, session.getFormation().getDuree());
            ps.setInt(4, session.getFormation().getParticipantMax());
            ps.setInt(5, session.getFormation().getParticipantMin());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method createNewFormation(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

        return result;

    }

    @Override
    public List<Local> getAllLocal() {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Local> listLocal = new ArrayList<>();
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idLocal, nomLocal from Local";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Local local = new Local(rs.getInt("idLocal"),rs.getString("nomLocal"));
                listLocal.add(local);

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Local> getAllLocal()): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listLocal;
    }

}
