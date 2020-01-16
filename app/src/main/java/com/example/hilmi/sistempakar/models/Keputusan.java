package com.example.hilmi.sistempakar.models;



public class Keputusan {
    private int id;
    private String pid,kode_gejala;

    public Keputusan(int id, String pid, String kode_gejala) {
        this.id = id;
        this.pid = pid;
        this.kode_gejala = kode_gejala;
    }

    public Keputusan(String pid, String kode_gejala) {
        this.pid = pid;
        this.kode_gejala = kode_gejala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getKode_gejala() {
        return kode_gejala;
    }

    public void setKode_gejala(String kode_gejala) {
        this.kode_gejala = kode_gejala;
    }
}
