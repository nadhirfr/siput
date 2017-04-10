/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_Iuran;
import model.model_IuranUser;
import model.model_Transaksi;
import model.model_User;

/**
 *
 * @author fachrul
 */
public interface implementIuranUser {
        public void insert(model_IuranUser b);
    
    public model_IuranUser get(String IuranIuranUserId);

    public void update(model_IuranUser b);
 
    public void delete(String IuranUserId);

    public List<model_IuranUser> getAll();

    public List<model_IuranUser> getCari(int userId);
    
    public model_User getUser(model_IuranUser b);
    
    public model_Iuran getIuran(model_IuranUser b);
    
    public model_Transaksi getTransaksi(model_IuranUser b);
    
    public int getCount();
}
