package com.example.lucke.adventuresahead.activities.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.activities.fragments.LocationFragment;
import com.example.lucke.adventuresahead.activities.fragments.TransportationFragment;


/**
 * Created by lucke on 6/21/2017.
 */

public class TripCategoryAdapter extends FragmentPagerAdapter {

    private Context context;

    public TripCategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LocationFragment();
        } else {
            return new TransportationFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.category_location);
        } else {
            return context.getString(R.string.category_transportation);
        }
    }
}
