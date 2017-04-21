/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Session;

/**
 *
 * @author rheza
 */
public interface ImplementSession {
        public void insert(Session b);

    public Session get(String SessionId);

    public void update(Session b);

    public void delete(String SessionId);

    public List<Session> getAll();

    public int getCount();
}
