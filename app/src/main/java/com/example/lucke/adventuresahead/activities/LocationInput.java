package com.example.lucke.adventuresahead.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.models.TableEnum;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lucke on 6/1/2017.
 */

public class LocationInput extends TripItemInput {

    protected String address;

    public static final String LOG_TAG = LocationInput.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_input);

        setToolBar();
        setDbHelper();
    }

    @Override
    public void addTripItem(View view) {
        setFields(R.id.new_location_name, R.id.new_location_start_date, R.id.new_location_end_date);
        address = ((EditText) findViewById(R.id.new_location_address)).getText().toString();

        List<String> fields = new ArrayList<>();
        fields.add(name);
        fields.add(address);
        fields.add(startDate);
        fields.add(endDate);

        long itemId = helper.addToTable(TableEnum.TABLE_LOCATIONS, fields);

        int tabPosition = getIntent().getIntExtra("tabPosition", 0);
        long adventureId = getIntent().getLongExtra("adventureId", 0);

        List<String> junctionFields = new ArrayList<>();
        junctionFields.add(String.valueOf(adventureId));
        junctionFields.add(String.valueOf(itemId));

        helper.addToTable(TableEnum.TABLE_ADVENTURE_LOCATION, junctionFields);

        Intent returnIntent = new Intent(LocationInput.this, CategoryActivity.class);
        returnIntent.putExtra("adventureId", adventureId);
        returnIntent.putExtra("tabPosition", tabPosition);
        startActivity(returnIntent);

        finish();
    }

}
