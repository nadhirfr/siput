/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.mysql.DAOMySQLDeposit;
import dao.mysql.DAOMySQLIuran;
import dao.mysql.DAOMySQLIuranUser;
import dao.mysql.DAOMySQLJenisIuran;
import dao.mysql.DAOMySQLKategoriIuran;
import dao.mysql.DAOMySQLPengeluaran;
import dao.mysql.DAOMySQLPengeluaranJenis;
import dao.mysql.DAOMySQLPengeluaranKategori;
import dao.mysql.DAOMySQLTransaksi;
import dao.mysql.DAOMySQLUser;
import dao.implementDeposit;
import dao.implementIuran;
import dao.implementIuranUser;
import dao.implementJenisIuran;
import dao.implementKategoriIuran;
import dao.implementPengeluaran;
import dao.implementPengeluaranJenis;
import dao.implementPengeluaranKategori;
import dao.implementTransaksi;
import dao.implementUser;

/**
 *
 * @author Komputer 03
 */
public class MySQLDAOFactory extends DAOFactory{
    
    public implementUser getUserMySQL() {
        return new DAOMySQLUser();
    }

    public implementIuran getIuranMySQL() {
       return new DAOMySQLIuran();
    }

    public implementDeposit getDepositMySQL() {
        return new DAOMySQLDeposit();
    }

    public implementIuranUser getIuranUserMySQL() {
        return new DAOMySQLIuranUser();
    }
    
    public implementTransaksi getTransaksiMySQL() {
        return new DAOMySQLTransaksi();
    }
    
    public implementPengeluaran getPengeluaranMySQL(){
        return new DAOMySQLPengeluaran();
    }
    
    public implementPengeluaranKategori getPengeluaranKategoriMySQL(){
        return new DAOMySQLPengeluaranKategori();
    }
    
    public implementPengeluaranJenis getPengeluaranJenisMySQL(){
        return new DAOMySQLPengeluaranJenis();
    }
    
    public implementKategoriIuran getKategoriIuran(){
        return new DAOMySQLKategoriIuran();
    }
    
    public implementJenisIuran getJenisIuran(){
        return new DAOMySQLJenisIuran();
    }
    

}