/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementPengeluaranKategori;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class PengeluaranKategoriModel implements implementPengeluaranKategori{

    RESTDAOFactory  restFactory;
    implementPengeluaranKategori daoPengeluaranKategori;
    List<PengeluaranKategori> listPegeluaranKategori;

    public PengeluaranKategoriModel() {
        restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        daoPengeluaranKategori = restFactory.getPengeluaranKategori();
        listPegeluaranKategori = daoPengeluaranKategori.getAll();

    }
    @Override
    public void insert(PengeluaranKategori b) {
        daoPengeluaranKategori.insert(b);
    }

    @Override
    public PengeluaranKategori get(String PengeluaranKategoriId) {
        return daoPengeluaranKategori.get(PengeluaranKategoriId);
   }

    @Override
    public void update(PengeluaranKategori b) {
        daoPengeluaranKategori.update(b);
    }

    @Override
    public void delete(String PengeluaranKategoriId) {
        daoPengeluaranKategori.delete(PengeluaranKategoriId);
    }

    @Override
    public List<PengeluaranKategori> getAll() {
        return listPegeluaranKategori;
    }

    @Override
    public List<PengeluaranKategori> getCari(String PengeluaranKategoriNama) {
        return daoPengeluaranKategori.getCari(PengeluaranKategoriNama);
    }

    @Override
    public int getCount() {
        return daoPengeluaranKategori.getCount();
    }
    
}
