/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO.Mysql;

import model.DAO.FormationDao;

/**
 *
 * @author Fabien
 */
public class MySqlFormationDao implements FormationDao{
    
    private static MySqlFormationDao instance;

    private MySqlFormationDao() {
    }

    public static MySqlFormationDao getInstance() {
        if (instance == null) {
            instance = new MySqlFormationDao();
        }
        return instance;
    }
    
    
    
    
    
    
}
