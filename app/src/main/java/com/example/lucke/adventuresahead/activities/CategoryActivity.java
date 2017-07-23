package com.example.lucke.adventuresahead.activities;

import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.activities.adapters.TripCategoryAdapter;
import com.example.lucke.adventuresahead.models.data.AdventuresData;
import com.example.lucke.adventuresahead.models.data.DatabaseHelper;

/**
 * Created by lucke on 6/21/2017.
 */

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHelper helper = AdventuresData.getInstance(getApplicationContext()).getDbHelper();

        long adventureId = getIntent().getLongExtra("adventureId", 0);

        int tabPosition = getIntent().getIntExtra("tabPosition", 0);


        setContentView(R.layout.category_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        TripCategoryAdapter adapter = new TripCategoryAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(tabPosition);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(helper.getAdventure(adventureId).getName());
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
