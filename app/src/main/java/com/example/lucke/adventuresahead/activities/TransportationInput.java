package com.example.lucke.adventuresahead.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.models.TableEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucke on 6/22/2017.
 */

public class TransportationInput extends TripItemInput {


    public static final String LOG_TAG = LocationInput.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transportation_input);

        setToolBar();
        setDbHelper();
    }

    @Override
    public void addTripItem(View view) {
        setFields(R.id.new_transportation_name, R.id.new_transportation_start_date, R.id.new_transportation_end_date);

        List<String> fields = new ArrayList<>();
        fields.add(name);
        fields.add(startDate);
        fields.add(endDate);

        long itemId = helper.addToTable(TableEnum.TABLE_TRANSPORTATION, fields);

        int tabPosition = getIntent().getIntExtra("tabPosition", 1);
        long adventureId = getIntent().getLongExtra("adventureId", 0);

        List<String> junctionFields = new ArrayList<>();
        junctionFields.add(String.valueOf(adventureId));
        junctionFields.add(String.valueOf(itemId));

        helper.addToTable(TableEnum.TABLE_ADVENTURE_TRANSPORTATION, junctionFields);

        Intent returnIntent = new Intent(TransportationInput.this, CategoryActivity.class);
        returnIntent.putExtra("adventureId", adventureId);
        returnIntent.putExtra("tabPosition", tabPosition);
        startActivity(returnIntent);

        finish();
    }
}
