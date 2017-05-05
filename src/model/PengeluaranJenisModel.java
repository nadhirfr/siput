/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import object.PengeluaranJenis;
import dao.implementPengeluaranJenis;
import factory.DAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class PengeluaranJenisModel implements implementPengeluaranJenis{
    RESTDAOFactory restFactory;
    implementPengeluaranJenis daoPengeluaranJenis;
    List<PengeluaranJenis> listPegeluaranJenis;

    public PengeluaranJenisModel() {
        restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        daoPengeluaranJenis = restFactory.getPengeluaranJenis();
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
