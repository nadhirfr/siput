/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_PengeluaranJenis;

/**
 *
 * @author rheza
 */
public interface implementPengeluaranJenis {
        public void insert(model_PengeluaranJenis b);
    
    public model_PengeluaranJenis get(String PengeluaranJenisId);

    public void update(model_PengeluaranJenis b);

    public void delete(String PengeluaranJenisId);

    public List<model_PengeluaranJenis> getAll();

    public List<model_PengeluaranJenis> getCari(String PengeluaranNama);
    
    public int getCount();
}
