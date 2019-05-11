package com.cagatay.sleepfresh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Objects;

public class ScreenStatusReceiver extends BroadcastReceiver {

    private SharedPreferencesManager spManager;
    private AlarmController alarmController;

    @Override
    public void onReceive(Context context, Intent intent) {
        spManager = new SharedPreferencesManager(context);
        alarmController = new AlarmController(context);

        String intentAction = Objects.requireNonNull(intent.getAction());
        if (intentAction.contentEquals(Intent.ACTION_SCREEN_OFF)) {
            createAutoAlarm();
        } else if (intentAction.contentEquals(Intent.ACTION_USER_PRESENT)) {
            alarmController.cancelAutoAlarm();
        }
    }

    private void createAutoAlarm() {
        long periodTime = getAlarmPeriodTime(spManager.getAlarmPeriod());
        long alarmTime = System.currentTimeMillis() + periodTime;
        alarmController.createAutoAlarm(alarmTime);
    }

    private long getAlarmPeriodTime(int period) {
        switch (period) {
            case 0:
                return 10800000; // 3 hours
            case 1:
                return 21600000; // 6 hours
            case 2:
                return 32400000; // 9 hours
            case 3:
                return 43200000; // 12 hours
            default:
                return 0;
        }
    }
}
