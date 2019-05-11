package com.cagatay.sleepfresh;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

class AlarmController {

    @SuppressWarnings("FieldCanBeLocal")
    private final int AUTO_ALARM_CODE = 10; // regular alarm goes from 0 (Monday) to 7 (Sunday)

    private final Context context;
    private final AlarmManager alarmManager;


    AlarmController(Context context) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    void createAutoAlarm(long alarmTime) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime,
                AlarmManager.INTERVAL_DAY, getAlarmIntent(AUTO_ALARM_CODE));

    }

    void cancelAutoAlarm() {
        alarmManager.cancel(getAlarmIntent(AUTO_ALARM_CODE));
    }

    void createRegularAlarm(int code, long alarmTime) {
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime,
                AlarmManager.INTERVAL_DAY * 7, getAlarmIntent(code));
    }

    void cancelRegularAlarm(int code) {
        alarmManager.cancel(getAlarmIntent(code));
    }

    private PendingIntent getAlarmIntent(int code) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        return PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

}
