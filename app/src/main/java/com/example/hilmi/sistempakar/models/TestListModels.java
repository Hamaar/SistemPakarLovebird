package com.example.hilmi.sistempakar.models;

/**
 * Created by User on 12/01/2020.
 */

public class TestListModels {
    private String testKode;
    private String testGejala;

    public TestListModels(String testKode, String testGejala) {
        this.testKode = testKode;
        this.testGejala = testGejala;
    }

    public String getTestKode() {
        return testKode;
    }

    public void setTestKode(String testKode) {
        this.testKode = testKode;
    }

    public String getTestGejala() {
        return testGejala;
    }

    public void setTestGejala(String testGejala) {
        this.testGejala = testGejala;
    }
}
