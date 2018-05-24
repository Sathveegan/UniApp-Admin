package com.example.janani.uniapp.data;

import java.util.Date;

/**
 * Created by Sathveegan on 25-May-18.
 */

public class RepeatRegistration {

    private String itnumber;
    private String name;
    private String subject;
    private Date date;
    private double amount;

    public  RepeatRegistration(){

    }

    public RepeatRegistration(String itnumber, String name, String subject, Date date, double amount) {
        this.itnumber = itnumber;
        this.name = name;
        this.subject = subject;
        this.date = date;
        this.amount = amount;
    }

    public String getItnumber() {
        return itnumber;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setItnumber(String itnumber) {
        this.itnumber = itnumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
