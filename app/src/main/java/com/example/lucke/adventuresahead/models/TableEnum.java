package com.example.lucke.adventuresahead.models;

import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucke on 6/21/2017.
 */

public enum TableEnum {
    TABLE_ADVENTURES ("adventures"),
    TABLE_LOCATIONS ("locations"),
    TABLE_TRANSPORTATION ("transportation"),
    TABLE_ADVENTURE_LOCATION ("adventure_location"),
    TABLE_ADVENTURE_TRANSPORTATION ("adventure_transportation");

    private final String table;

    TableEnum(String table) {
        this.table = table;
    }

    public String getTableName() {
        return table;
    }

    public List<String> getKeys() {
        List<String> keys = new ArrayList<>();

        switch (table) {
            case "adventures":
                keys.add("name");
                keys.add("start");
                break;
            case "locations":
                keys.add("name");
                keys.add("address");
                keys.add("start");
                keys.add("end");
                break;
            case "transportation":
                keys.add("name");
                keys.add("start");
                keys.add("end");
                break;
            case "adventure_location":
                keys.add("adventure_id");
                keys.add("location_id");
                break;
            case "adventure_transportation":
                keys.add("adventure_id");
                keys.add("transportation_id");
                break;
            default:
                break;
        }

        return keys;
    }

    public TripItem getTripItem(String[] args) {
        TripItem item;
        switch (table) {

            case "locations":
                item = new Location(args);
                break;
            default:
                item = new Transportation(args);
                break;
        }

        return item;
    }

    public List<TableEnum> getJunctionParents() {
        List<TableEnum> parents = new ArrayList<>();
        switch (table) {
            case "adventure_location":
                parents.add(TABLE_ADVENTURES);
                parents.add(TABLE_LOCATIONS);
                break;
            default:
                parents.add(TABLE_ADVENTURES);
                parents.add(TABLE_TRANSPORTATION);
                break;
        }

        return parents;
    }

}

