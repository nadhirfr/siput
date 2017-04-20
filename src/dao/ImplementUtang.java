/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import model.Utang;
import model.User;
/**
 *
 * @author rheza
 */
public interface ImplementUtang {
    public void insert(Utang b);
    
    public Utang get(String utangId);
    
    public Utang getByUser(User b);

    public void update(Utang b);

    public void delete(String utangId);

    public List<Utang> getAll();
    
    //public List<Utang> getCari(String userDisplayname);
    
    public User getUser(Utang b);
    
    public int getCount();
}
