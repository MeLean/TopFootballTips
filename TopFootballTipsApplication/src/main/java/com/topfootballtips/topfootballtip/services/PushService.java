package com.topfootballtips.topfootballtip.services;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.backendless.push.BackendlessPushService;
import com.topfootballtips.topfootballtip.R;
import com.topfootballtips.topfootballtip.ui.activities.MainActivity;

public class PushService extends BackendlessPushService
{
    public static final String TAG = "TFTdebug";
    @Override
    public void onRegistered(Context context, String registrationId )
    {
        Log.d(TAG,"registered and id is: " + registrationId);
    }

    @Override
    public void onUnregistered( Context context, Boolean unregistered )
    {
        Log.d(TAG,"unregistered = " + unregistered);
    }

    @Override
    public boolean onMessage( Context context, Intent intent )
    {
        String message = intent.getStringExtra("message");
        long[] v = {500,1000};
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setVibrate(v)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getString(R.string.notification_title))
                        .setAutoCancel(true)
                        .setContentText(message);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0, mBuilder.build());






        Log.d(TAG, message);

        return true;
    }

    @Override
    public void onError( Context context, String message )
    {
        Log.d(TAG,message);
    }
}