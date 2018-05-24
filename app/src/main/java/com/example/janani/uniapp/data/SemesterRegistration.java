package com.example.janani.uniapp.data;

import java.util.Date;

/**
 * Created by Sathveegan on 25-May-18.
 */

public class SemesterRegistration {

    private String itnumber;
    private String name;
    private String email;
    private int year;
    private int semester;
    private Date date;
    private String contact;
    private double amount;

    public SemesterRegistration(){

    }

    public void setItnumber(String itnumber) {
        this.itnumber = itnumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getItnumber() {
        return itnumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public Date getDate() {
        return date;
    }

    public String getContact() {
        return contact;
    }

    public double getAmount() {
        return amount;
    }

    public SemesterRegistration(String itnumber, String name, String email, int year, int semester, Date date, String contact, double amount) {
        this.itnumber = itnumber;
        this.name = name;
        this.email = email;
        this.year = year;
        this.semester = semester;
        this.date = date;
        this.contact = contact;
        this.amount = amount;
    }
}
