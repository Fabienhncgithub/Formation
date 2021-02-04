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
                Statut statut = new Statut(rs.getInt("idStatut"), rs.getString("nomStatut"), rs.getDouble("discount"));
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

        String sql = "SELECT idFormation, nomFormation, prix, duree, participantMax, supprime FROM formation where supprime = 0";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation formation = new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getInt("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getBoolean("supprime"));
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

        String sql = "SELECT idFormation, nomFormation, prix, duree, participantMax  FROM formation WHERE nomFormation LIKE ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation formation = new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getInt("prix"), rs.getInt("duree"), rs.getInt("participantMax"));
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
        String sql = "SELECT session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin, session.supprime, user.idUser, user.nom, user.prenom, user.adresse, user.email, user.`password`, user.role, user.supprime,user.statut, role.idRole, role.nomRole, local.idLocal, local.nomLocal FROM local join session ON local.idLocal = session.idLocal  JOIN formation ON session.idFormation = formation.idFormation JOIN user ON session.idFormateur = user.idUser JOIN role ON role.idRole = user.role WHERE session.idSession = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idSession);
            rs = ps.executeQuery();
            if (rs.next()) {
                session = new Session(rs.getInt("idSession"),
                        new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                                new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getBoolean("supprime")),
                        new Local(rs.getInt("idLocal"), rs.getString("nomLocal")),
                        rs.getDate("dateDebut"),
                        rs.getDate("dateFin"),
                        rs.getBoolean("supprime"));
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
        String sql = "SELECT session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin, session.supprime, user.idUser, user.nom, user.prenom, user.adresse, user.email, user.`password`, user.role, user.supprime,user.statut, role.idRole, role.nomRole, local.idLocal, local.nomLocal FROM local join session ON local.idLocal = session.idLocal  JOIN formation ON session.idFormation = formation.idFormation JOIN user ON session.idFormateur = user.idUser JOIN role ON role.idRole = user.role WHERE formation.idFormation = ?";
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, formation.getIdFormation());
            rs = ps.executeQuery();
            while (rs.next()) {
                Session session = new Session(rs.getInt("idSession"),
                        new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                                new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getBoolean("supprime")),
                        new Local(rs.getInt("idLocal"), rs.getString("nomLocal")),
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

        String sql = "SELECT idFormation, nomFormation, prix, duree, participantMax, supprime  FROM formation where idFormation = ? AND supprime = 0";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormation);
            rs = ps.executeQuery();
            if (rs.next()) {
                formation = new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getDouble("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getBoolean("supprime"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getFormationyId(int idFormation): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return formation;
    }

    @Override
    public User getUserByEmail(String email) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User u = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idUser, nom, prenom, adresse, email, password, role, statut, supprime FROM user WHERE email = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, email);
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
                            getRoleById(rs.getInt("role")), rs.getBoolean("supprime"));

                } else {
                    u = null;
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getUserByEmail(String name): \n" + sqle.getMessage());
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

        String sql = "SELECT * FROM user WHERE idUser = ? AND role = 3 AND supprime = 0";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormateur);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                        getRoleById(rs.getInt("role")), rs.getBoolean("supprime"));

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
                statut = new Statut(rs.getInt("idStatut"), rs.getString("nomStatut"), rs.getDouble("discount"));
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

        String sql = "INSERT INTO formation(nomFormation, prix, duree, participantMax) VALUES (?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, formation.getNomFormation());
            ps.setDouble(2, formation.getPrix());
            ps.setInt(3, formation.getDuree());
            ps.setInt(4, formation.getParticipantMax());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method createNewFormation(): \n" + sqle.getMessage());
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

        String sql = "UPDATE formation SET nomFormation = ? ,prix=? ,duree=? ,participantMax=? where idFormation = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, formation.getNomFormation());
            ps.setDouble(2, formation.getPrix());
            ps.setInt(3, formation.getDuree());
            ps.setInt(4, formation.getParticipantMax());
            ps.setInt(5, formation.getIdFormation());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method void updateFormation(Formation formation): \n" + sqle.getMessage());
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

        String sql = "SELECT * FROM user WHERE supprime = 0 AND Role  = 3";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int role = rs.getInt("role");
                if (role == 3) {
                    formateur = new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            new Role(rs.getInt("role"), rs.getString("role")), rs.getBoolean("supprime"));
                    listFormateur.add(formateur);
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Formateur> getAllFormateur(): \n" + sqle.getMessage());
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
            ps.setInt(1, session.getIdSession());
            rs = ps.executeQuery();
            while (rs.next()) {

                inscription = new Inscription();
                listInscription.add(inscription);

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Inscription> listeInscriptionbySession(Session session): \n" + sqle.getMessage());
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

        String sql = "SELECT session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin, session.supprime,user.idUser, user.nom, user.prenom, user.adresse, user.email, user.`password`, user.role, user.statut, user.supprime,role.idRole, role.nomRole, statut.idStatut, statut.nomStatut, local.idLocal, local.nomLocal  FROM session JOIN formation ON session.idFormation = formation.idFormation JOIN user ON user.idUser = session.idFormateur JOIN role ON role.idRole = user.role JOIN statut on statut.idStatut = user.statut JOIN local ON local.idLocal = session.idLocal";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Session session = new Session(rs.getInt("idSession"),
                        new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                                new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getBoolean("supprime")),
                        new Local(rs.getInt("idLocal"), rs.getString("nomLocal")),
                        rs.getDate("dateDebut"),
                        rs.getDate("dateFin"),
                        rs.getBoolean("supprime"));
                listInformationsByFormateur.add(session);

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Session> listeInformationsByFormateurs(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listInformationsByFormateur;

    }

    @Override
    public boolean CreateNewSession(Session session, Formation formation) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql1 = "SELECT idSession, idFormation, idformateur, idLocal, dateDebut, dateFin, supprime FROM session WHERE supprime = 0 AND idFormateur = ? AND ?  BETWEEN dateDebut AND dateFin AND ?  BETWEEN dateDebut AND dateFin";
        String sql2 = "INSERT INTO Session(idformation, idformateur, idLocal, dateDebut, dateFin) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(sql1);
            ps.setInt(1, session.getIdformateur().getIdUser());
            ps.setDate(2, new java.sql.Date(session.getDateDebut().getTime()));
            ps.setDate(3, new java.sql.Date(session.getDateFin().getTime()));
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql2);
                ps.setInt(1, formation.getIdFormation());
                ps.setInt(2, session.getIdformateur().getIdUser());
                ps.setInt(3, session.getIdLocal().getIdLocal());
                ps.setDate(4, new java.sql.Date(session.getDateDebut().getTime()));
                ps.setDate(5, new java.sql.Date(session.getDateFin().getTime()));
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method createNewFormation(): \n" + sqle.getMessage());
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

        String sql = "SELECT idLocal, nomLocal from Local WHERE supprime = 0";

        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Local local = new Local(rs.getInt("idLocal"), rs.getString("nomLocal"));
                listLocal.add(local);

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Local> getAllLocal()): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listLocal;
    }

    @Override
    public List<Formateur> getAllFormateurAvailable(Session session) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Formateur formateur = null;
        List<Formateur> listFormateur = new ArrayList<>();
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idUser, nom, prenom, adresse, email, password, role, statut, supprime FROM user JOIN session on user.idUser = session.idFormateur  WHERE ? BETWEEN dateDebut AND dateFin AND ?  BETWEEN dateDebut AND dateFin AND role = 3 AND supprime = 0";

        try {
            ps = c.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(session.getDateDebut().getTime()));
            ps.setDate(2, new java.sql.Date(session.getDateFin().getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {

                formateur = new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                        new Role(rs.getInt("role"), rs.getString("role")), rs.getBoolean("supprime"));
                listFormateur.add(formateur);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, List<Formateur> getAllFormateurAvailable(Session session): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listFormateur;
    }

    @Override
    public boolean validationPaiement(int sessionId, User user) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql1 = "SELECT idSession, idUser FROM inscription WHERE idSession = ? AND idUser  = ? AND inscription.notificationPaiement = 1 ";
        String sql2 = "UPDATE inscription SET notificationPaiement = 1  WHERE idInscription = ? ";

        try {
            ps = c.prepareStatement(sql1);
            ps.setInt(1, sessionId);
            ps.setInt(2, user.getIdUser());
            rs = ps.executeQuery();
            if (!rs.next()) {
                ps = c.prepareStatement(sql2);
                ps.setInt(1, sessionId);
                ps.executeUpdate();
                result = true;
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method validationPaiement(int sessionId, User user) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

        return result;
    }

    @Override
    public List<Inscription> getInscritpionPaiementNotification() {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Inscription> listInscription = new ArrayList<>();
        String sql = "SELECT idInscription,statutPaiement,notificationPaiement,prix FROM inscription WHERE inscription.notificationPaiement = true AND inscription.statutPaiement = false";
        try {
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inscription inscription = new Inscription(rs.getInt("idInscription"), rs.getBoolean("statutPaiement"), rs.getBoolean("notificationPaiement"), rs.getDouble("prix"));
                listInscription.add(inscription);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method List<Inscription> getInscritpionPaiementNotification(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listInscription;

    }

    @Override
    public void validationStatutPaiment(int idInscription) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE inscription SET statutPaiement = 1  WHERE idInscription = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idInscription);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method validationPaiement(int sessionId, User user) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }

    @Override
    public List<Stagiaire> resultListStagiaireBySession(int sessionId) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Stagiaire> listStagiaire = new ArrayList<>();

        String sql = "SELECT inscription.annule, session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin,  session.supprime,user.idUser, user.nom, user.prenom, user.adresse, user.email, user.`password`, user.role, user.supprime,user.statut, role.idRole, role.nomRole, statut.idStatut, statut.nomStatut, statut.discount,local.idLocal, local.nomLocal, inscription.statutPaiement, inscription.notificationPaiement, inscription.annule, inscription.prix FROM inscription JOIN session ON session.idSession  = inscription.idSession JOIN user ON user.idUser = inscription.idUser JOIN role ON role.idRole = user.role JOIN statut on statut.idStatut = user.statut JOIN local ON local.idLocal = session.idLocal WHERE inscription.idSession = ? AND inscription.annule = 0";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, sessionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Stagiaire stagiaire = new Stagiaire(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                        getRoleById(rs.getInt("role")),
                        getStatutById(rs.getInt("statut")));
                listStagiaire.add(stagiaire);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Stagiaire> resultListStagiaireBySession(int sessionId): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listStagiaire;
    }

    @Override
    public void cleanDb() {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "call cleanDb()";

        try {
            ps = c.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method cleanDb(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

    }

    @Override
    public User getUserbyId(int idUser) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User u = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idUser, nom, prenom, adresse, email, password, role, statut, supprime FROM user WHERE idUser = ? ";

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
                            getRoleById(rs.getInt("role")), rs.getBoolean("supprime"));

                } else {
                    u = null;
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getUserByName(String name): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return u;
    }

    @Override
    public Formateur getFormateurBySession(int idSession) {
        Formateur formateur = new Formateur();
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT session.idSession, session.idFormateur, user.idUser, user.nom, user.prenom FROM session JOIN user ON user.idUser = session.idFormateur where session.idSession = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idSession);
            rs = ps.executeQuery();
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method Formateur getFormateurBySession(int idSession) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return formateur;
    }

    @Override
    public List<Session> listeSessionByIdFormateur(int idFormateur) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Session> listSession = new ArrayList<>();
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT session.idSession, session.idFormation, session.idFormateur, session.idLocal, session.dateDebut, session.dateFin, session.supprime, user.idUser,user.nom, user.prenom, user.adresse, user.email, user.`password`, user.role, user.statut, user.supprime,role.idRole, role.nomRole, statut.idStatut, statut.nomStatut, local.idLocal, local.nomLocal  FROM session JOIN user ON user.idUser = session.idFormateur JOIN role ON role.idRole = user.role JOIN statut on statut.idStatut = user.statut JOIN local ON local.idLocal = session.idLocal WHERE session.idFormateur = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormateur);
            rs = ps.executeQuery();
            while (rs.next()) {
                Session session
                        = new Session(rs.getInt("idSession"),
                                new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                                        new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getBoolean("supprime")),
                                new Local(rs.getInt("idLocal"), rs.getString("nomLocal")),
                                rs.getDate("dateDebut"),
                                rs.getDate("dateFin"),
                                rs.getBoolean("supprime"));

                listSession.add(session);

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method listeSessionByIdFormateur(int idFormateur)): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listSession;

    }

    @Override
    public List<Formateur> getFormateurAvailable(Session session, Formation formation) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Formateur formateur = null;
        List<Formateur> listFormateur = new ArrayList<>();
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT user.idUser, user.nom, user.prenom, user.adresse, user.email, user.`password`, user.role, user.statut, user.supprime,role.idRole, role.nomRole, enseigne.idFormation, enseigne.idUser FROM user JOIN role ON role.idRole = user.role JOIN statut ON statut.idStatut = user.statut JOIN enseigne ON user.idUser = enseigne.idUser WHERE enseigne.idFormation  = ? AND user.idUser NOT IN (SELECT session.idFormateur from session WHERE session.dateDebut > ? AND session.dateFin < ? OR session.dateDebut < ? AND session.dateFin > ?) AND user.supprime = 0 AND Role  = 3 AND enseigne.idUser = user.idUser ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, formation.getIdFormation());
            ps.setDate(2, new java.sql.Date(session.getDateFin().getTime()));
            ps.setDate(3, new java.sql.Date(session.getDateDebut().getTime()));
            ps.setDate(4, new java.sql.Date(session.getDateFin().getTime()));
            ps.setDate(5, new java.sql.Date(session.getDateDebut().getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                int role = rs.getInt("role");
                if (role == 3) {
                    formateur = new Formateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("email"), rs.getString("password"),
                            new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getBoolean("supprime"));
                    listFormateur.add(formateur);
                }
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Formateur> getFormateurAvailable(Session session): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listFormateur;
    }

    @Override
    public Inscription getInscriptionbyId(int inscriptionId) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Inscription inscription = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idInscription,statutPaiement,notificationPaiement,prix FROM inscription WHERE idInscription = ? AND notificationPaiement = 1 ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, inscriptionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                inscription = new Inscription(rs.getInt("idInscription"), rs.getBoolean("statutPaiement"), rs.getBoolean("notificationPaiement"), rs.getDouble("prix"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method Inscription getInscriptionbyId(int inscriptionId): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return inscription;
    }

    @Override
    public List<Local> getLocalAvailable(Session session, Formation formation) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Formateur formateur = null;
        List<Local> listLocal = new ArrayList<>();
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT local.idLocal , local.nomLocal FROM local WHERE local.idLocal NOT IN(select session.idLocal from session,local where local.idLocal = session.idLocal AND session.dateDebut < ? AND session.dateFin > ? OR session.dateDebut > ? AND session.dateFin < ?)";
        try {
            ps = c.prepareStatement(sql);

            ps.setDate(1, new java.sql.Date(session.getDateFin().getTime()));
            ps.setDate(2, new java.sql.Date(session.getDateDebut().getTime()));
            ps.setDate(3, new java.sql.Date(session.getDateFin().getTime()));
            ps.setDate(4, new java.sql.Date(session.getDateDebut().getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {

                Local local = new Local(rs.getInt("idLocal"), rs.getString("nomLocal"));
                listLocal.add(local);

            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Formateur> getFormateurAvailable(Session session): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listLocal;

    }

    @Override
    public void UpdateSession(Session session, Formation formation) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE session SET idFormateur=? ,idLocal=? ,dateDebut=?, dateFin =? WHERE idSession = ?";

        try {
            ps = c.prepareStatement(sql);

            ps.setInt(1, session.getIdformateur().getIdUser());
            ps.setInt(2, session.getIdLocal().getIdLocal());
            ps.setDate(3, new java.sql.Date(session.getDateDebut().getTime()));
            ps.setDate(4, new java.sql.Date(session.getDateFin().getTime()));
            ps.setInt(5, session.getIdSession());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method createNewFormation(): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }

    }

    @Override
    public boolean createLocaux(Local local) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql1 = "SELECT nomLocal FROM local WHERE nomLocal = ?";
        String sql2 = "INSERT INTO local(nomLocal) VALUES (?)";

        try {
            ps = c.prepareStatement(sql1);
            ps.setString(1, local.getNomLocal());
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql2);
                ps.setString(1, local.getNomLocal());
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method boolean createLocaux(Local local): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public boolean updateLocaux(Local local) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql2 = "UPDATE local SET nomLocal=? WHERE idLocal = ? ";

        try {
            ps = c.prepareStatement(sql2);
            ps.setString(1, local.getNomLocal());
            ps.setInt(2, local.getIdLocal());
            ps.executeUpdate();
            result = true;
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method boolean boolean updateLocaux(Local local): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public boolean deleteLocaux(Local local) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE local SET supprime = 1 WHERE idLocal = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, local.getIdLocal());
            ps.executeUpdate();
            result = true;
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method boolean deleteLocaux(Local local): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public boolean createStatut(Statut statut) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql1 = "SELECT nomStatut FROM statut WHERE nomStatut = ?";
        String sql2 = "INSERT INTO statut(nomStatut, discount) VALUES (?,?)";

        try {
            ps = c.prepareStatement(sql1);
            ps.setString(1, statut.getNomStatut());
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql2);
                ps.setString(1, statut.getNomStatut());
                ps.setDouble(2, statut.getDiscount());
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method boolean createLocaux(Local local): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public boolean updateStatut(Statut statut) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql1 = "SELECT nomStatut FROM statut WHERE nomStatut = ? AND idStatut != ?";
        String sql2 = "UPDATE statut SET nomStatut=?, discount=? WHERE idStatut = ?";

        try {
            ps = c.prepareStatement(sql1);
            ps.setString(1, statut.getNomStatut());
            ps.setInt(2, statut.getIdStatut());
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql2);
                ps.setString(1, statut.getNomStatut());
                ps.setDouble(2, statut.getDiscount());
                ps.setInt(3, statut.getIdStatut());
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method updateStatut(Statut statut): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public boolean addFormationToFormateur(int idFormation, int idUser) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql1 = "SELECT idUser FROM enseigne WHERE idUser = ? AND idFormation = ?";
        String sql2 = "INSERT INTO enseigne(idUser, idFormation) VALUES (?,?)";

        try {
            ps = c.prepareStatement(sql1);
            ps.setInt(1, idUser);
            ps.setInt(2, idFormation);
            rs = ps.executeQuery();
            if (!rs.next()) {
                result = true;
                ps = c.prepareStatement(sql2);
                ps.setInt(1, idUser);
                ps.setInt(2, idFormation);
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method boolean addFormationToFormateur(int idUser, int idFormation): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;

    }

    @Override
    public List<Formation> getFormationByFormateur(int idFormateur) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        List<Formation> listFormation = new ArrayList<>();

        String sql = "SELECT formation.idFormation, nomFormation, prix, duree, participantMax, supprime, idUser FROM formation JOIN enseigne ON formation.idFormation = enseigne.idFormation WHERE supprime = 0 AND enseigne.idUser = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormateur);
            rs = ps.executeQuery();
            while (rs.next()) {
                Formation formation = new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getInt("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getBoolean("supprime"));
                listFormation.add(formation);
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method List<Formation> getFormationByFormateur(int idFormateur) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return listFormation;
    }

    @Override
    public boolean deleteFormationToFormateur(int idFormation, int idFormateur) {
        boolean result = false;
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "DELETE FROM enseigne WHERE idFormation = ? AND idUser = ?";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, idFormation);
            ps.setInt(2, idFormateur);
            ps.executeUpdate();
            result = true;
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDao, method boolean deleteFormationToFormateur(int idFormation, int idFormateur) : \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return result;
    }

    @Override
    public Formation getFormationByNom(String nomFormation) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Formation formation = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idFormation, nomFormation, prix, duree, participantMax, supprime  FROM formation where nomFormation = ? AND supprime = 0";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, nomFormation);
            rs = ps.executeQuery();
            if (rs.next()) {
                formation = new Formation(rs.getInt("idFormation"), rs.getString("nomFormation"), rs.getDouble("prix"), rs.getInt("duree"), rs.getInt("participantMax"), rs.getBoolean("supprime"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method getFormationyId(int idFormation): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return formation;
    }

    @Override
    public Local getLocalByNom(String nomLocal) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Local local = null;
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "SELECT idLocal,nomLocal FROM local where nomLocal = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, nomLocal);
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
    public Inscription getInscriptionbyIdUser(int inscriptionId, User user) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Inscription inscription = null;
        c = MySqlDaoFactory.getInstance().getConnection();
        
        
        System.out.println(inscriptionId);
        System.out.println(user.getIdUser());
        

        String sql = "SELECT idInscription,statutPaiement,notificationPaiement,prix FROM inscription WHERE idInscription = ? AND idUser = ? AND notificationPaiement = 1 ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, inscriptionId);
            ps.setInt(2, user.getIdUser());
            rs = ps.executeQuery();
            while (rs.next()) {
                inscription = new Inscription(rs.getInt("idInscription"), rs.getBoolean("statutPaiement"), rs.getBoolean("notificationPaiement"), rs.getDouble("prix"));
            }
        } catch (SQLException sqle) {
            System.err.println("MySqlCentreDAO, method Inscription getInscriptionbyId(int inscriptionId): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
        return inscription;
    }

}
