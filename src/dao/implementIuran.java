/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import object.Iuran;
import object.JenisIuran;
import object.KategoriIuran;

/**
 *
 * @author fachrul
 */
public interface implementIuran {
    public int insert(Iuran b);
    
    public Iuran get(String IuranId);

    public void update(Iuran b);

    public void delete(String IuranId);

    public List<Iuran> getAll();

    public List<Iuran> getCari(String IuranNama);
    
    public JenisIuran getJenisIuran(Iuran b);
    
    public KategoriIuran getKategoriIuran(Iuran b);
    
    public int getCount();
    
}
