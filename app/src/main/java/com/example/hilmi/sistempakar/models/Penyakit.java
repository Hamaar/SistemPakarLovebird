package com.example.hilmi.sistempakar.models;



public class Penyakit {
    private String kode_penyakit;
    private String nama_penyakit;
    private String solusi;


    public Penyakit( String kode_penyakit, String nama_penyakit, String solusi) {
        this.kode_penyakit = kode_penyakit;
        this.nama_penyakit = nama_penyakit;
        this.solusi = solusi;
    }




    public String getKode_penyakit() {
        return kode_penyakit;
    }

    public void setKode_penyakit(String kode_penyakit) {
        this.kode_penyakit = kode_penyakit;
    }

    public String getNama_penyakit() {
        return nama_penyakit;
    }

    public void setNama(String nama) {
        this.nama_penyakit = nama;
    }

    public String getCara() {
        return solusi;
    }

    public void setCara(String cara) {
        this.solusi = cara;
    }
}
