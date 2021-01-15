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
import model.DAO.SessionDao;
import model.Session;

/**
 *
 * @author Fabien
 */
class MySqlSessionDao implements SessionDao{
        
    private static MySqlSessionDao instance;

    private MySqlSessionDao() {
    }

    public static MySqlSessionDao getInstance() {
        if (instance == null) {
            instance = new MySqlSessionDao();
        }
        return instance;
    }

    @Override
    public void deleteSession(Session session) {
        Connection c;
        ResultSet rs = null;
        PreparedStatement ps = null;
       
        c = MySqlDaoFactory.getInstance().getConnection();

        String sql = "UPDATE session SET supprime = 1  WHERE idsession = ? ";

        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, session.getIdSession());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("MySqlUserDao, method deleteSession(Session session): \n" + sqle.getMessage());
        } finally {
            MySqlDaoFactory.closeAll(rs, ps, c);
        }
    }
    
}
