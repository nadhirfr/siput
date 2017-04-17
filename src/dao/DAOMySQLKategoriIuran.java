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
import model.KategoriIuran;

/**
 *
 * @author fachrul
 */
public class DAOMySQLKategoriIuran implements implementKategoriIuran{

    Connection connection;
    final String insert = "INSERT INTO iuran_kategori (iuran_kategori_id,iuran_kategori_nama,iuran_kategori_interval) VALUES (NULL,?,?);";
    final String update = "UPDATE iuran_kategori SET iuran_kategori_id=?, iuran_kategori_nama=?, iuran_kategori_interval=? WHERE iuran_kategori_id=?;";
    final String delete = "DELETE FROM iuran_kategori WHERE iuran_kategori_id=?;";
    final String select = "SELECT * FROM iuran_kategori;";
    final String get = "SELECT * FROM iuran_kategori WHERE iuran_kategori_id=?;";
    final String cari = "SELECT * FROM iuran_kategori WHERE iuran_kategori_nama LIKE ?;";

    public DAOMySQLKategoriIuran() {
        connection = Koneksi.connection();
    }
    @Override
    public void insert(KategoriIuran b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getIuranKategoriNama());
            statement.setInt(2, b.getIuranKategoriInterval());
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
    public KategoriIuran get(String KategoriIuranId) {
        PreparedStatement statement = null;
        KategoriIuran kategoriIuran = new KategoriIuran();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, KategoriIuranId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               kategoriIuran.setIuranKategoriId(rs.getInt("iuran_kategori_id"));
               kategoriIuran.setIuranKategoriNama(rs.getString("iuran_kategori_nama"));
               kategoriIuran.setIuranKategoriInterval(rs.getInt("iuran_kategori_interval"));
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
        return kategoriIuran;
    }

    @Override
    public void update(KategoriIuran b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getIuranKategoriNama());
            statement.setInt(2, b.getIuranKategoriInterval());
            statement.setString(3, Integer.toString(b.getIuranKategoriId()));
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
    public void delete(String KategoriIuranId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, KategoriIuranId);
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
    public List<KategoriIuran> getAll() {
        List<KategoriIuran> lb = null;
        try {
            lb = new ArrayList<KategoriIuran>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                KategoriIuran b = new KategoriIuran();
                b.setIuranKategoriId(rs.getInt("iuran_kategori_id"));
                b.setIuranKategoriNama(rs.getString("iuran_kategori_nama"));
                b.setIuranKategoriInterval(rs.getInt("iuran_kategori_interval"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<KategoriIuran> getCari(String KategoriIuranNama) {
        List<KategoriIuran> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + KategoriIuranNama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                KategoriIuran b = new KategoriIuran();
                b.setIuranKategoriId(rs.getInt("iuran_kategori_id"));
                b.setIuranKategoriNama(rs.getString("iuran_kategori_nama"));
                b.setIuranKategoriInterval(rs.getInt("iuran_kategori_interval"));
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
