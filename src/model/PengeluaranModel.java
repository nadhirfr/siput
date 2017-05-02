/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementDeposit;
import dao.implementPengeluaran;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class PengeluaranModel implements implementPengeluaran {

    RESTDAOFactory restFactory;
    implementPengeluaran dAOPengeluaran;
    List<Pengeluaran> listPegeluaran;

    public PengeluaranModel() {
        restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        dAOPengeluaran = restFactory.getPengeluaran();
        listPegeluaran = dAOPengeluaran.getAll();

    }

    @Override
    public void insert(Pengeluaran b) {
        dAOPengeluaran.insert(b);
    }

    @Override
    public Pengeluaran get(String PengeluaranId) {
        return dAOPengeluaran.get(PengeluaranId);
    }

    @Override
    public void update(Pengeluaran b) {
        dAOPengeluaran.update(b);
    }

    @Override
    public void delete(String PengeluaranId) {
        dAOPengeluaran.delete(PengeluaranId);
    }

    @Override
    public List<Pengeluaran> getAll() {
        return dAOPengeluaran.getAll();
    }

    @Override
    public List<Pengeluaran> getCari(String PengeluaranNama) {
        return dAOPengeluaran.getCari(PengeluaranNama);
    }

    @Override
    public int getCount() {
        return dAOPengeluaran.getCount();
    }

}
