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
public class model_Transaksi {
    private int transaksiId, transaksiNominal,
            userId, iuranId, pengeluaranId;
    private Date transaksiDate;
    private String transaksiNama, transaksiTipe;

    public model_Transaksi() {
    }

    public model_Transaksi(int transaksiNominal, int userId, int iuranId, int pengeluaranId, Date transaksiDate, String transaksiNama, String transaksiTipe) {
        this.transaksiNominal = transaksiNominal;
        this.userId = userId;
        this.iuranId = iuranId;
        this.pengeluaranId = pengeluaranId;
        this.transaksiDate = transaksiDate;
        this.transaksiNama = transaksiNama;
        this.transaksiTipe = transaksiTipe;
    }

    public model_Transaksi(int transaksiId, int transaksiNominal, int userId, int iuranId, int pengeluaranId, Date transaksiDate, String transaksiNama, String transaksiTipe) {
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

    public int getIuranId() {
        return iuranId;
    }

    public void setIuranId(int iuranId) {
        this.iuranId = iuranId;
    }

    public int getPengeluaranId() {
        return pengeluaranId;
    }

    public void setPengeluaranId(int pengeluaranId) {
        this.pengeluaranId = pengeluaranId;
    }

    public Date getTransaksiDate() {
        return transaksiDate;
    }

    public void setTransaksiDate(Date transaksiDate) {
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
