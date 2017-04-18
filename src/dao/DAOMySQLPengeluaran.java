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
import model.Pengeluaran;
import model.PengeluaranJenis;
import model.PengeluaranKategori;

/**
 *
 * @author fachrul
 */
public class DAOMySQLPengeluaran implements implementPengeluaran{
    Connection connection;
    final String insert = "INSERT INTO pengeluaran (pengeluaran_id,pengeluaran_nama,pengeluaran_jenis_id,pengeluaran_kategori_id) VALUES (NULL,?,?,?);";
    final String update = "UPDATE pengeluaran SET pengeluaran_nama=?, pengeluaran_jenis_id=?, pengeluaran_kategori_id=? WHERE pengeluaran_id=?;";
    final String delete = "DELETE FROM pengeluaran WHERE pengeluaran_id=?;";
    final String select = "SELECT * FROM pengeluaran;";
    final String get = "SELECT * FROM pengeluaran WHERE pengeluaran_id=?;";
    final String getPengeluaranJenis = "SELECT * FROM pengeluaran_jenis WHERE pengeluaran_jenis_id=?;";
    final String getPengeluaranKategori = "SELECT * FROM pengeluaran_kategori WHERE pengeluaran_kategori_id=?;";
    final String cari = "SELECT * FROM pengeluaran WHERE pengeluaran_nama LIKE ?;";

    public DAOMySQLPengeluaran() {
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Pengeluaran b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getPengeluaran_nama());
            statement.setInt(3, b.getPengeluaran_jenis_id());
            statement.setInt(4, b.getPengeluaran_kategori_id());
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
    public Pengeluaran get(String PengeluaranId) {
        PreparedStatement statement = null;
        Pengeluaran pengeluaran = new Pengeluaran();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, PengeluaranId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               pengeluaran.setPengeluaran_id(Integer.parseInt(rs.getString("pengeluaran_id")));
               pengeluaran.setPengeluaran_nama(rs.getString("pengeluaran_nama"));
               pengeluaran.setPengeluaran_jenis_id(rs.getInt("pengeluaran_jenis_id"));
               pengeluaran.setPengeluaran_kategori_id(rs.getInt("pengeluaran_kategori_id"));
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
        return pengeluaran;

    }

    @Override
    public void update(Pengeluaran b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getPengeluaran_nama());
            statement.setInt(3, b.getPengeluaran_jenis_id());
            statement.setInt(4, b.getPengeluaran_kategori_id());
            statement.setString(5, Integer.toString(b.getPengeluaran_id()));
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
    public void delete(String PengeluaranId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, PengeluaranId);
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
    public List<Pengeluaran> getAll() {
        List<Pengeluaran> lb = null;
        try {
            lb = new ArrayList<Pengeluaran>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Pengeluaran b = new Pengeluaran();
                b.setPengeluaran_id(rs.getInt("pengeluaran_id"));
                b.setPengeluaran_nama(rs.getString("pengeluaran_nama"));
                b.setPengeluaran_jenis_id(rs.getInt("pengeluaran_jenis_id"));
                b.setPengeluaran_kategori_id(rs.getInt("pengeluaran_kategori_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<Pengeluaran> getCari(String PengeluaranNama) {
        List<Pengeluaran> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + PengeluaranNama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pengeluaran b = new Pengeluaran();
                b.setPengeluaran_id(rs.getInt("pengeluaran_id"));
                b.setPengeluaran_nama(rs.getString("pengeluaran_nama"));
                b.setPengeluaran_jenis_id(rs.getInt("pengeluaran_jenis_id"));
                b.setPengeluaran_kategori_id(rs.getInt("pengeluaran_kategori_id"));
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

    //@Override
    public PengeluaranJenis getPengeluaranJenis(Pengeluaran b) {
        PreparedStatement statement = null;
        PengeluaranJenis jenisPengeluaran = new PengeluaranJenis();
        try {
            statement = connection.prepareStatement(get);
            statement.setInt(1, b.getPengeluaran_jenis_id());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               jenisPengeluaran.setPengeluaran_jenis_id(rs.getInt("pengeluaran_jenis_id"));
               jenisPengeluaran.setPengeluaran_nama(rs.getString("pengeluaran_nama"));
               jenisPengeluaran.setPengeluaran_keterangan(rs.getString("pengeluaran_keterangan"));
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
        return jenisPengeluaran;
    }

    //@Override
    public PengeluaranKategori getPengeluaranKategori(Pengeluaran b) {
        PreparedStatement statement = null;
        PengeluaranKategori kategoriPengeluaran = new PengeluaranKategori();
        try {
            statement = connection.prepareStatement(get);
            statement.setInt(1, b.getPengeluaran_jenis_id());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               kategoriPengeluaran.setPengeluaran_kategori_id(rs.getInt("pengeluaran_kategori_id"));
               kategoriPengeluaran.setPengeluaran_kategori_nama(rs.getString("pengeluaran_kategori_nama"));
               kategoriPengeluaran.setPengeluaran_kategori_waktu(rs.getInt("pengeluaran_kategori_waktu"));
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
        return kategoriPengeluaran;
    }
}
