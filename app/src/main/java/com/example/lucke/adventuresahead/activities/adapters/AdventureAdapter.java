package com.example.lucke.adventuresahead.activities.adapters;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lucke.adventuresahead.R;
import com.example.lucke.adventuresahead.activities.CategoryActivity;
import com.example.lucke.adventuresahead.activities.NotificationPublisher;
import com.example.lucke.adventuresahead.models.Adventure;

import java.util.List;

/**
 * AdventuresAdapter creates list item layout for each adventure
 * in the data source (a list of {@link Adventure} objects)
 *
 * These list item layouts will be provided to an adapter view
 * like ListView to be displayed to the user
 */

public class AdventureAdapter extends ArrayAdapter<Adventure>{

    public AdventureAdapter(Context context, List<Adventure> adventures) {
        super(context, 0, adventures);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Checks there is an existing list item view (called convertView) that can be reused
        // If the convertView is null, inflate a new list item layout
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.adventures_list_item2, parent, false);
        }

        // Find the adventure at the given position in the list of adventures
        final Adventure currentAdventure = getItem(position);

        Button alarmButton = (Button) listItemView.findViewById(R.id.set_alarm);

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNotification(currentAdventure.getId(), 15000);
            }
        });



        // Populate Adventure properties in the appropriate TextView
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.trip_name);
        nameTextView.setText(currentAdventure.getName());
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.trip_date);
        dateTextView.setText(currentAdventure.getDate());


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    public void setNotification(long adventureId, long delay) {

        Notification.Builder mBuilder = new Notification.Builder(getContext());

        mBuilder.setContentTitle("Notification");
        mBuilder.setContentText("Your trip is starting soon!");
        mBuilder.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);

        Intent redirectIntent = new Intent(getContext(), CategoryActivity.class);
        redirectIntent.putExtra("adventureId", adventureId);
        PendingIntent redirectPendingIntent = PendingIntent.getActivity(getContext(), 0, redirectIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(redirectPendingIntent);

        Intent notificationIntent = new Intent(getContext(), NotificationPublisher.class);
        notificationIntent.putExtra("adventureId", adventureId);
        notificationIntent.putExtra("notification", mBuilder.build());
        PendingIntent resultIntent = PendingIntent.getBroadcast(getContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, resultIntent);
    }
}
