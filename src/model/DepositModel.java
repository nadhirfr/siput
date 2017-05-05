/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import object.Deposit;
import object.User;
import dao.implementDeposit;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class DepositModel implements implementDeposit{

    RESTDAOFactory mysqlFactory;
    implementDeposit dAODeposit;
    List<Deposit> listDeposit;

    public DepositModel() {
        mysqlFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        dAODeposit = mysqlFactory.getDeposit();
        listDeposit = dAODeposit.getAll();

    }
    
    @Override
    public void insert(Deposit b) {
        dAODeposit.insert(b);
    }

    @Override
    public Deposit get(String depositId) {
        return dAODeposit.get(depositId);
    }

    @Override
    public Deposit getByUser(User b) {
        return dAODeposit.getByUser(b);
    }

    @Override
    public void update(Deposit b) {
        dAODeposit.update(b);
    }

    @Override
    public void delete(String depositId) {
        dAODeposit.delete(depositId);
    }

    @Override
    public List<Deposit> getAll() {
        return listDeposit;
    }

    @Override
    public List<Deposit> getCari(String userDisplayname) {
       return dAODeposit.getCari(userDisplayname);
    }

    @Override
    public User getUser(Deposit b) {
        return dAODeposit.getUser(b);
    }

    @Override
    public int getCount() {
        return dAODeposit.getCount();
    }
    
}
