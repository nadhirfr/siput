/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_DepositPerubahan;

/**
 *
 * @author rheza
 */
public interface ImplementDepositPerubahan {

    public void insert(model_DepositPerubahan b);

    public model_DepositPerubahan get(String DepositPerubahanId);

    public void update(model_DepositPerubahan b);

    public void delete(String DepositPerubahanId);

    public List<model_DepositPerubahan> getAll();

    public int getCount();
}
