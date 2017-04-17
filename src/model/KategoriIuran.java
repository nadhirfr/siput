/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fachrul
 */
public class KategoriIuran {
    private int IuranKategoriId, IuranKategoriInterval;
    private String IuranKategoriNama ;

    public int getIuranKategoriId() {
        return IuranKategoriId;
    }

    public void setIuranKategoriId(int IuranKategoriId) {
        this.IuranKategoriId = IuranKategoriId;
    }

    public String getIuranKategoriNama() {
        return IuranKategoriNama;
    }

    public void setIuranKategoriNama(String IuranKategoriNama) {
        this.IuranKategoriNama = IuranKategoriNama;
    }

    public int getIuranKategoriInterval() {
        return IuranKategoriInterval;
    }

    public void setIuranKategoriInterval(int IuranKategoriInterval) {
        this.IuranKategoriInterval = IuranKategoriInterval;
    }

    public KategoriIuran(int IuranKategoriId, String IuranKategoriNama, int IuranKategoriInterval) {
        this.IuranKategoriId = IuranKategoriId;
        this.IuranKategoriNama = IuranKategoriNama;
        this.IuranKategoriInterval = IuranKategoriInterval;
    }

    public KategoriIuran(String IuranKategoriNama, int IuranKategoriInterval) {
        this.IuranKategoriNama = IuranKategoriNama;
        this.IuranKategoriInterval = IuranKategoriInterval;
    }

    public KategoriIuran() {
    }
}
