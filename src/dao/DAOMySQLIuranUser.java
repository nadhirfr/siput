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
import model.Iuran;
import model.IuranUser;
import model.User;

/**
 *
 * @author fachrul
 */
public class DAOMySQLIuranUser implements implementIuranUser{
    Connection connection;
    final String insert = "INSERT INTO iuran_user (iuran_user_id,iuran_user_status,user_id,iuran_id) VALUES (NULL,?,?,?);";
    final String update = "UPDATE iuran_user SET iuran_user_status=?, user_id=?, iuran_id=? WHERE iuran_user_id=?;";
    final String delete = "DELETE FROM iuran_user WHERE iuran_user_id=?;";
    final String select = "SELECT * FROM iuran_user;";
    final String get = "SELECT * FROM iuran_user WHERE iuran_user_id=?;";
    final String getByUserAndIuran = "SELECT * FROM iuran_user WHERE user_id=? AND iuran_id=?;";
    final String getUser = "SELECT * FROM user WHERE user_id=?;";
    final String getIuran = "SELECT * FROM iuran WHERE iuran_id=?;";
    final String cari = "SELECT * FROM iuran_user WHERE user_id LIKE ?;";

    public DAOMySQLIuranUser() {
        connection = Koneksi.connection();
    }

    @Override
    public void insert(IuranUser b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setInt(1, b.isIuranUserStatus());
            statement.setInt(2, b.getUserId());
            statement.setInt(3, b.getIuranId());
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
    public IuranUser get(String IuranIuranUserId) {
        PreparedStatement statement = null;
        IuranUser iuranUser = new IuranUser();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, IuranIuranUserId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               iuranUser.setIuranUserId(rs.getInt("iuran_user_id"));
               iuranUser.setIuranUserStatus(rs.getInt("iuran_user_status"));
               iuranUser.setUserId(rs.getInt("user_id"));
               iuranUser.setIuranId(rs.getInt("iuran_id"));
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
    public void update(IuranUser b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setInt(1, b.isIuranUserStatus());
            statement.setInt(2, b.getUserId());
            statement.setInt(3, b.getIuranId());
            statement.setString(4, Integer.toString(b.getIuranUserId()));
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
    public List<IuranUser> getAll() {
        List<IuranUser> lb = null;
        try {
            lb = new ArrayList<IuranUser>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                IuranUser b = new IuranUser();
                b.setIuranUserId(rs.getInt("iuran_user_id"));
                b.setIuranUserStatus(rs.getInt("iuran_user_status"));
                b.setUserId(rs.getInt("user_id"));
                b.setIuranId(rs.getInt("iuran_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<IuranUser> getCari(int userId) {
        List<IuranUser> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + userId + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                IuranUser b = new IuranUser();
                b.setIuranUserId(rs.getInt("iuran_user_id"));
                b.setIuranUserStatus(rs.getInt("iuran_user_status"));
                b.setUserId(rs.getInt("user_id"));
                b.setIuranId(rs.getInt("iuran_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public User getUser(IuranUser b) {
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
    public Iuran getIuran(IuranUser b) {
        PreparedStatement statement = null;
        Iuran iuran = new Iuran();
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
    public IuranUser getByUserAndIuran(User u, Iuran i) {
        PreparedStatement statement = null;
        IuranUser iuranUser = new IuranUser();
        try {
            statement = connection.prepareStatement(getByUserAndIuran);
            statement.setInt(1, u.getUser_id());
            statement.setInt(2, i.getIuranId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               iuranUser.setIuranUserId(rs.getInt("iuran_user_id"));
               iuranUser.setIuranUserStatus(rs.getInt("iuran_user_status"));
               iuranUser.setUserId(rs.getInt("user_id"));
               iuranUser.setIuranId(rs.getInt("iuran_id"));
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
    
}
