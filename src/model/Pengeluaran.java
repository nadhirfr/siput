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
public class Pengeluaran {

    private int pengeluaran_id, pengeluaran_jenis_id, pengeluaran_kategori_id;
    private String pengeluaran_nama, pengeluaran_keterangan;

    public Pengeluaran() {

    }

    public Pengeluaran(int pengeluaran_jenis_id, int pengeluaran_kategori_id, String pengeluaran_nama, String pengeluaran_keterangan) {
        this.pengeluaran_jenis_id = pengeluaran_jenis_id;
        this.pengeluaran_kategori_id = pengeluaran_kategori_id;
        this.pengeluaran_nama = pengeluaran_nama;
        this.pengeluaran_keterangan = pengeluaran_keterangan;
    }

    public Pengeluaran(int pengeluaran_id, int pengeluaran_jenis_id, int pengeluaran_kategori_id, String pengeluaran_nama, String pengeluaran_keterangan) {
        this.pengeluaran_id = pengeluaran_id;
        this.pengeluaran_jenis_id = pengeluaran_jenis_id;
        this.pengeluaran_kategori_id = pengeluaran_kategori_id;
        this.pengeluaran_nama = pengeluaran_nama;
        this.pengeluaran_keterangan = pengeluaran_keterangan;
    }

    public String getPengeluaran_keterangan() {
        return pengeluaran_keterangan;
    }

    public void setPengeluaran_keterangan(String pengeluaran_keterangan) {
        this.pengeluaran_keterangan = pengeluaran_keterangan;
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

    public String getPengeluaran_nama() {
        return pengeluaran_nama;
    }

    public void setPengeluaran_nama(String pengeluaran_nama) {
        this.pengeluaran_nama = pengeluaran_nama;
    }
    
}
