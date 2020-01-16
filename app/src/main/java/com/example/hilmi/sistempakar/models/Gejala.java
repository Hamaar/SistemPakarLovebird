package com.example.hilmi.sistempakar.models;

import java.io.Serializable;


public class Gejala implements Serializable {
    private String kode_gejala = null;
    private String nama_gejala = null;

    public Gejala(String kode_gejala, String nama_gejala) {
        this.kode_gejala = kode_gejala;
        this.nama_gejala = nama_gejala;
    }






    public String getGid() {
        return kode_gejala;
    }

    public void setGid(String gid) {
        this.kode_gejala = gid;
    }

    public String getGejala() {
        return nama_gejala;
    }

    public void setGejala(String gejala) {
        this.nama_gejala = gejala;
    }
}
