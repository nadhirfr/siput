/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import model.model_Pengeluaran;
import java.util.List;

/**
 *
 * @author rheza
 */
public interface implementPengeluaran {
    public void insert(model_Pengeluaran b);
    
    public model_Pengeluaran get(String PengeluaranId);

    public void update(model_Pengeluaran b);

    public void delete(String PengeluaranId);

    public List<model_Pengeluaran> getAll();

    public List<model_Pengeluaran> getCari(String PengeluaranNama);
    
    public int getCount();
}
