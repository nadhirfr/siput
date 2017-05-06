/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import object.KategoriIuran;
import dao.implementIuran;
import dao.implementKategoriIuran;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class KategoriIuranModel implements implementKategoriIuran{

    RESTDAOFactory restFactory;
    implementKategoriIuran dAOKategoriIuran;
    List<KategoriIuran> listKategoriIuran;
    
    public KategoriIuranModel() {
        restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        dAOKategoriIuran = restFactory.getKategoriIuran();
        listKategoriIuran = dAOKategoriIuran.getAll();
    }
    
    @Override
    public void insert(KategoriIuran b) {
        dAOKategoriIuran.insert(b);
    }

    @Override
    public KategoriIuran get(String KategoriIuranId) {
        return dAOKategoriIuran.get(KategoriIuranId);
    }

    @Override
    public void update(KategoriIuran b) {
        dAOKategoriIuran.update(b);
    }

    @Override
    public void delete(String KategoriIuranId) {
        dAOKategoriIuran.delete(KategoriIuranId);
    }

    @Override
    public List<KategoriIuran> getAll() {
        return listKategoriIuran;
    }

    @Override
    public List<KategoriIuran> getCari(String KategoriIuranNama) {
        return dAOKategoriIuran.getCari(KategoriIuranNama);
    }

    @Override
    public int getCount() {
        return dAOKategoriIuran.getCount();
    }
    
}
