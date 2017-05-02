/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementJenisIuran;
import dao.implementKategoriIuran;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class JenisIuranModel implements implementJenisIuran{
    RESTDAOFactory restFactory;
    implementJenisIuran dAOJenisIuran;
    List<JenisIuran> listJenisIuran;
    
    public JenisIuranModel() {
        restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        dAOJenisIuran = restFactory.getJenisIuran();
        listJenisIuran = dAOJenisIuran.getAll();
    }
    @Override
    public void insert(JenisIuran b) {
        dAOJenisIuran.insert(b);
    }

    @Override
    public JenisIuran get(String JenisIuranId) {
        return dAOJenisIuran.get(JenisIuranId);
    }

    @Override
    public void update(JenisIuran b) {
        dAOJenisIuran.update(b);
    }

    @Override
    public void delete(String JenisIuranId) {
        dAOJenisIuran.delete(JenisIuranId);
    }

    @Override
    public List<JenisIuran> getAll() {
        return listJenisIuran;
    }

    @Override
    public List<JenisIuran> getCari(String JenisIuranNama) {
        return dAOJenisIuran.getCari(JenisIuranNama);
    }

    @Override
    public int getCount() {
        return dAOJenisIuran.getCount();
    }
    
}
