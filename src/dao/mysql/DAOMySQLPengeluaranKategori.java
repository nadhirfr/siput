/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.implementPengeluaranKategori;
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
import object.PengeluaranKategori;

/**
 *
 * @author fachrul
 */
public class DAOMySQLPengeluaranKategori implements implementPengeluaranKategori{

    Connection connection;
    final String insert = "INSERT INTO pengeluaran_kategori (pengeluaran_kategori_id,pengeluaran_nama,pengeluaran_waktu) VALUES (NULL,?,?);";
    final String update = "UPDATE pengeluaran_kategori SET pengeluaran_kategori_id=?, pengeluaran_nama=?, pengeluaran_waktu=? WHERE pengeluaran_kategori_id=?;";
    final String delete = "DELETE FROM pengeluaran_kategori WHERE pengeluaran_kategori_id=?;";
    final String select = "SELECT * FROM pengeluaran_kategori;";
    final String get = "SELECT * FROM pengeluaran_kategori WHERE pengeluaran_kategori_id=?;";
    final String cari = "SELECT * FROM pengeluaran_kategori WHERE pengeluaran_nama LIKE ?;";

    public DAOMySQLPengeluaranKategori() {
        connection = Koneksi.connection();
    }
    @Override
    public void insert(PengeluaranKategori b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getPengeluaran_kategori_nama());
            statement.setString(2, Integer.toString(b.getPengeluaran_kategori_waktu()));
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
    public PengeluaranKategori get(String PengeluaranKategoriId) {
        PreparedStatement statement = null;
        PengeluaranKategori kategoriIuran = new PengeluaranKategori();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, PengeluaranKategoriId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               kategoriIuran.setPengeluaran_kategori_id(rs.getInt("pengeluaran_kategori_id"));
               kategoriIuran.setPengeluaran_kategori_nama(rs.getString("pengeluaran_kategori_nama"));
               kategoriIuran.setPengeluaran_kategori_waktu(rs.getInt("pengeluaran_kategori_waktu"));
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
    public void update(PengeluaranKategori b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getPengeluaran_kategori_nama());
            statement.setString(2, Integer.toString(b.getPengeluaran_kategori_waktu()));
            statement.setString(3, Integer.toString(b.getPengeluaran_kategori_id()));
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
    public void delete(String PengeluaranKategoriId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, PengeluaranKategoriId);
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
    public List<PengeluaranKategori> getAll() {
        List<PengeluaranKategori> lb = null;
        try {
            lb = new ArrayList<PengeluaranKategori>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                PengeluaranKategori b = new PengeluaranKategori();
                b.setPengeluaran_kategori_id(rs.getInt("pengeluaran_kategori_id"));
                b.setPengeluaran_kategori_nama(rs.getString("pengeluaran_kategori_nama"));
                b.setPengeluaran_kategori_waktu(rs.getInt("pengeluaran_kategori_waktu"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<PengeluaranKategori> getCari(String PengeluaranKategoriNama) {
        List<PengeluaranKategori> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + PengeluaranKategoriNama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                PengeluaranKategori b = new PengeluaranKategori();
                b.setPengeluaran_kategori_id(rs.getInt("pengeluaran_kategori_id"));
                b.setPengeluaran_kategori_nama(rs.getString("pengeluaran_kategori_nama"));
                b.setPengeluaran_kategori_waktu(rs.getInt("pengeluaran_kategori_waktu"));
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
