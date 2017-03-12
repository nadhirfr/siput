/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.DAOUser;
import dao.implementUser;

/**
 *
 * @author Komputer 03
 */
public class MySQLDAOFactory extends DAOFactory{
    
    @Override
    public implementUser getUserDAO() {
        return new DAOUser();
    }
}
