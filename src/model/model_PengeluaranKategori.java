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
public class model_PengeluaranKategori {

    private int pengeluaran_kategori_id, pengeluaran_kategori_waktu;
    private char pengeluaran_kategori_nama;

    public model_PengeluaranKategori() {

    }

    public model_PengeluaranKategori(int pengeluaran_kategori_id, char pengeluaran_kategori_nama, int pengeluaran_kategori_waktu) {
        this.pengeluaran_kategori_id = pengeluaran_kategori_id;
        this.pengeluaran_kategori_nama = pengeluaran_kategori_nama;
        this.pengeluaran_kategori_waktu = pengeluaran_kategori_waktu;
    }

    public model_PengeluaranKategori(char pengeluaran_kategori_nama, int pengeluaran_kategori_waktu) {
        this.pengeluaran_kategori_nama = pengeluaran_kategori_nama;
        this.pengeluaran_kategori_waktu = pengeluaran_kategori_waktu;
    }

    public int getPengeluaran_kategori_id() {
        return pengeluaran_kategori_id;
    }

    public void setPengeluaran_kategori_id(int pengeluaran_kategori_id) {
        this.pengeluaran_kategori_id = pengeluaran_kategori_id;
    }

    public int getPengeluaran_kategori_waktu() {
        return pengeluaran_kategori_waktu;
    }

    public void setPengeluaran_kategori_waktu(int pengeluaran_kategori_waktu) {
        this.pengeluaran_kategori_waktu = pengeluaran_kategori_waktu;
    }

    public char getPengeluaran_kategori_nama() {
        return pengeluaran_kategori_nama;
    }

    public void setPengeluaran_kategori_nama(char pengeluaran_kategori_nama) {
        this.pengeluaran_kategori_nama = pengeluaran_kategori_nama;
    }

}
