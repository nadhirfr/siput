/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.Rest.DAORestUser;
import dao.implementUser;

/**
 *
 * @author fachrul
 */
public class RESTDAOFactory extends DAOFactory{
    public implementUser getUserREST() {
        return new DAORestUser();
    }
}
