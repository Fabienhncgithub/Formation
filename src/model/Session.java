/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.DAO.AbstractDaoFactory;
import model.DAO.CentreDao;
import model.DAO.SessionDao;

/**
 *
 * @author Fabien
 */
public class Session {

    private int idSession;

    private Formateur idformateur;
    private Local idLocal;
    private Date dateDebut;
    private Date dateFin;
    private boolean supprime;
    private List<Inscription> listeInscriptionbySession;

    public Session(int idSession, Formateur idformateur, Local idLocal, Date dateDebut, Date dateFin, boolean supprime) {
        this.idSession = idSession;
        this.idformateur = idformateur;
        this.idLocal = idLocal;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.supprime = supprime;
    }

    //getformationbyidSession
    public Session() {
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public Formateur getIdformateur() {
        return idformateur;
    }

    public void setIdformateur(Formateur idformateur) {
        this.idformateur = idformateur;
    }

    public Local getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Local idLocal) {
        this.idLocal = idLocal;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isSupprime() {
        return supprime;
    }

    public void setSupprime(boolean supprime) {
        this.supprime = supprime;
    }

    public List<Inscription> getListeInscriptionbySession() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listeInscriptionbySession = centreDao.listeInscriptionbySession(this);
        return listeInscriptionbySession;
    }

    public void setListeInscriptionbySession(List<Inscription> listeInscriptionbySession) {
        this.listeInscriptionbySession = listeInscriptionbySession;
    }

    public void deleteSession() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        SessionDao sessionDao = factory.createSessionDao();
        sessionDao.deleteSession(this);
    }

    public List<Inscription> listeInscriptionbySession() {
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CentreDao centreDao = factory.createCentreDao();
        listeInscriptionbySession = centreDao.listeInscriptionbySession(this);
        return listeInscriptionbySession;
    }

    public void setDateF(Date date, int duree) {
        this.setDateDebut(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 1; i < duree;) {
            calendar.add(Calendar.DATE, 1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                i++;
            }
        }
        this.setDateFin(calendar.getTime());
    }
}
