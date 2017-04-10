/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_Iuran;
import model.model_JenisIuran;
import model.model_KategoriIuran;

/**
 *
 * @author fachrul
 */
public interface implementIuran {
    public void insert(model_Iuran b);
    
    public model_Iuran get(String IuranId);

    public void update(model_Iuran b);

    public void delete(String IuranId);

    public List<model_Iuran> getAll();

    public List<model_Iuran> getCari(String IuranNama);
    
    public model_JenisIuran getJenisIuran(model_Iuran b);
    
    public model_KategoriIuran getKategoriIuran(model_Iuran b);
    
    public int getCount();
    
}
