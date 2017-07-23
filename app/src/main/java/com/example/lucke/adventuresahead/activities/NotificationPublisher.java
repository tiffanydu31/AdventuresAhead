package com.example.lucke.adventuresahead.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lucke on 7/19/2017.
 */

public class NotificationPublisher extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra("notification");
        int id = intent.getIntExtra("adventureId", 0);
        notificationManager.notify(id, notification);

    }
}
