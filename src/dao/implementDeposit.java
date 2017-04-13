/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Deposit;
import model.User;

/**
 *
 * @author fachrul
 */
public interface implementDeposit {
    public void insert(Deposit b);
    
    public Deposit get(String depositId);
    
    public Deposit getByUser(User b);

    public void update(Deposit b);

    public void delete(String depositId);

    public List<Deposit> getAll();
    
    public List<Deposit> getCari(String userDisplayname);
    
    public User getUser(Deposit b);
    
    public int getCount();
}
