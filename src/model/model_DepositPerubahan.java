/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author rheza
 */
public class model_DepositPerubahan {

    private int deposit_perubahan_id, deposit_id, transaksi_id;
    private Date deposit_perubahan_date;

    public model_DepositPerubahan() {

    }

    public model_DepositPerubahan(int deposit_perubahan_id, Date deposit_perubahan_date, int deposit_id, int transaksi_id) {
        this.deposit_perubahan_id = deposit_perubahan_id;
        this.deposit_perubahan_date = deposit_perubahan_date;
        this.deposit_id = deposit_id;
        this.transaksi_id = transaksi_id;
    }

    public model_DepositPerubahan(Date deposit_perubahan_date, int deposit_id, int transaksi_id) {
        this.deposit_perubahan_date = deposit_perubahan_date;
        this.deposit_id = deposit_id;
        this.transaksi_id = transaksi_id;
    }

    public int getDeposit_perubahan_id() {
        return deposit_perubahan_id;
    }

    public void setDeposit_perubahan_id(int deposit_perubahan_id) {
        this.deposit_perubahan_id = deposit_perubahan_id;
    }

    public int getDeposit_id() {
        return deposit_id;
    }

    public void setDeposit_id(int deposit_id) {
        this.deposit_id = deposit_id;
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public void setTransaksi_id(int transaksi_id) {
        this.transaksi_id = transaksi_id;
    }

    public Date getDeposit_perubahan_date() {
        return deposit_perubahan_date;
    }

    public void setDeposit_perubahan_date(Date deposit_perubahan_date) {
        this.deposit_perubahan_date = deposit_perubahan_date;
    }

}
