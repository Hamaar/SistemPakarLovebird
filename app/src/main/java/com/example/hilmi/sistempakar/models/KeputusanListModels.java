package com.example.hilmi.sistempakar.models;

/**
 * Created by User on 12/01/2020.
 */

public class KeputusanListModels {
    private String ID;
    private String kode_keputusan;
    private String kode_gejala;

    public KeputusanListModels(String ID, String kode_keputusan, String kode_gejala) {
        this.ID = ID;
        this.kode_keputusan = kode_keputusan;
        this.kode_gejala = kode_gejala;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getKode_keputusan() {
        return kode_keputusan;
    }

    public void setKode_keputusan(String kode_keputusan) {
        this.kode_keputusan = kode_keputusan;
    }

    public String getKode_gejala() {
        return kode_gejala;
    }

    public void setKode_gejala(String kode_gejala) {
        this.kode_gejala = kode_gejala;
    }
}