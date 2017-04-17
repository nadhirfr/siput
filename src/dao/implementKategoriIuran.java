/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.KategoriIuran;

/**
 *
 * @author fachrul
 */
public interface implementKategoriIuran {
     public void insert(KategoriIuran b);
    
    public KategoriIuran get(String KategoriIuranId);

    public void update(KategoriIuran b);

    public void delete(String KategoriIuranId);

    public List<KategoriIuran> getAll();

    public List<KategoriIuran> getCari(String KategoriIuranNama);
    
    public int getCount();
}
