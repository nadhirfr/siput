/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import object.PengeluaranPerubahan;
/**
 *
 * @author rheza
 */
public interface implementPengeluaranPerubahan {
        public void insert(PengeluaranPerubahan b);
    
    public PengeluaranPerubahan get(String PengeluaranPerubahanId);

    public void update(PengeluaranPerubahan b);

    public void delete(String PengeluaranPerubahanId);

    public List<PengeluaranPerubahan> getAll();

    public List<PengeluaranPerubahan> getCari(String PengeluaranPerubahanNama);
    
    public int getCount();
}
