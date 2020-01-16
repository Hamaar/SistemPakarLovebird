package com.example.hilmi.sistempakar.models;

import java.io.Serializable;

/**
 * Created by User on 11/01/2020.
 */

public class User implements Serializable {
    //variables
    int id;
    String username;
    String password;
    String fullname;
    // Constructor with two parameters name and password
    public User(String username,String password, String fullname)
    {
        this.username=username;
        this.password=password;
        this.fullname=fullname;
    }
    //Parameter constructor containing all three parameters
//    public User(String username, String password, String fullname)
//    {
//        this.id=id;
//        this.username=username;
//        this.password=password;
//        this.fullname=fullname;
//
//    }
    //getting id
    public int getId() {
        return id;
    }
    //setting id
    public void setId(int id) {
        this.id = id;
    }
    //getting name
    public String getUsername() {
        return username;
    }
    //setting name
    public void setUsername(String username) {
        this.username = username;
    }
    //getting password
    public String getPassword() {
        return password;
    }
    //setting password
    public void setPassword(String password) {
        this.password = password;
    }
    //getting password
    public String getFullname() {
        return fullname;
    }
    //setting password
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}