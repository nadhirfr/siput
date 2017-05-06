/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import object.PengeluaranPerubahan;
import dao.implementPengeluaran;
import dao.implementPengeluaranPerubahan;
import factory.DAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class PengeluaranPerubahanModel implements implementPengeluaranPerubahan{

    RESTDAOFactory restFactory;
    implementPengeluaranPerubahan dAOPengeluaranPerubahan;
    List<PengeluaranPerubahan> listPegeluaranPengeluaran;

    public PengeluaranPerubahanModel() {
        restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        dAOPengeluaranPerubahan = restFactory.getPengeluaranPerubahan();
        listPegeluaranPengeluaran = dAOPengeluaranPerubahan.getAll();
    }
    @Override
    public void insert(PengeluaranPerubahan b) {
        dAOPengeluaranPerubahan.insert(b);
    }

    @Override
    public PengeluaranPerubahan get(String PengeluaranPerubahanId) {
        return dAOPengeluaranPerubahan.get(PengeluaranPerubahanId);
    }

    @Override
    public void update(PengeluaranPerubahan b) {
        dAOPengeluaranPerubahan.update(b);
    }

    @Override
    public void delete(String PengeluaranPerubahanId) {
        dAOPengeluaranPerubahan.delete(PengeluaranPerubahanId);
    }

    @Override
    public List<PengeluaranPerubahan> getAll() {
        return listPegeluaranPengeluaran;
    }

    @Override
    public List<PengeluaranPerubahan> getCari(String PengeluaranPerubahanNama) {
        return dAOPengeluaranPerubahan.getCari(PengeluaranPerubahanNama);
    }

    @Override
    public int getCount() {
        return dAOPengeluaranPerubahan.getCount();
    }
    
}
