package com.example.lucke.adventuresahead.models;

/**
 * Created by lucke on 4/26/2017.
 */

import java.util.ArrayList;
import java.util.List;


public class Adventure {


    private long id;
    private String name;
    private String date;


    public Adventure() {}

    public Adventure(String name){
        this.name = name;
    }

    public Adventure(long id, String name, String date){
        this.id = id;
        setName(name);
        setDate(date);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Adventure Name: " + name + "; ID: " + id;
    }
}
