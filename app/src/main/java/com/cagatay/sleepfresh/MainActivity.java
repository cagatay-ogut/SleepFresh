package com.cagatay.sleepfresh;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIF_CHANNEL_ID = "NOTIF_CHANNEL_ALARM";
    private final CharSequence NOTIF_CHANNEL_DESC = "Alarm Notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioAttributes audioAttr = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setLegacyStreamType(AudioManager.STREAM_ALARM)
                            .build();

            NotificationChannel channel = new NotificationChannel(NOTIF_CHANNEL_ID, NOTIF_CHANNEL_DESC, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM), audioAttr);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
