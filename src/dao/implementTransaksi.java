/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import object.Iuran;
import object.Pengeluaran;
import object.Transaksi;
import object.User;

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
    
    public Pengeluaran getPengeluaran(Transaksi b);
    
    public int getJumlahKas();
    
    public int getJumlahIuran();
    
    public int getJumlahPengeluaran();
    
    public int getJumlahTransaksi();
    
    public int getUtang(int user_id, int iuran_id);
    
    public int getTotalBayar(int user_id, int iuran_id);
    
    public int getTotalDibayar(int user_id, int iuran_id);
    
    public Transaksi getTransaksiPertama(String user_id, String iuran_id);
    
    public int getCount();
}
