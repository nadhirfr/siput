/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import model.model_PengeluaranPerubahan;
/**
 *
 * @author rheza
 */
public interface implementPengeluaranPerubahan {
        public void insert(model_PengeluaranPerubahan b);
    
    public model_PengeluaranPerubahan get(String PengeluaranPerubahanId);

    public void update(model_PengeluaranPerubahan b);

    public void delete(String PengeluaranPerubahanId);

    public List<model_PengeluaranPerubahan> getAll();

    public List<model_PengeluaranPerubahan> getCari(String PengeluaranPerubahanNama);
    
    public int getCount();
}
