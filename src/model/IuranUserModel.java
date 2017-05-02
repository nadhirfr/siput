/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementIuranUser;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class IuranUserModel implements implementIuranUser{

    RESTDAOFactory restFactory;
    implementIuranUser dAOIuranUser;
    List<IuranUser> listIuranUser;
    
    public IuranUserModel() {
        restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        dAOIuranUser = restFactory.getIuranUser();
        listIuranUser = dAOIuranUser.getAll();
    }
    @Override
    public void insert(IuranUser b) {
        dAOIuranUser.insert(b);
    }

    @Override
    public IuranUser get(String IuranIuranUserId) {
        return dAOIuranUser.get(IuranIuranUserId);
    }

    @Override
    public void update(IuranUser b) {
        dAOIuranUser.update(b);
    }

    @Override
    public void delete(String IuranUserId) {
        dAOIuranUser.delete(IuranUserId);
    }

    @Override
    public List<IuranUser> getAll() {
        return listIuranUser;
    }

    @Override
    public List<IuranUser> getCari(int userId) {
        return dAOIuranUser.getCari(userId);
    }

    @Override
    public User getUser(IuranUser b) {
        return dAOIuranUser.getUser(b);
    }

    @Override
    public Iuran getIuran(IuranUser b) {
       return dAOIuranUser.getIuran(b);
    }

    @Override
    public int getCount() {
        return dAOIuranUser.getCount();
    }

    @Override
    public IuranUser getByUserAndIuran(User u, Iuran i) {
        return dAOIuranUser.getByUserAndIuran(u, i);
    }
    
}
