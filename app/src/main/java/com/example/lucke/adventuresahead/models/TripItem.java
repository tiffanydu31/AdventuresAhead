package com.example.lucke.adventuresahead.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lucke on 6/21/2017.
 */

public abstract class TripItem {

    private long id;
    private String name;
    private Date startDate;
    private Date endDate;

    public TripItem() {}

    public TripItem(String[] args) {
        this(Long.parseLong(args[0]), args[1], args[2], args[3]);
    }

    public TripItem(String name, String startDate, String endDate) {
        this.name = name;
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public TripItem(long id, String name, String startDate, String endDate) {
        this(name, startDate, endDate);
        setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getStringStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.startDate = df.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStringEndDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(endDate);
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.endDate = df.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "; ID: " + id;
    }
}
