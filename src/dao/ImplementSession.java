/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.model_Session;

/**
 *
 * @author rheza
 */
public interface ImplementSession {
        public void insert(model_Session b);

    public model_Session get(String SessionId);

    public void update(model_Session b);

    public void delete(String SessionId);

    public List<model_Session> getAll();

    public int getCount();
}
