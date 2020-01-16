package com.example.hilmi.sistempakar.models;

/**
 * Created by User on 12/01/2020.
 */

public class SolusiListModels {
    private String kode_solusi;
    private String nama_solusi;

    public SolusiListModels(String kode_solusi, String nama_solusi) {
        this.kode_solusi = kode_solusi;
        this.nama_solusi = nama_solusi;
    }

    public String getKode_solusi() {
        return kode_solusi;
    }

    public void setKode_solusi(String kode_solusi) {
        this.kode_solusi = kode_solusi;
    }

    public String getNama_solusi() {
        return nama_solusi;
    }

    public void setNama_solusi(String nama_solusi) {
        this.nama_solusi = nama_solusi;
    }
}
