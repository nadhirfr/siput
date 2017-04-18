/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.Pengeluaran;
import java.util.List;

/**
 *
 * @author rheza
 */
public interface implementPengeluaran {
    public void insert(Pengeluaran b);
    
    public Pengeluaran get(String PengeluaranId);

    public void update(Pengeluaran b);

    public void delete(String PengeluaranId);

    public List<Pengeluaran> getAll();

    public List<Pengeluaran> getCari(String PengeluaranNama);
    
    public int getCount();
}
