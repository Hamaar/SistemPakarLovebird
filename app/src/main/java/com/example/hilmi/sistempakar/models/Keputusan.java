package com.example.hilmi.sistempakar.models;



public class Keputusan {
    private int id;
    private String kode_penyakit,kode_gejala;

    public Keputusan(int id, String kode_penyakit, String kode_gejala) {
        this.id = id;
        this.kode_penyakit = kode_penyakit;
        this.kode_gejala = kode_gejala;
    }

    public Keputusan(String kode_penyakit, String kode_gejala) {
        this.kode_penyakit = kode_penyakit;
        this.kode_gejala = kode_gejala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPid() {
        return kode_penyakit;
    }

    public void setPid(String pid) {
        this.kode_penyakit = pid;
    }

    public String getKode_gejala() {
        return kode_gejala;
    }

    public void setKode_gejala(String kode_gejala) {
        this.kode_gejala = kode_gejala;
    }
}
