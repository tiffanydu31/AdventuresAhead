package com.example.lucke.adventuresahead.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.models.Adventure;
import com.example.lucke.adventuresahead.models.TripItem;

import java.util.List;

/**
 * AdventuresAdapter creates list item layout for each adventure
 * in the data source (a list of {@link Adventure} objects)
 *
 * These list item layouts will be provided to an adapter view
 * like ListView to be displayed to the user
 */

public class TripItemAdapter extends ArrayAdapter<TripItem>{

    public TripItemAdapter(Context context, List<TripItem> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Checks there is an existing list item view (called convertView) that can be reused
        // If the convertView is null, inflate a new list item layout
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.trip_item, parent, false);
        }

        TripItem currentItem = getItem(position);

        // Populate Adventure properties in the appropriate TextView
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.name);
        locationTextView.setText(currentItem.getName());
        TextView startDateTextView = (TextView) listItemView.findViewById(R.id.start_date);
        startDateTextView.setText(currentItem.getStringStartDate());
        TextView endDateTextView = (TextView) listItemView.findViewById(R.id.end_date);
        endDateTextView.setText(currentItem.getStringEndDate());



        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
