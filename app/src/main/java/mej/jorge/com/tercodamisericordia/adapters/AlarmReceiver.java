package mej.jorge.com.tercodamisericordia.adapters;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

import mej.jorge.com.tercodamisericordia.MainActivity;
import mej.jorge.com.tercodamisericordia.R;

/**
 * Created by Jorge on 1/8/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, MainActivity.class);
        int requestID = (int)System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestID, intent1, flags);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.icon_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_app))
                .setContentTitle("Divina Misericórdia")
                .setContentText("Hora do Terço!")
                .setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

    }

    public void setAlarm(Context context, Calendar calendar) {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, pendingIntent);
    }

    public void cancelAlarm(Context context) {
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

}
