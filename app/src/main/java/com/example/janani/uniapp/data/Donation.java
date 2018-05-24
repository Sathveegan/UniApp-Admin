package com.example.janani.uniapp.data;

import java.util.Date;

/**
 * Created by Sathveegan on 25-May-18.
 */

public class Donation {
    private String name;
    private Date date;
    private double amount;

    public Donation(){

    }

    public Donation(String name, Date date, double amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
