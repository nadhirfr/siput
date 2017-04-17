/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Iuran;
import model.model_Pengeluaran;
import model.Transaksi;
import model.User;

/**
 *
 * @author fachrul
 */
public interface implementTransaksi {
    public void insert(Transaksi b);
    
    public Transaksi get(String transaksiId);

    public void update(Transaksi b);

    public void delete(String transaksiId);

    public List<Transaksi> getAll();

    public List<Transaksi> getCari(String IuranNama);
    
    public User getUser(Transaksi b);
    
    public Iuran getIuran(Transaksi b);
    
    public model_Pengeluaran getPengeluaran(Transaksi b);
    
    public int getCount();
}
