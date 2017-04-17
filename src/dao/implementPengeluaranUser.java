/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.PengeluaranUser;

/**
 *
 * @author rheza
 */
public interface implementPengeluaranUser {
        public void insert(PengeluaranUser b);
    
    public PengeluaranUser get(String PengeluaranUserId);

    public void update(PengeluaranUser b);

    public void delete(String PengeluaranUserId);

    public List<PengeluaranUser> getAll();

    public List<PengeluaranUser> getCari(String PengeluaranUserNama);
    
    public int getCount();
}
