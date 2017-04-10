/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_KategoriIuran;

/**
 *
 * @author fachrul
 */
public interface implementKategoriIuran {
     public void insert(model_KategoriIuran b);
    
    public model_KategoriIuran get(String KategoriIuranId);

    public void update(model_KategoriIuran b);

    public void delete(String KategoriIuranId);

    public List<model_KategoriIuran> getAll();

    public List<model_KategoriIuran> getCari(String KategoriIuranNama);
    
    public int getCount();
}
