/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementUser;
import factory.DAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class UserModel implements implementUser{

    RESTDAOFactory restFactory;
    implementUser dAOUser;
    List<User> listUser;

    public UserModel() {
         restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
         dAOUser = restFactory.getUserREST();
         listUser = dAOUser.getAll(); 
    }
    
    @Override
    public int insert(User b) {
        return dAOUser.insert(b);
    }

    @Override
    public User getUser(String user_id) {
        return dAOUser.getUser(user_id);
    }

    @Override
    public void update(User b) {
        dAOUser.update(b);
    }

    @Override
    public void delete(String user_id) {
        dAOUser.delete(user_id);
    }

    @Override
    public List<User> getAll() {
        return listUser;
    }

    @Override
    public List<User> getCari(String displayname) {
        return dAOUser.getCari(displayname);
    }

    @Override
    public int getCount() {
        return dAOUser.getCount();
    }
    
}
