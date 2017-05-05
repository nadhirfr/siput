/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Iuran;
import model.IuranUser;
import model.Transaksi;
import model.User;

/**
 *
 * @author fachrul
 */
public interface implementIuranUser {
        public void insert(IuranUser b);
    
    public IuranUser get(String IuranIuranUserId);
    
    public IuranUser getByUserAndIuran(User u, Iuran i);

    public void update(IuranUser b);
 
    public void delete(String IuranUserId);

    public List<IuranUser> getAll();

    public List<IuranUser> getCari(int userId);
    
    public User getUser(IuranUser b);
    
    public Iuran getIuran(IuranUser b);
        
    public List<IuranUser> getBelumLunas(User b);
        
    public int getCount();
}
