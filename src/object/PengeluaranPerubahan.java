/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.Date;

/**
 *
 * @author rheza
 */
public class PengeluaranPerubahan {

    private int pengeluaran_perubahan_id, pengeluaran_perubahan_nominal, pengeluaran_id;
    private String pengeluaran_perubahan_date;

    public PengeluaranPerubahan() {

    }

    public PengeluaranPerubahan(int pengeluaran_perubahan_id, int pengeluaran_perubahan_nominal, String pengeluaran_perubahan_date, int pengeluaran_id) {
        this.pengeluaran_perubahan_id = pengeluaran_perubahan_id;
        this.pengeluaran_perubahan_nominal = pengeluaran_perubahan_nominal;
        this.pengeluaran_perubahan_date = pengeluaran_perubahan_date;
        this.pengeluaran_id = pengeluaran_id;
    }

    public PengeluaranPerubahan(int pengeluaran_perubahan_nominal, String pengeluaran_perubahan_date, int pengeluaran_id) {
        this.pengeluaran_perubahan_nominal = pengeluaran_perubahan_nominal;
        this.pengeluaran_perubahan_date = pengeluaran_perubahan_date;
        this.pengeluaran_id = pengeluaran_id;
    }

    public int getPengeluaran_perubahan_id() {
        return pengeluaran_perubahan_id;
    }

    public void setPengeluaran_perubahan_id(int pengeluaran_perubahan_id) {
        this.pengeluaran_perubahan_id = pengeluaran_perubahan_id;
    }

    public int getPengeluaran_perubahan_nominal() {
        return pengeluaran_perubahan_nominal;
    }

    public void setPengeluaran_perubahan_nominal(int pengeluaran_perubahan_nominal) {
        this.pengeluaran_perubahan_nominal = pengeluaran_perubahan_nominal;
    }

    public int getPengeluaran_id() {
        return pengeluaran_id;
    }

    public void setPengeluaran_id(int pengeluaran_id) {
        this.pengeluaran_id = pengeluaran_id;
    }

    public String getPengeluaran_perubahan_date() {
        return pengeluaran_perubahan_date;
    }

    public void setPengeluaran_perubahan_date(String pengeluaran_perubahan_date) {
        this.pengeluaran_perubahan_date = pengeluaran_perubahan_date;
    }

}
