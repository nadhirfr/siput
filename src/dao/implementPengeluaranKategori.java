/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.model_PengeluaranKategori;
import java.util.List;
/**
 *
 * @author rheza
 */
public interface implementPengeluaranKategori {
        public void insert(model_PengeluaranKategori b);
    
    public model_PengeluaranKategori get(String PengeluaranKategoriId);

    public void update(model_PengeluaranKategori b);

    public void delete(String PengeluaranKategoriId);

    public List<model_PengeluaranKategori> getAll();

    public List<model_PengeluaranKategori> getCari(String PengeluaranKategoriNama);
    
    public int getCount();
}
