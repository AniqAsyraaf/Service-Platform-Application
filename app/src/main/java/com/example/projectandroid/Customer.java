package com.example.projectandroid;

public class Customer {

    private int id;
    private String businessname;
    private byte[] image;
    private float rating;

    public Customer(int id, String businessname, byte[] image, float rating) {
        this.id = id;
        this.businessname = businessname;
        this.image = image;
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
