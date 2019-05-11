package com.cagatay.sleepfresh;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        showAlarmNotification(context);
    }

    private void showAlarmNotification(Context context) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, MainActivity.NOTIF_CHANNEL_ID)
                        .setContentTitle(context.getString(R.string.notification_alarm_message))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                        .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(), 0))
                        .setOngoing(true)
                        .setVibrate(new long[] {500, 500})
                        .setAutoCancel(true);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_INSISTENT;

        NotificationManagerCompat notManager = NotificationManagerCompat.from(context);
        notManager.notify(1, notification);
    }
}
