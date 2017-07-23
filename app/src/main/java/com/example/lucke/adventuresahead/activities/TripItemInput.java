package com.example.lucke.adventuresahead.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.activities.fragments.DatePickerFragment;
import com.example.lucke.adventuresahead.models.Location;
import com.example.lucke.adventuresahead.models.TripItem;
import com.example.lucke.adventuresahead.models.data.AdventuresData;
import com.example.lucke.adventuresahead.models.data.DatabaseHelper;


/**
 * Created by lucke on 6/1/2017.
 */

public abstract class TripItemInput extends AppCompatActivity{

    protected String name;
    protected String startDate;
    protected String endDate;

    protected DatabaseHelper helper;

    public void setToolBar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setDbHelper() {
        helper = AdventuresData.getInstance(getApplicationContext()).getDbHelper();
    }

    public void setFields(@IdRes int nameId,@IdRes int startDateId, @IdRes int endDateId) {
        name = ((EditText) findViewById(nameId)).getText().toString();
        startDate = ((TextView) findViewById(startDateId)).getText().toString();
        endDate = ((TextView) findViewById(endDateId)).getText().toString();
    }

    public abstract void addTripItem(View view);

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setView(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
