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
import model.JenisIuran;
import model.KategoriIuran;

/**
 *
 * @author fachrul
 */
public class DAOMySQLIuran implements implementIuran{
    Connection connection;
    final String insert = "INSERT INTO iuran (iuran_id,iuran_nama,iuran_nominal,iuran_jenis_id,iuran_kategori_id) VALUES (NULL,?,?,?,?);";
    final String update = "UPDATE iuran SET iuran_nama=?, iuran_nominal=?, iuran_jenis_id=?, iuran_kategori_id=? WHERE iuran_id=?;";
    final String delete = "DELETE FROM iuran WHERE iuran_id=?;";
    final String select = "SELECT * FROM iuran;";
    final String get = "SELECT * FROM iuran WHERE iuran_id=?;";
    final String getJenisIuran = "SELECT * FROM iuran_jenis WHERE iuran_jenis_id=?;";
    final String getKategoriIuran = "SELECT * FROM iuran_kategori WHERE iuran_kategori_id=?;";
    final String cari = "SELECT * FROM iuran WHERE iuran_nama LIKE ?;";

    public DAOMySQLIuran() {
        connection = Koneksi.connection();
    }

    @Override
    public int insert(Iuran b) {
        PreparedStatement statement = null;
        int inserted_id = 0;
        try {
            statement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getIuranNama());
            statement.setInt(2, b.getIuranNominal());
            statement.setInt(3, b.getIuranJenisId());
            statement.setInt(4, b.getIuranKategoriId());
            //statement.executeUpdate();
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inserted_id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
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
        return inserted_id;
    }

    @Override
    public Iuran get(String IuranId) {
        PreparedStatement statement = null;
        Iuran iuran = new Iuran();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, IuranId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               iuran.setIuranId(Integer.parseInt(rs.getString("iuran_id")));
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
    public void update(Iuran b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getIuranNama());
            statement.setInt(2, b.getIuranNominal());
            statement.setInt(3, b.getIuranJenisId());
            statement.setInt(4, b.getIuranKategoriId());
            statement.setString(5, Integer.toString(b.getIuranId()));
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
    public void delete(String IuranId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, IuranId);
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
    public List<Iuran> getAll() {
        List<Iuran> lb = null;
        try {
            lb = new ArrayList<Iuran>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Iuran b = new Iuran();
                b.setIuranId(rs.getInt("iuran_id"));
                b.setIuranNama(rs.getString("iuran_nama"));
                b.setIuranNominal(rs.getInt("iuran_nominal"));
                b.setIuranJenisId(rs.getInt("iuran_jenis_id"));
                b.setIuranKategoriId(rs.getInt("iuran_kategori_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<Iuran> getCari(String IuranNama) {
        List<Iuran> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + IuranNama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Iuran b = new Iuran();
                b.setIuranId(rs.getInt("iuran_id"));
                b.setIuranNama(rs.getString("iuran_nama"));
                b.setIuranNominal(rs.getInt("iuran_nominal"));
                b.setIuranJenisId(rs.getInt("iuran_jenis_id"));
                b.setIuranKategoriId(rs.getInt("iuran_kategori_id"));
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

    @Override
    public JenisIuran getJenisIuran(Iuran b) {
        PreparedStatement statement = null;
        JenisIuran jenisIuran = new JenisIuran();
        try {
            statement = connection.prepareStatement(get);
            statement.setInt(1, b.getIuranJenisId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               jenisIuran.setIuranJenisId(rs.getInt("iuran_jenis_id"));
               jenisIuran.setIuranJenisNama(rs.getString("iuran_jenis_nama"));
               jenisIuran.setIuranJenisKeterangan(rs.getString("iuran_jenis_keterangan"));
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
        return jenisIuran;
    }

    @Override
    public KategoriIuran getKategoriIuran(Iuran b) {
        PreparedStatement statement = null;
        KategoriIuran kategoriIuran = new KategoriIuran();
        try {
            statement = connection.prepareStatement(get);
            statement.setInt(1, b.getIuranJenisId());
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
}
