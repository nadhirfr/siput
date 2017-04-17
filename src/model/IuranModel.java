/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementIuran;
import dao.implementTransaksi;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class IuranModel implements implementIuran{

    MySQLDAOFactory mysqlFactory;
    implementIuran dAOIuran;
    List<Iuran> listIuran;
    
    public IuranModel() {
        mysqlFactory = (MySQLDAOFactory) DAOFactory.getFactory(DAOFactory.MySQL);
        dAOIuran = mysqlFactory.getIuranMySQL();
        listIuran = dAOIuran.getAll();
    }

    @Override
    public void insert(Iuran b) {
        dAOIuran.insert(b);
    }

    @Override
    public Iuran get(String IuranId) {
        return dAOIuran.get(IuranId);
    }

    @Override
    public void update(Iuran b) {
        dAOIuran.update(b);
    }

    @Override
    public void delete(String IuranId) {
        dAOIuran.delete(IuranId);
    }

    @Override
    public List<Iuran> getAll() {
        return dAOIuran.getAll();
    }

    @Override
    public List<Iuran> getCari(String IuranNama) {
        return dAOIuran.getCari(IuranNama);
    }

    @Override
    public JenisIuran getJenisIuran(Iuran b) {
        return dAOIuran.getJenisIuran(b);
    }

    @Override
    public KategoriIuran getKategoriIuran(Iuran b) {
        return dAOIuran.getKategoriIuran(b);
    }

    @Override
    public int getCount() {
        return dAOIuran.getCount();
    }
    
}
