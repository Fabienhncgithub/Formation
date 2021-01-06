package model.DAO.Mysql;

import model.DAO.CentreDao;
import model.DAO.FormateurDao;
import model.DAO.FormationDao;
import model.DAO.Mysql.MySqlCentreDao;
import model.DAO.UserDao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import model.DAO.AbstractDaoFactory;
import model.DAO.Mysql.MySqlFormateurDao;
import model.DAO.Mysql.MySqlFormationDao;
import model.DAO.SessionDao;
import model.DAO.StagiaireDao;

public class MySqlDaoFactory extends AbstractDaoFactory {

    private static MySqlDaoFactory instance;
    private static final String FICHIER_PROPERTIES = "model/dao/config.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

    private String url;
    private String driver;
    private String username;
    private String password;

    
    private MySqlDaoFactory() {
    }

    public static MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection c = null;
        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (fichierProperties == null) {
            //throw new DaoConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
        }

        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            username = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
            password = properties.getProperty(PROPERTY_MOT_DE_PASSE);

        } catch (IOException e) {
            //throw new DaoConfigurationException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
        }

        try {
            Class.forName(driver); // initialise le driver jdbc pour DriverManager
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) { // gestion erreur pour le Class.forName()
            e.printStackTrace();
        }

        return c;
    }

    public static void closeAll(ResultSet rs, Statement ps, Connection c) { // ferme toutes les connections
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
        }

        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
        }
    }

    @Override
    public UserDao createUserDao() {
        return MySqlUserDao.getInstance();
    }

  @Override
    public CentreDao createCentreDao() {
        return MySqlCentreDao.getInstance();
    }

  @Override
    public FormationDao createFormationDao() {
        return MySqlFormationDao.getInstance();
    }

    @Override
    public FormateurDao createFormateurDao() {
            return MySqlFormateurDao.getInstance();
    }

    @Override
    public SessionDao createSessionDao() {
         return MySqlSessionDao.getInstance();
    }

    @Override
    public StagiaireDao createStagiaireDao() {
  return MySqlStagiaireDao.getInstance();
    }

   



 




 


}
