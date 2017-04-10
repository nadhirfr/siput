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
import model.model_Iuran;
import model.model_IuranUser;
import model.model_Transaksi;
import model.model_User;

/**
 *
 * @author fachrul
 */
public class DAOMySQLIuranUser implements implementIuranUser{
    Connection connection;
    final String insert = "INSERT INTO iuran_user (iuran_user_id,iuran_user_status,user_id,iuran_id,transaksi_id) VALUES (NULL,?,?,?,?);";
    final String update = "UPDATE iuran_user SET iuran_user_status=?, user_id=?, iuran_id=?, transaksi_id=? WHERE iuran_user_id=?;";
    final String delete = "DELETE FROM iuran WHERE iuran_id=?;";
    final String select = "SELECT * FROM iuran_user;";
    final String get = "SELECT * FROM iuran_user WHERE iuran_user_id=?;";
    final String getUser = "SELECT * FROM user WHERE user_id=?;";
    final String getIuran = "SELECT * FROM iuran WHERE iuran_id=?;";
    final String getTransaksi = "SELECT * FROM transaksi WHERE transaksi_id=?;";
    final String cari = "SELECT * FROM iuran_user WHERE user_id LIKE ?;";

    public DAOMySQLIuranUser() {
        connection = Koneksi.connection();
    }

    @Override
    public void insert(model_IuranUser b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setBoolean(1, b.isIuranUserStatus());
            statement.setInt(2, b.getUserId());
            statement.setInt(3, b.getIuranId());
            statement.setInt(4, b.getTransaksiId());
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
    public model_IuranUser get(String IuranIuranUserId) {
        PreparedStatement statement = null;
        model_IuranUser iuranUser = new model_IuranUser();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, IuranIuranUserId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               iuranUser.setIuranUserId(rs.getInt("iuran_user_id"));
               iuranUser.setIuranUserStatus(rs.getBoolean("iuran_user_status"));
               iuranUser.setUserId(rs.getInt("user_id"));
               iuranUser.setIuranId(rs.getInt("iuran_id"));
               iuranUser.setTransaksiId(rs.getInt("transaksi_id"));
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
        return iuranUser;
    }

    @Override
    public void update(model_IuranUser b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setBoolean(1, b.isIuranUserStatus());
            statement.setInt(2, b.getUserId());
            statement.setInt(3, b.getIuranId());
            statement.setInt(4, b.getTransaksiId());
            statement.setString(5, Integer.toString(b.getIuranUserId()));
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
    public void delete(String IuranUserId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, IuranUserId);
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
    public List<model_IuranUser> getAll() {
        List<model_IuranUser> lb = null;
        try {
            lb = new ArrayList<model_IuranUser>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                model_IuranUser b = new model_IuranUser();
                b.setIuranUserId(rs.getInt("iuran_user_id"));
                b.setIuranUserStatus(rs.getBoolean("iuran_user_status"));
                b.setUserId(rs.getInt("user_id"));
                b.setIuranId(rs.getInt("iuran_id"));
                b.setTransaksiId(rs.getInt("transaksi_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<model_IuranUser> getCari(int userId) {
        List<model_IuranUser> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + userId + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model_IuranUser b = new model_IuranUser();
                b.setIuranUserId(rs.getInt("iuran_user_id"));
                b.setIuranUserStatus(rs.getBoolean("iuran_user_status"));
                b.setUserId(rs.getInt("user_id"));
                b.setIuranId(rs.getInt("iuran_id"));
                b.setTransaksiId(rs.getInt("transaksi_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public model_User getUser(model_IuranUser b) {
        PreparedStatement statement = null;
        model_User user = new model_User();
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
    public model_Iuran getIuran(model_IuranUser b) {
        PreparedStatement statement = null;
        model_Iuran iuran = new model_Iuran();
        try {
            statement = connection.prepareStatement(get);
            statement.setInt(1, b.getIuranId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               iuran.setIuranId(rs.getInt("iuran_id"));
                iuran.setIuranNama(rs.getString("iuran_nama"));
                iuran.setIuranNominal(rs.getInt("iuran_nominal"));
                iuran.setIuranJenisId(rs.getInt("iuran_jenis_id"));
                iuran.setIuranKategoriId(rs.getInt("iuran_kategori_id"));
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
        return iuran;
    }

    @Override
    public model_Transaksi getTransaksi(model_IuranUser b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
