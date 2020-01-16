package com.example.hilmi.sistempakar.models;

/**
 * Created by User on 12/01/2020.
 */

public class AkunListModels {
    private String ID;
    private String username;
    private String password;
    private String fullname;

    public AkunListModels(String ID, String username, String password, String fullname) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
