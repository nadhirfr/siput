/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.MySQLPersonDAO;
import dao.PersonDAO;
import factory.DAOFactory;

/**
 *
 * @author Komputer 03
 */
public class MySQLDAOFactory extends DAOFactory{
    public PersonDAO getPersonDAO(){
        return new MySQLPersonDAO();
    }
    
}
