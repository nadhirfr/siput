/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rheza
 */
public class model_PengeluaranUser {

    private int pengeluaran_user_id, user_id, pengeluaran_id, transaksi_id;
    private boolean pengeluaran_user_status;

    public model_PengeluaranUser() {

    }

    public model_PengeluaranUser(int pengeluaran_user_id, boolean pengeluaran_user_status, int user_id, int pengeluaran_id, int transaksi_id) {
        this.pengeluaran_user_id = pengeluaran_user_id;
        this.pengeluaran_user_status = pengeluaran_user_status;
        this.user_id = user_id;
        this.pengeluaran_id = pengeluaran_id;
        this.transaksi_id = transaksi_id;
    }

    public model_PengeluaranUser(boolean pengeluaran_user_status, int user_id, int pengeluaran_id, int transaksi_id) {
        this.pengeluaran_user_status = pengeluaran_user_status;
        this.user_id = user_id;
        this.pengeluaran_id = pengeluaran_id;
        this.transaksi_id = transaksi_id;
    }

    public int getPengeluaran_user_id() {
        return pengeluaran_user_id;
    }

    public void setPengeluaran_user_id(int pengeluaran_user_id) {
        this.pengeluaran_user_id = pengeluaran_user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPengeluaran_id() {
        return pengeluaran_id;
    }

    public void setPengeluaran_id(int pengeluaran_id) {
        this.pengeluaran_id = pengeluaran_id;
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public void setTransaksi_id(int transaksi_id) {
        this.transaksi_id = transaksi_id;
    }

    public boolean isPengeluaran_user_status() {
        return pengeluaran_user_status;
    }

    public void setPengeluaran_user_status(boolean pengeluaran_user_status) {
        this.pengeluaran_user_status = pengeluaran_user_status;
    }

}
