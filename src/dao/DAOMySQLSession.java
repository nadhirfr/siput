/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.Koneksi;
import model.Session;

/**
 *
 * @author fachrul
 */
public class DAOMySQLSession implements ImplementSession{
    Connection connection;
    final String insert = "INSERT INTO session (session_id,user_id,session_time) VALUES (NULL,?,?);";
    final String update = "UPDATE session SET user_id=?, session_time=? WHERE session_id=?;";
    final String delete = "DELETE FROM session WHERE session_id=?;";
    final String select = "SELECT * FROM session;";
    final String get = "SELECT * FROM session WHERE session_id=?;";

    public DAOMySQLSession() {
        connection = Koneksi.connection();
    }
    
    @Override
    public void insert(Session b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, String.valueOf(b.getUser_id()));
            statement.setString(2, b.getSession_time());
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Session get(String SessionId) {
    PreparedStatement statement = null;
        Session session = new Session();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, SessionId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               session.setSession_id(Integer.parseInt(rs.getString("session_id")));
               session.setSession_time(rs.getString("session_time"));
               session.setUser_id(rs.getInt("user_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }    
        return session;
    }

    @Override
    public void update(Session b) {
    PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setInt(1, b.getUser_id());
            statement.setString(2, b.getSession_time());
            statement.setInt(3, b.getSession_id());;
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String SessionId) {
    PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, SessionId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Session> getAll() {
    List<Session> lb = null;
        try {
            lb = new ArrayList<>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Session b = new Session();
                b.setSession_id(rs.getInt("session_id"));
                b.setSession_time(rs.getString("session_time"));
                b.setUser_id(rs.getInt("user_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public int getCount() {
    int lb = 0;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            rs.last();
            lb = rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
    
}
