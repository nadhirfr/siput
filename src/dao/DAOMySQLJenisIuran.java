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
import model.model_JenisIuran;

/**
 *
 * @author fachrul
 */
public class DAOMySQLJenisIuran implements implementJenisIuran{

    Connection connection;
    final String insert = "INSERT INTO iuran_jenis (iuran_jenis_id,iuran_jenis_nama,iuran_jenis_keterangan) VALUES (NULL,?,?);";
    final String update = "UPDATE iuran_jenis SET iuran_jenis_id=?, iuran_jenis_nama=?, iuran_jenis_keterangan=? WHERE iuran_jenis_id=?;";
    final String delete = "DELETE FROM iuran_jenis WHERE iuran_jenis_id=?;";
    final String select = "SELECT * FROM iuran_jenis;";
    final String get = "SELECT * FROM iuran_jenis WHERE iuran_jenis_id=?;";
    final String cari = "SELECT * FROM iuran_jenis WHERE iuran_jenis_nama LIKE ?;";

    public DAOMySQLJenisIuran() {
        connection = Koneksi.connection();
    }
    @Override
    public void insert(model_JenisIuran b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getIuranJenisNama());
            statement.setString(2, b.getIuranJenisKeterangan());
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
    public model_JenisIuran get(String JenisIuranId) {
        PreparedStatement statement = null;
        model_JenisIuran jenisIuran = new model_JenisIuran();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, JenisIuranId);
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
    public void update(model_JenisIuran b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getIuranJenisNama());
            statement.setString(2, b.getIuranJenisKeterangan());
            statement.setString(3, Integer.toString(b.getIuranJenisId()));
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
    public void delete(String JenisIuranId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, JenisIuranId);
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
    public List<model_JenisIuran> getAll() {
        List<model_JenisIuran> lb = null;
        try {
            lb = new ArrayList<model_JenisIuran>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                model_JenisIuran b = new model_JenisIuran();
                b.setIuranJenisId(rs.getInt("iuran_jenis_id"));
                b.setIuranJenisNama(rs.getString("iuran_jenis_nama"));
                b.setIuranJenisKeterangan(rs.getString("iuran_jenis_keterangan"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<model_JenisIuran> getCari(String JenisIuranNama) {
        List<model_JenisIuran> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + JenisIuranNama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model_JenisIuran b = new model_JenisIuran();
                b.setIuranJenisId(rs.getInt("iuran_jenis_id"));
                b.setIuranJenisNama(rs.getString("iuran_jenis_nama"));
                b.setIuranJenisKeterangan(rs.getString("iuran_jenis_keterangan"));
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
