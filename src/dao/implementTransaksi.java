/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_Iuran;
import model.model_Pengeluaran;
import model.model_Transaksi;
import model.User;

/**
 *
 * @author fachrul
 */
public interface implementTransaksi {
    public void insert(model_Transaksi b);
    
    public model_Transaksi get(String transaksiId);

    public void update(model_Transaksi b);

    public void delete(String transaksiId);

    public List<model_Transaksi> getAll();

    public List<model_Transaksi> getCari(String IuranNama);
    
    public User getUser(model_Transaksi b);
    
    public model_Iuran getIuran(model_Transaksi b);
    
    public model_Pengeluaran getPengeluaran(model_Transaksi b);
    
    public int getCount();
}
