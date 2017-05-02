/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.Rest.DAORestDeposit;
import dao.Rest.DAORestIuran;
import dao.Rest.DAORestIuranUser;
import dao.Rest.DAORestJenisIuran;
import dao.Rest.DAORestKategoriIuran;
import dao.Rest.DAORestPengeluaran;
import dao.Rest.DAORestPengeluaranJenis;
import dao.Rest.DAORestPengeluaranKategori;
import dao.Rest.DAORestPengeluaranPerubahan;
import dao.Rest.DAORestTransaksi;
import dao.Rest.DAORestUser;
import dao.implementDeposit;
import dao.implementIuran;
import dao.implementIuranUser;
import dao.implementJenisIuran;
import dao.implementKategoriIuran;
import dao.implementPengeluaran;
import dao.implementPengeluaranJenis;
import dao.implementPengeluaranKategori;
import dao.implementPengeluaranPerubahan;
import dao.implementTransaksi;
import dao.implementUser;

/**
 *
 * @author fachrul
 */
public class RESTDAOFactory extends DAOFactory{
    public implementUser getUser() {
        return new DAORestUser();
    }
    
    public implementIuran getIuran() {
        return new DAORestIuran();
    }
    
    public implementDeposit getDeposit() {
        return new DAORestDeposit();
    }
    
    public implementIuranUser getIuranUser() {
        return new DAORestIuranUser();
    }
    
    public implementJenisIuran getJenisIuran(){
        return new DAORestJenisIuran();
    }
    
    public implementKategoriIuran getKategoriIuran(){
        return new DAORestKategoriIuran();
    }
    
    public implementPengeluaran getPengeluaran(){
        return new DAORestPengeluaran();
    }
    
    public implementPengeluaranJenis getPengeluaranJenis(){
        return new DAORestPengeluaranJenis();
    }
    
    public implementPengeluaranKategori getPengeluaranKategori(){
        return new DAORestPengeluaranKategori();
    }
    
    public implementPengeluaranPerubahan getPengeluaranPerubahan(){
        return new DAORestPengeluaranPerubahan();
    }
    
    public implementTransaksi getTransaksi(){
        return new DAORestTransaksi();
    }
}
