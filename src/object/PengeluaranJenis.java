/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author rheza
 */
public class PengeluaranJenis {

    private int pengeluaran_jenis_id;
    private String pengeluaran_nama;
    private String pengeluaran_keterangan;

    public PengeluaranJenis() {

    }

    public PengeluaranJenis(int pengeluaran_jenis_id, String pengeluaran_nama, String pengeluaran_keterangan) {
        this.pengeluaran_jenis_id = pengeluaran_jenis_id;
        this.pengeluaran_nama = pengeluaran_nama;
        this.pengeluaran_keterangan = pengeluaran_keterangan;
    }

    public PengeluaranJenis(String pengeluaran_nama, String pengeluaran_keterangan) {
        this.pengeluaran_nama = pengeluaran_nama;
        this.pengeluaran_keterangan = pengeluaran_keterangan;
    }

    public int getPengeluaran_jenis_id() {
        return pengeluaran_jenis_id;
    }

    public void setPengeluaran_jenis_id(int pengeluaran_jenis_id) {
        this.pengeluaran_jenis_id = pengeluaran_jenis_id;
    }

    public String getPengeluaran_nama() {
        return pengeluaran_nama;
    }

    public void setPengeluaran_nama(String pengeluaran_nama) {
        this.pengeluaran_nama = pengeluaran_nama;
    }

    public String getPengeluaran_keterangan() {
        return pengeluaran_keterangan;
    }

    public void setPengeluaran_keterangan(String pengeluaran_keterangan) {
        this.pengeluaran_keterangan = pengeluaran_keterangan;
    }
    
}
