/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.PengeluaranJenis;

/**
 *
 * @author rheza
 */
public interface implementPengeluaranJenis {
        public void insert(PengeluaranJenis b);
    
    public PengeluaranJenis get(String PengeluaranJenisId);

    public void update(PengeluaranJenis b);

    public void delete(String PengeluaranJenisId);

    public List<PengeluaranJenis> getAll();

    public List<PengeluaranJenis> getCari(String PengeluaranNama);
    
    public int getCount();
}
