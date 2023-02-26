package com.example.reminders;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public static final int NOTIFICATION_ID = 1;
    NotificationManager mNotifyManager;
    public static final String TAG = "StartService";


    public MyIntentService() {
        super("My Intent Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Se povika servisot");
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("Reminder")
                .setContentText("Reminding you!")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        NotificationManagerCompat mNotifyManager = NotificationManagerCompat.from(this);
            mNotifyManager.notify(NOTIFICATION_ID,notification);

            }
        }

