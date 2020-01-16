package com.example.hilmi.sistempakar.models;



public class KeputusandanSolusi {
    private int id;
    private String pid,gid;

    public KeputusandanSolusi(int id, String pid, String gid) {
        this.id = id;
        this.pid = pid;
        this.gid = gid;
    }

    public KeputusandanSolusi(String pid, String gid) {
        this.pid = pid;
        this.gid = gid;
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

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}
