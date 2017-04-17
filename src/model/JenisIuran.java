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
public class JenisIuran {
    private int IuranJenisId;
    private String IuranJenisNama, IuranJenisKeterangan;

    public int getIuranJenisId() {
        return IuranJenisId;
    }

    public void setIuranJenisId(int IuranJenisId) {
        this.IuranJenisId = IuranJenisId;
    }

    public String getIuranJenisNama() {
        return IuranJenisNama;
    }

    public void setIuranJenisNama(String IuranJenisNama) {
        this.IuranJenisNama = IuranJenisNama;
    }

    public String getIuranJenisKeterangan() {
        return IuranJenisKeterangan;
    }

    public void setIuranJenisKeterangan(String IuranJenisKeterangan) {
        this.IuranJenisKeterangan = IuranJenisKeterangan;
    }

    public JenisIuran(String IuranJenisNama, String IuranJenisKeterangan) {
        this.IuranJenisNama = IuranJenisNama;
        this.IuranJenisKeterangan = IuranJenisKeterangan;
    }

    public JenisIuran(int IuranJenisId, String IuranJenisNama, String IuranJenisKeterangan) {
        this.IuranJenisId = IuranJenisId;
        this.IuranJenisNama = IuranJenisNama;
        this.IuranJenisKeterangan = IuranJenisKeterangan;
    }

    public JenisIuran() {
    }
}
