package com.example.projectandroid;

public class OngoingBooking {
    private String servicename, date, time, status;
    private int bookingID;

    public OngoingBooking(String servicename, String date, String time, String status, int bookingID) {
        this.servicename = servicename;
        this.date = date;
        this.time = time;
        this.status = status;
        this.bookingID = bookingID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
