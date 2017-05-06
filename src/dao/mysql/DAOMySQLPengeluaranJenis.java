/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.implementPengeluaranJenis;
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
import object.PengeluaranJenis;

/**
 *
 * @author fachrul
 */
public class DAOMySQLPengeluaranJenis implements implementPengeluaranJenis{

    Connection connection;
    final String insert = "INSERT INTO pengeluaran_jenis (pengeluaran_jenis_id,pengeluaran_nama,pengeluaran_keterangan) VALUES (NULL,?,?);";
    final String update = "UPDATE pengeluaran_jenis SET pengeluaran_jenis_id=?, pengeluaran_nama=?, pengeluaran_keterangan=? WHERE pengeluaran_jenis_id=?;";
    final String delete = "DELETE FROM pengeluaran_jenis WHERE pengeluaran_jenis_id=?;";
    final String select = "SELECT * FROM pengeluaran_jenis;";
    final String get = "SELECT * FROM pengeluaran_jenis WHERE pengeluaran_jenis_id=?;";
    final String cari = "SELECT * FROM pengeluaran_jenis WHERE pengeluaran_nama LIKE ?;";

    public DAOMySQLPengeluaranJenis() {
        connection = Koneksi.connection();
    }
    @Override
    public void insert(PengeluaranJenis b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getPengeluaran_nama());
            statement.setString(2, b.getPengeluaran_keterangan());
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
    public PengeluaranJenis get(String PengeluaranJenisId) {
        PreparedStatement statement = null;
        PengeluaranJenis jenisIuran = new PengeluaranJenis();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, PengeluaranJenisId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               jenisIuran.setPengeluaran_jenis_id(rs.getInt("pengeluaran_jenis_id"));
               jenisIuran.setPengeluaran_nama(rs.getString("pengeluaran_nama"));
               jenisIuran.setPengeluaran_keterangan(rs.getString("pengeluaran_keterangan"));
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
    public void update(PengeluaranJenis b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getPengeluaran_nama());
            statement.setString(2, b.getPengeluaran_keterangan());
            statement.setString(3, Integer.toString(b.getPengeluaran_jenis_id()));
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
    public void delete(String PengeluaranJenisId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, PengeluaranJenisId);
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
    public List<PengeluaranJenis> getAll() {
        List<PengeluaranJenis> lb = null;
        try {
            lb = new ArrayList<PengeluaranJenis>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                PengeluaranJenis b = new PengeluaranJenis();
                b.setPengeluaran_jenis_id(rs.getInt("pengeluaran_jenis_id"));
                b.setPengeluaran_nama(rs.getString("pengeluaran_nama"));
                b.setPengeluaran_keterangan(rs.getString("pengeluaran_keterangan"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<PengeluaranJenis> getCari(String PengeluaranJenisNama) {
        List<PengeluaranJenis> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + PengeluaranJenisNama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                PengeluaranJenis b = new PengeluaranJenis();
                b.setPengeluaran_jenis_id(rs.getInt("pengeluaran_jenis_id"));
                b.setPengeluaran_nama(rs.getString("pengeluaran_nama"));
                b.setPengeluaran_keterangan(rs.getString("pengeluaran_keterangan"));
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
