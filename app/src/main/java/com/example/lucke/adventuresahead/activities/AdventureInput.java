package com.example.lucke.adventuresahead.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lucke.adventuresahead.activities.adapters.TripItemAdapter;
import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.models.Adventure;
import com.example.lucke.adventuresahead.models.TableEnum;
import com.example.lucke.adventuresahead.models.TripItem;
import com.example.lucke.adventuresahead.models.data.AdventuresData;
import com.example.lucke.adventuresahead.models.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lucke on 5/13/2017.
 */

public class AdventureInput extends AppCompatActivity{

    public static final String LOG_TAG = AdventureInput.class.getName();
    private DatabaseHelper helper;

    private List<Long> locationIds = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adventure_input);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = AdventuresData.getInstance(getApplicationContext()).getDbHelper();

    }

    public void addAdventure(View view) {
        List<String> inputs = new ArrayList<>();

        inputs.add(((EditText) findViewById(R.id.new_adventure_name)).getText().toString());
        inputs.add(((EditText) findViewById(R.id.new_adventure_date)).getText().toString());

        long adventureId = helper.addToTable(TableEnum.TABLE_ADVENTURES, inputs);

        Intent returnIntent = new Intent(AdventureInput.this, AdventuresMain.class);
        startActivity(returnIntent);
        finish();

    }

}
