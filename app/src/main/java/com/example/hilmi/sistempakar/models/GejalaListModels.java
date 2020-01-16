package com.example.hilmi.sistempakar.models;

/**
 * Created by User on 12/01/2020.
 */

public class GejalaListModels {
    private String kode_gejala;
    private String nama_gejala;

    public GejalaListModels(String kode_gejala, String nama_gejala) {
        this.kode_gejala = kode_gejala;
        this.nama_gejala = nama_gejala;
    }

    public String getKode_gejala() {
        return kode_gejala;
    }

    public void setKode_gejala(String kode_gejala) {
        this.kode_gejala = kode_gejala;
    }

    public String getNama_gejala() {
        return nama_gejala;
    }

    public void setNama_gejala(String nama_gejala) {
        this.nama_gejala = nama_gejala;
    }
}
