/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementTransaksi;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import factory.RESTDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class TransaksiModel implements implementTransaksi{
    RESTDAOFactory restFactory;
    implementTransaksi dAOTransaksi;
    List<Transaksi> listTransaksi;
        
    public TransaksiModel() {
        restFactory = (RESTDAOFactory) DAOFactory.getFactory(DAOFactory.REST);
        dAOTransaksi = restFactory.getTransaksi();
        listTransaksi = dAOTransaksi.getAll();
    }

    @Override
    public void insert(Transaksi b) {
        dAOTransaksi.insert(b);
    }

    @Override
    public Transaksi get(String transaksiId) {
        return dAOTransaksi.get(transaksiId);
    }

    @Override
    public void update(Transaksi b) {
        dAOTransaksi.update(b);
    }

    @Override
    public void delete(String transaksiId) {
        dAOTransaksi.delete(transaksiId);
    }

    @Override
    public List<Transaksi> getAll() {
        return listTransaksi;
    }

    @Override
    public List<Transaksi> getCari(String IuranNama) {
        return dAOTransaksi.getCari(IuranNama);
    }

    @Override
    public User getUser(Transaksi b) {
        return dAOTransaksi.getUser(b);
    }

    @Override
    public Iuran getIuran(Transaksi b) {
        return dAOTransaksi.getIuran(b);
    }

    @Override
    public Pengeluaran getPengeluaran(Transaksi b) {
        return dAOTransaksi.getPengeluaran(b);
    }

    @Override
    public int getCount() {
        return dAOTransaksi.getCount();
    }

    @Override
    public int getJumlahKas() {
        return dAOTransaksi.getJumlahKas();
    }

    @Override
    public int getJumlahIuran() {
        return dAOTransaksi.getJumlahIuran();
    }

    @Override
    public int getJumlahPengeluaran() {
        return dAOTransaksi.getJumlahPengeluaran();
    }

    @Override
    public int getJumlahTransaksi() {
        return dAOTransaksi.getJumlahTransaksi();
    }

    @Override
    public Transaksi getTransaksiPertama(String user_id, String iuran_id) {
        return dAOTransaksi.getTransaksiPertama(user_id, iuran_id);
    }
    
}
