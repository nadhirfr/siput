/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.DAOMySQLDeposit;
import dao.DAOMySQLIuran;
import dao.DAOMySQLIuranUser;
import dao.DAOMySQLUser;
import dao.implementDeposit;
import dao.implementIuran;
import dao.implementIuranUser;
import dao.implementUser;

/**
 *
 * @author Komputer 03
 */
public class MySQLDAOFactory extends DAOFactory{
    
    @Override
    public implementUser getUserMySQL() {
        return new DAOMySQLUser();
    }

    @Override
    public implementIuran getIuranMySQL() {
       return new DAOMySQLIuran();
    }

    @Override
    public implementDeposit getDepositMySQL() {
        return new DAOMySQLDeposit();
    }

    @Override
    public implementIuranUser getIuranUserMySQL() {
        return new DAOMySQLIuranUser();
    }

    
}
