package com.example.projectandroid;

public class BookingHistory {

    private String servicename, date, time, cname, status;
    int bookingID;

    public BookingHistory(String servicename, String date, String time, String cname, String status, int bookingID) {
        this.servicename = servicename;
        this.date = date;
        this.time = time;
        this.cname = cname;
        this.status = status;
        this.bookingID = bookingID;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }
}
