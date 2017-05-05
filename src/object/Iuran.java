/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author fachrul
 */
public class Iuran {
    private int IuranId,IuranJenisId,IuranKategoriId, IuranNominal;
    private String IuranNama;

    public Iuran() {
    }

    public Iuran(int IuranId, int IuranJenisId, int IuranKategoriId, String IuranNama, int IuranNominal) {
        this.IuranId = IuranId;
        this.IuranJenisId = IuranJenisId;
        this.IuranKategoriId = IuranKategoriId;
        this.IuranNama = IuranNama;
        this.IuranNominal = IuranNominal;
    }

    public Iuran(int IuranJenisId, int IuranKategoriId, String IuranNama, int IuranNominal) {
        this.IuranJenisId = IuranJenisId;
        this.IuranKategoriId = IuranKategoriId;
        this.IuranNama = IuranNama;
        this.IuranNominal = IuranNominal;
    }

    public int getIuranId() {
        return IuranId;
    }

    public void setIuranId(int IuranId) {
        this.IuranId = IuranId;
    }

    public int getIuranJenisId() {
        return IuranJenisId;
    }

    public void setIuranJenisId(int IuranJenisId) {
        this.IuranJenisId = IuranJenisId;
    }

    public int getIuranKategoriId() {
        return IuranKategoriId;
    }

    public void setIuranKategoriId(int IuranKategoriId) {
        this.IuranKategoriId = IuranKategoriId;
    }

    public String getIuranNama() {
        return IuranNama;
    }

    public void setIuranNama(String IuranNama) {
        this.IuranNama = IuranNama;
    }

    public int getIuranNominal() {
        return IuranNominal;
    }

    public void setIuranNominal(int IuranNominal) {
        this.IuranNominal = IuranNominal;
    }
    
    
}
