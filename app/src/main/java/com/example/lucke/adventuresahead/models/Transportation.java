package com.example.lucke.adventuresahead.models;

/**
 * Created by lucke on 6/21/2017.
 */

public class Transportation extends TripItem {

    public Transportation() {
        super();
    }

    public Transportation(String[] args) {
        super(args);
    }

    public Transportation(String name, String startDate, String endDate) {
        super(name, startDate, endDate);
    }

    public Transportation(long id, String name, String startDate, String endDate) {
        super(id, name, startDate, endDate);
    }
    
}
