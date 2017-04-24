/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementPengeluaranJenis;
import dao.implementPengeluaranKategori;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class PengeluaranJenisModel implements implementPengeluaranJenis{
    MySQLDAOFactory mysqlFactory;
    implementPengeluaranJenis daoPengeluaranJenis;
    List<PengeluaranJenis> listPegeluaranJenis;

    public PengeluaranJenisModel() {
        mysqlFactory = (MySQLDAOFactory) DAOFactory.getFactory(DAOFactory.MySQL);
        daoPengeluaranJenis = mysqlFactory.getPengeluaranJenisMySQL();
        listPegeluaranJenis = daoPengeluaranJenis.getAll();

    }
    
    
    @Override
    public void insert(PengeluaranJenis b) {
        daoPengeluaranJenis.insert(b);
    }

    @Override
    public PengeluaranJenis get(String PengeluaranJenisId) {
        return daoPengeluaranJenis.get(PengeluaranJenisId);
    }

    @Override
    public void update(PengeluaranJenis b) {
        daoPengeluaranJenis.update(b);
    }

    @Override
    public void delete(String PengeluaranJenisId) {
        daoPengeluaranJenis.delete(PengeluaranJenisId);
    }

    @Override
    public List<PengeluaranJenis> getAll() {
        return listPegeluaranJenis;
    }

    @Override
    public List<PengeluaranJenis> getCari(String PengeluaranNama) {
        return getCari(PengeluaranNama);
    }

    @Override
    public int getCount() {
        return daoPengeluaranJenis.getCount();
    }
    
}
