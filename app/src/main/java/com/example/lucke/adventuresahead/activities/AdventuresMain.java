package com.example.lucke.adventuresahead.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.activities.adapters.AdventureAdapter;
import com.example.lucke.adventuresahead.models.TableEnum;
import com.example.lucke.adventuresahead.models.data.AdventuresData;
import com.example.lucke.adventuresahead.models.data.DatabaseHelper;


public class AdventuresMain extends AppCompatActivity {

    public static final String LOG_TAG = AdventuresMain.class.getName();

    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adventures_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        helper = AdventuresData.getInstance(getApplicationContext()).getDbHelper();

        ListView adventuresListView = (ListView) findViewById(R.id.list);

        final AdventureAdapter adapter = new AdventureAdapter(this, helper.getAllAdventures());

        adventuresListView.setAdapter(adapter);

        // Adventure Details (onClick)
        adventuresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent categoryIntent = new Intent(AdventuresMain.this, CategoryActivity.class);
                categoryIntent.putExtra("adventureId", adapter.getItem(position).getId());
                startActivity(categoryIntent);
            }
        });

        // Delete Adventure (onLongClick)
        adventuresListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdventuresMain.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete this adventure?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteFromTable(TableEnum.TABLE_ADVENTURES,adapter.getItem(position).getId());

                        Intent refreshPage = new Intent(AdventuresMain.this, AdventuresMain.class);
                        startActivity(refreshPage);
                    }
                });

                builder.setCancelable(true);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                final AlertDialog deleteAlert = builder.create();
                deleteAlert.show();

                return true;
            }
        });

        // Find a reference to the addAdventure TextView
        TextView addAdventureTextView = (TextView) findViewById(R.id.add_adventure);
        // Set onClickListener to add new adventure
        addAdventureTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAdventureIntent = new Intent(AdventuresMain.this, AdventureInput.class);
                startActivity(addAdventureIntent);
            }
        });

    }

    public void resetDatabase(View view) {
        helper.clearDatabase();
        Intent newIntent = new Intent(AdventuresMain.this, AdventuresMain.class);
        startActivity(newIntent);
        finish();
    }
}
