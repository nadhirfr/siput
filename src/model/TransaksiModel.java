/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.implementTransaksi;
import factory.DAOFactory;
import factory.MySQLDAOFactory;
import java.util.List;

/**
 *
 * @author fachrul
 */
public class TransaksiModel implements implementTransaksi{

    MySQLDAOFactory mysqlFactory;
    implementTransaksi dAOTransaksi;
    List<Transaksi> listTransaksi;
    
    public TransaksiModel() {
        mysqlFactory = (MySQLDAOFactory) DAOFactory.getFactory(DAOFactory.MySQL);
        dAOTransaksi = mysqlFactory.getTransaksiMySQL();
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
    public model_Pengeluaran getPengeluaran(Transaksi b) {
        return dAOTransaksi.getPengeluaran(b);
    }

    @Override
    public int getCount() {
        return dAOTransaksi.getCount();
    }
    
}
