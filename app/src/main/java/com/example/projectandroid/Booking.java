package com.example.projectandroid;

public class Booking {

    private int id, serlistID, custID;
    private String date, time, servicename;

    public Booking(int id, int serlistID, int custID, String date, String time, String servicename) {
        this.id = id;
        this.serlistID = serlistID;
        this.custID = custID;
        this.date = date;
        this.time = time;
        this.servicename = servicename;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerlistID() {
        return serlistID;
    }

    public void setSerlistID(int serlistID) {
        this.serlistID = serlistID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
