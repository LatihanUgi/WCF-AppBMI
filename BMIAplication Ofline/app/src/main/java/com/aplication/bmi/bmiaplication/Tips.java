package com.aplication.bmi.bmiaplication;

/**
 * Created by Ugi Ispoyo Widodo on 6/3/2016.
 */
public class Tips {
    private String id_artikel;
    private String judul;
    private String tanggal;
    private String artikel;

    public Tips(String pid, String jdl, String tgl, String pgm) {
        id_artikel = pid;
        judul = jdl;
        tanggal = tgl;
        artikel = pgm;

    }

    public String getid_artikel() {
        return id_artikel;
    }

    public void setid_artikel(String id_artikel) {
        this.id_artikel = id_artikel;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getartikel() {
        return artikel;
    }

    public void setartikel(String artikel) {
        this.artikel = artikel;
    }
}
