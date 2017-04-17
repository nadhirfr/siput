/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author fachrul
 */
public class Transaksi {
    private int transaksiId, transaksiNominal,
            userId;
    private String transaksiNama, transaksiTipe,transaksiDate,iuranId,pengeluaranId;

    public Transaksi() {
    }

    public Transaksi(int transaksiNominal, int userId, String iuranId, String pengeluaranId, String transaksiDate, String transaksiNama, String transaksiTipe) {
        this.transaksiNominal = transaksiNominal;
        this.userId = userId;
        this.iuranId = iuranId;
        this.pengeluaranId = pengeluaranId;
        this.transaksiDate = transaksiDate;
        this.transaksiNama = transaksiNama;
        this.transaksiTipe = transaksiTipe;
    }

    public Transaksi(int transaksiId, int transaksiNominal, int userId, String iuranId, String pengeluaranId, String transaksiDate, String transaksiNama, String transaksiTipe) {
        this.transaksiId = transaksiId;
        this.transaksiNominal = transaksiNominal;
        this.userId = userId;
        this.iuranId = iuranId;
        this.pengeluaranId = pengeluaranId;
        this.transaksiDate = transaksiDate;
        this.transaksiNama = transaksiNama;
        this.transaksiTipe = transaksiTipe;
    }

    public int getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(int transaksiId) {
        this.transaksiId = transaksiId;
    }

    public int getTransaksiNominal() {
        return transaksiNominal;
    }

    public void setTransaksiNominal(int transaksiNominal) {
        this.transaksiNominal = transaksiNominal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIuranId() {
        return iuranId;
    }

    public void setIuranId(String iuranId) {
        this.iuranId = iuranId;
    }

    public String getPengeluaranId() {
        return pengeluaranId;
    }

    public void setPengeluaranId(String pengeluaranId) {
        this.pengeluaranId = pengeluaranId;
    }

    public String getTransaksiDate() {
        return transaksiDate;
    }

    public void setTransaksiDate(String transaksiDate) {
        this.transaksiDate = transaksiDate;
    }

    public String getTransaksiNama() {
        return transaksiNama;
    }

    public void setTransaksiNama(String transaksiNama) {
        this.transaksiNama = transaksiNama;
    }

    public String getTransaksiTipe() {
        return transaksiTipe;
    }

    public void setTransaksiTipe(String transaksiTipe) {
        this.transaksiTipe = transaksiTipe;
    }
    
    
}
