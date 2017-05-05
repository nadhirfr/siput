/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import object.Iuran;
import object.JenisIuran;
import object.KategoriIuran;
import dao.implementIuran;
import factory.DAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class IuranModel implements implementIuran{

    RESTDAOFactory restlFactory;
    implementIuran dAOIuran;
    List<Iuran> listIuran;
    
    public IuranModel() {
        restlFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        dAOIuran = restlFactory.getIuran();
        listIuran = dAOIuran.getAll();
    }

    @Override
    public int insert(Iuran b) {
        return dAOIuran.insert(b);
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
