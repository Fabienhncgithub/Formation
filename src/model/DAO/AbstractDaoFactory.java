package model.DAO;

public abstract class AbstractDaoFactory {

    private static AbstractDaoFactory factory;

    public static AbstractDaoFactory getFactory() {
        return factory;
    }

    public static void setFactory(AbstractDaoFactory factory) {
        AbstractDaoFactory.factory = factory;
    }

    public abstract UserDao createUserDao();

    public abstract CentreDao createCentreDao();

    public abstract FormationDao createFormationDao();

    public abstract FormateurDao createFormateurDao();

    public abstract SessionDao createSessionDao();

    public abstract StagiaireDao createStagiaireDao() ;
}
