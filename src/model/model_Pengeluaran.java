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
public class model_Pengeluaran {

    private int pengeluaran_id, pengeluaran_jenis_id, pengeluaran_kategori_id;
    private char pengeluaran_nama;

    public model_Pengeluaran() {

    }

    public model_Pengeluaran(int pengeluaran_id, char pengeluaran_nama, int pengeluaran_jenis_id, int pengeluaran_kategori_id) {
        this.pengeluaran_id = pengeluaran_id;
        this.pengeluaran_nama = pengeluaran_nama;
        this.pengeluaran_jenis_id = pengeluaran_jenis_id;
        this.pengeluaran_kategori_id = pengeluaran_kategori_id;
    }

    public model_Pengeluaran(char pengeluaran_nama, int pengeluaran_jenis_id, int pengeluaran_kategori_id) {
        this.pengeluaran_nama = pengeluaran_nama;
        this.pengeluaran_jenis_id = pengeluaran_jenis_id;
        this.pengeluaran_kategori_id = pengeluaran_kategori_id;
    }

    public int getPengeluaran_id() {
        return pengeluaran_id;
    }

    public void setPengeluaran_id(int pengeluaran_id) {
        this.pengeluaran_id = pengeluaran_id;
    }

    public int getPengeluaran_jenis_id() {
        return pengeluaran_jenis_id;
    }

    public void setPengeluaran_jenis_id(int pengeluaran_jenis_id) {
        this.pengeluaran_jenis_id = pengeluaran_jenis_id;
    }

    public int getPengeluaran_kategori_id() {
        return pengeluaran_kategori_id;
    }

    public void setPengeluaran_kategori_id(int pengeluaran_kategori_id) {
        this.pengeluaran_kategori_id = pengeluaran_kategori_id;
    }

    public char getPengeluaran_nama() {
        return pengeluaran_nama;
    }

    public void setPengeluaran_nama(char pengeluaran_nama) {
        this.pengeluaran_nama = pengeluaran_nama;
    }
    
}
