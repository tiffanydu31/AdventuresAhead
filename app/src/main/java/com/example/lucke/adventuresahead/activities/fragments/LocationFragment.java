package com.example.lucke.adventuresahead.activities.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.activities.CategoryActivity;
import com.example.lucke.adventuresahead.activities.LocationInput;
import com.example.lucke.adventuresahead.activities.MapsActivity;
import com.example.lucke.adventuresahead.activities.adapters.TripItemAdapter;
import com.example.lucke.adventuresahead.models.TableEnum;
import com.example.lucke.adventuresahead.models.TripItem;
import com.example.lucke.adventuresahead.models.data.AdventuresData;
import com.example.lucke.adventuresahead.models.data.DatabaseHelper;

import java.util.List;

/**
 * Created by lucke on 6/21/2017.
 */

public class LocationFragment extends Fragment {

    public static final String LOG_TAG = LocationFragment.class.getName();
    DatabaseHelper helper;
    private long adventureId;

    public LocationFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.category_list, container, false);

        helper = AdventuresData.getInstance(getActivity().getApplicationContext()).getDbHelper();
        adventureId = getActivity().getIntent().getExtras().getLong("adventureId");

        ListView locationsListView = (ListView) rootView.findViewById(R.id.list);

        List<TripItem> locations = helper.getAllTripItemsByAdventure(TableEnum.TABLE_ADVENTURE_LOCATION, adventureId);

        final TripItemAdapter adapter = new TripItemAdapter(getActivity(), locations);

        locationsListView.setAdapter(adapter);

        // display location (onClick)
        locationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent displayMap = new Intent(getActivity(), MapsActivity.class);
                displayMap.putExtra("locationId", adapter.getItem(position).getId());
                startActivity(displayMap);
            }
        });

        // delete location (onLongClick)
        locationsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete this location?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), adapter.getItem(position).toString(), Toast.LENGTH_SHORT);
                        toast.show();
                        helper.deleteFromTable(TableEnum.TABLE_LOCATIONS, adapter.getItem(position).getId());

                        Intent refreshIntent = new Intent(getActivity(), CategoryActivity.class);
                        refreshIntent.putExtra("adventureId", adventureId);
                        refreshIntent.putExtra("tabPosition", 0);
                        startActivity(refreshIntent);

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

        TextView addLocationTextView = (TextView) rootView.findViewById(R.id.add_item);

        // add new location (button)
        addLocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addLocationIntent = new Intent(getActivity(), LocationInput.class);
                addLocationIntent.putExtra("tabPosition", 0);
                addLocationIntent.putExtra("adventureId", adventureId);
                startActivity(addLocationIntent);
            }
        });

        return rootView;
    }

}
