package com.example.lucke.adventuresahead.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;

/**
 * Created by lucke on 5/12/2017.
 */

public class Location extends TripItem{

    private String address;

    public Location() {
        super();
    }

    public Location(String[] args) {
        this(Long.parseLong(args[0]), args[1], args[2], args[3], args[4]);
    }

    public Location(String name, String startDate, String endDate) {
        super(name, startDate, endDate);
    }

    public Location(String name, String address, String startDate, String endDate) {
        this(name, startDate, endDate);
        setAddress(address);

    }
    public Location(long id, String name, String startDate, String endDate) {
        super(id, name, startDate, endDate);
    }

    public Location(long id, String name, String address, String startDate, String endDate) {
        this(id, name, startDate, endDate);
        setAddress(address);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
