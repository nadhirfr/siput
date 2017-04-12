/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_PengeluaranUser;

/**
 *
 * @author rheza
 */
public interface implementPengeluaranUser {
        public void insert(model_PengeluaranUser b);
    
    public model_PengeluaranUser get(String PengeluaranUserId);

    public void update(model_PengeluaranUser b);

    public void delete(String PengeluaranUserId);

    public List<model_PengeluaranUser> getAll();

    public List<model_PengeluaranUser> getCari(String PengeluaranUserNama);
    
    public int getCount();
}
