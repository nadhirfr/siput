/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.PengeluaranKategori;
import java.util.List;
/**
 *
 * @author rheza
 */
public interface implementPengeluaranKategori {
        public void insert(PengeluaranKategori b);
    
    public PengeluaranKategori get(String PengeluaranKategoriId);

    public void update(PengeluaranKategori b);

    public void delete(String PengeluaranKategoriId);

    public List<PengeluaranKategori> getAll();

    public List<PengeluaranKategori> getCari(String PengeluaranKategoriNama);
    
    public int getCount();
}
