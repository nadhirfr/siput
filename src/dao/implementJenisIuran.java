/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.JenisIuran;

/**
 *
 * @author fachrul
 */
public interface implementJenisIuran {
    public void insert(JenisIuran b);
    
    public JenisIuran get(String JenisIuranId);

    public void update(JenisIuran b);

    public void delete(String JenisIuranId);

    public List<JenisIuran> getAll();

    public List<JenisIuran> getCari(String JenisIuranNama);
    
    public int getCount();
}
