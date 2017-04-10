/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_Deposit;
import model.model_User;

/**
 *
 * @author fachrul
 */
public interface implementDeposit {
    public void insert(model_Deposit b);
    
    public model_Deposit get(String depositId);
    
    public model_Deposit getByUser(model_User b);

    public void update(model_Deposit b);

    public void delete(String depositId);

    public List<model_Deposit> getAll();
    
    public List<model_Deposit> getCari(String userDisplayname);
    
    public model_User getUser(model_Deposit b);
    
    public int getCount();
}
