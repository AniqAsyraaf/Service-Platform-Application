package com.example.projectandroid;

public class Serlist {

    private int id;
    private String servicename;
    private String description;
    private double price_min;
    private double price_max;
    private int company_ID;

    public Serlist(int id, String servicename, String description, double price_min, double price_max, int company_ID) {
        this.id = id;
        this.servicename = servicename;
        this.description = description;
        this.price_min = price_min;
        this.price_max = price_max;
        this.company_ID = company_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice_min() {
        return price_min;
    }

    public void setPrice_min(double price_min) {
        this.price_min = price_min;
    }

    public double getPrice_max() {
        return price_max;
    }

    public void setPrice_max(double price_max) {
        this.price_max = price_max;
    }

    public int getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(int company_ID) {
        this.company_ID = company_ID;
    }
}
