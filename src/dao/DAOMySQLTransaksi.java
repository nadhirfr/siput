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
import model.Pengeluaran;
import model.Transaksi;
import model.User;

/**
 *
 * @author fachrul
 */
public class DAOMySQLTransaksi implements implementTransaksi{

    Connection connection;
    final String insert = "INSERT INTO transaksi (transaksi_id,transaksi_date,transaksi_nama,"
            + "transaksi_nominal,user_id,transaksi_tipe,iuran_id,pengeluaran_id) "
            + "VALUES (NULL,?,?,?,?,?,?,?);";
    final String update = "UPDATE transaksi SET transaksi_date=?, transaksi_nama=?, transaksi_nominal=?, "
            + "user_id=?, transaksi_tipe=?, iuran_id=?, pengeluaran_id=?  WHERE transaksi_id=?;";
    final String delete = "DELETE FROM transaksi WHERE transaksi_id=?;";
    final String select = "SELECT * FROM transaksi;";
    final String get = "SELECT * FROM transaksi WHERE transaksi_id=?;";
    final String getUser = "SELECT * FROM user WHERE user_id=?;";
    final String getIuran = "SELECT * FROM iuran WHERE iuran_id=?;";
    final String getPengeluaran = "SELECT * FROM pengeluaran WHERE pengeluaran_id=?;";
    final String cari = "SELECT * FROM transaksi WHERE transaksi_nama LIKE ?;";
    final String jumlahtransaksi = "SELECT sum(transaksi_nominal) FROM transaksi; ";

    public DAOMySQLTransaksi() {
        connection = Koneksi.connection();
    }
    @Override
    public void insert(Transaksi b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getTransaksiDate().toString());
            statement.setString(2, b.getTransaksiNama());
            statement.setInt(3, b.getTransaksiNominal());
            statement.setInt(4, b.getUserId());
            statement.setString(5, b.getTransaksiTipe());
            statement.setString(6, b.getIuranId());
            statement.setString(7, b.getPengeluaranId());
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
    public Transaksi get(String transaksiId) {
        PreparedStatement statement = null;
        Transaksi transaksi = new Transaksi();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, transaksiId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               transaksi.setTransaksiId(rs.getInt("transaksi_id"));
               transaksi.setTransaksiDate(rs.getString("transaksi_date"));
               transaksi.setTransaksiNama(rs.getString("transaksi_nama"));
               transaksi.setTransaksiNominal(rs.getInt("transaksi_nominal"));
               transaksi.setUserId(rs.getInt("user_id"));
               transaksi.setTransaksiTipe(rs.getString("transaksi_tipe"));
               transaksi.setIuranId(rs.getString("iuran_id"));
               transaksi.setPengeluaranId(rs.getString("pengeluaran_id"));
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
        return transaksi;

    }

    @Override
    public void update(Transaksi b) {
    PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getTransaksiDate().toString());
            statement.setString(2, b.getTransaksiNama());
            statement.setInt(3, b.getTransaksiNominal());
            statement.setInt(4, b.getUserId());
            statement.setString(5, b.getTransaksiTipe());
            statement.setString(6, b.getIuranId());
            statement.setString(7, b.getPengeluaranId());
            statement.setInt(8, b.getTransaksiId());
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
    public void delete(String transaksiId) {
    PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, transaksiId);
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
    public List<Transaksi> getAll() {
    List<Transaksi> lb = null;
        try {
            lb = new ArrayList<Transaksi>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Transaksi b = new Transaksi();
                 b.setTransaksiId(rs.getInt("transaksi_id"));
               b.setTransaksiDate(rs.getString("transaksi_date"));
               b.setTransaksiNama(rs.getString("transaksi_nama"));
               b.setTransaksiNominal(rs.getInt("transaksi_nominal"));
               b.setUserId(rs.getInt("user_id"));
               b.setTransaksiTipe(rs.getString("transaksi_tipe"));
               b.setIuranId(rs.getString("iuran_id"));
               b.setPengeluaranId(rs.getString("pengeluaran_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;}

    @Override
    public List<Transaksi> getCari(String IuranNama) {
    List<Transaksi> lb = null;
        try {
            lb = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + IuranNama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Transaksi b = new Transaksi();
                b.setTransaksiId(rs.getInt("transaksi_id"));
               b.setTransaksiDate(rs.getString("transaksi_date"));
               b.setTransaksiNama(rs.getString("transaksi_nama"));
               b.setTransaksiNominal(rs.getInt("transaksi_nominal"));
               b.setUserId(rs.getInt("user_id"));
               b.setTransaksiTipe(rs.getString("transaksi_tipe"));
               b.setIuranId(rs.getString("iuran_id"));
               b.setPengeluaranId(rs.getString("pengeluaran_id"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;}

    @Override
    public User getUser(Transaksi b) {
    PreparedStatement statement = null;
        User user = new User();
        try {
            statement = connection.prepareStatement(getUser);
            statement.setInt(1, b.getUserId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               user.setUser_id(Integer.parseInt(rs.getString("user_id")));
               user.setUser_username(rs.getString("user_username"));
               user.setUser_displayname(rs.getString("user_displayname"));
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
    public Iuran getIuran(Transaksi b) {
        PreparedStatement statement = null;
        Iuran iuran = new Iuran();
        try {
            statement = connection.prepareStatement(getIuran);
            statement.setString(1, b.getIuranId());
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
    public Pengeluaran getPengeluaran(Transaksi b) {
    PreparedStatement statement = null;
        Pengeluaran pengeluaran = new Pengeluaran();
        try {
            statement = connection.prepareStatement(getPengeluaran);
            statement.setString(1, b.getPengeluaranId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               pengeluaran.setPengeluaran_id(rs.getInt("pengeluaran_id"));
               pengeluaran.setPengeluaran_jenis_id(rs.getInt("pengeluaran_jenis_id"));
               pengeluaran.setPengeluaran_kategori_id(rs.getInt("pengeluaran_kategori_id"));
               pengeluaran.setPengeluaran_nama(rs.getString("pengeluaran_nama"));
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
        return lb;}
    
    
    public int getIuranNominal(){
        return 0;     
    }
    
}
