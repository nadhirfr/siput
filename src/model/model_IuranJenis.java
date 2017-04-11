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
public class model_IuranJenis {

    private int iuran_jenis_id;
    private char iuran_jenis_nama;
    private String iuran_jenis_keterangan;

    public model_IuranJenis() {

    }

    public model_IuranJenis(int iuran_jenis_id, char iuran_jenis_nama, String iuran_jenis_keterangan) {
        this.iuran_jenis_id = iuran_jenis_id;
        this.iuran_jenis_nama = iuran_jenis_nama;
        this.iuran_jenis_keterangan = iuran_jenis_keterangan;
    }

    public model_IuranJenis(char iuran_jenis_nama, String iuran_jenis_keterangan) {
        this.iuran_jenis_nama = iuran_jenis_nama;
        this.iuran_jenis_keterangan = iuran_jenis_keterangan;
    }

    public int getIuran_jenis_id() {
        return iuran_jenis_id;
    }

    public void setIuran_jenis_id(int iuran_jenis_id) {
        this.iuran_jenis_id = iuran_jenis_id;
    }

    public char getIuran_jenis_nama() {
        return iuran_jenis_nama;
    }

    public void setIuran_jenis_nama(char iuran_jenis_nama) {
        this.iuran_jenis_nama = iuran_jenis_nama;
    }

    public String getIuran_jenis_keterangan() {
        return iuran_jenis_keterangan;
    }

    public void setIuran_jenis_keterangan(String iuran_jenis_keterangan) {
        this.iuran_jenis_keterangan = iuran_jenis_keterangan;
    }

}
