package com.example.lucke.adventuresahead.models.data;

import android.content.Context;

/**
 * Created by lucke on 6/20/2017.
 */

public class AdventuresData {
    private final Context context;
    private DatabaseHelper helper;

    private static AdventuresData instance;

    public AdventuresData(Context context){
        this.context = context;
        helper = new DatabaseHelper(context);
    }

    public static AdventuresData getInstance(Context context) {
        if (instance == null) {
            instance = new AdventuresData(context);
        }
        return instance;
    }

    public Context getContext() {
        return context;
    }

    public DatabaseHelper getDbHelper(){
        return helper;
    }
}
