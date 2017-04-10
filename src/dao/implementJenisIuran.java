/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_JenisIuran;

/**
 *
 * @author fachrul
 */
public interface implementJenisIuran {
    public void insert(model_JenisIuran b);
    
    public model_JenisIuran get(String JenisIuranId);

    public void update(model_JenisIuran b);

    public void delete(String JenisIuranId);

    public List<model_JenisIuran> getAll();

    public List<model_JenisIuran> getCari(String JenisIuranNama);
    
    public int getCount();
}
