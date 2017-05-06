/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.implementDeposit;
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
import object.Deposit;
import object.User;

/**
 *
 * @author fachrul
 */
public class DAOMySQLDeposit implements implementDeposit{

    Connection connection;
    final String insert = "INSERT INTO deposit (deposit_id,user_id,deposit_jumlah) VALUES (NULL,?,?);";
    final String update = "UPDATE deposit SET user_id=?, deposit_jumlah=? WHERE deposit_id=?;";
    final String delete = "DELETE FROM deposit WHERE deposit_id=?;";
    final String select = "SELECT * FROM deposit;";
    final String get = "SELECT * FROM deposit WHERE deposit_id=?;";
    final String getbyUser = "SELECT * FROM deposit WHERE user_id=?;";
    final String getUser = "SELECT * FROM user WHERE user_id=?;";
    final String cari = "SELECT * FROM deposit WHERE user_id LIKE ?;";

    public DAOMySQLDeposit() {
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Deposit b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setInt(1, b.getUserId());
            statement.setInt(2, b.getDepositJumlah());
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
    public Deposit get(String depositId) {
        PreparedStatement statement = null;
        Deposit deposit = new Deposit();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, depositId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               deposit.setDepositId(rs.getInt("deposit_id"));
               deposit.setUserId(rs.getInt("user_id"));
               deposit.setDepositJumlah(rs.getInt("deposit_jumlah"));
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
        return deposit;
    }

    @Override
    public void update(Deposit b) {
    PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setInt(1, b.getUserId());
            statement.setInt(2, b.getDepositJumlah());
            statement.setInt(3, b.getDepositId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }}

    @Override
    public void delete(String depositId) {
    PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, depositId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }}

    @Override
    public List<Deposit> getAll() {
    List<Deposit> lb = null;
        try {
            lb = new ArrayList<Deposit>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Deposit b = new Deposit();
                b.setDepositId(rs.getInt("deposit_id"));
               b.setUserId(rs.getInt("user_id"));
               b.setDepositJumlah(rs.getInt("deposit_jumlah"));
               lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;}

    @Override
    public List<Deposit> getCari(String userDisplayname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(Deposit b) {
    PreparedStatement statement = null;
        User user = new User();
        try {
            statement = connection.prepareStatement(get);
            statement.setInt(1, b.getUserId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               user.setUser_id(rs.getInt("user_id"));
               user.setUser_username(rs.getString("user_username"));
               user.setUser_displayname(rs.getString("user_displayname"));
               user.setUser_password(rs.getString("user_password"));
               user.setUser_password(rs.getString("user_password"));
               user.setUser_tipe(rs.getString("user_tipe"));
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
        return user;
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

    @Override
    public Deposit getByUser(User b) {
    PreparedStatement statement = null;
        Deposit deposit = new Deposit();
        try {
            statement = connection.prepareStatement(getbyUser);
            statement.setInt(1, b.getUser_id());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               deposit.setDepositId(rs.getInt("deposit_id"));
               deposit.setUserId(rs.getInt("user_id"));
               deposit.setDepositJumlah(rs.getInt("deposit_jumlah"));
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
        return deposit;}
    
}
