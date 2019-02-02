package com.cagatay.sleepfresh;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

class SharedPreferencesManager {

    private final String AUTO_ALARM_PREF = "is_auto_alarm_set";
    private final String REGULAR_ALARM_PREF = "is_regular_alarm_set";
    private final String ALARM_PERIOD_POS = "alarm_period_position";
    private final String WEEK_DAY_BOX = "week_day_";

    @SuppressWarnings("FieldCanBeLocal")
    private final int DEF_ALARM_PERIOD_POS = 1; // 6 hours

    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;

    SharedPreferencesManager(Context context){
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        editor.apply();
    }

    boolean isAutoAlarmOn() {
        return sharedPref.getBoolean(AUTO_ALARM_PREF, false);
    }

    void setAutoAlarmPref(boolean isChecked){
        editor.putBoolean(AUTO_ALARM_PREF, isChecked);
        editor.apply();
    }

    boolean isRegularAlarmOn() {
        return sharedPref.getBoolean(REGULAR_ALARM_PREF, false);
    }

    void setRegularAlarmPref(boolean isChecked){
        editor.putBoolean(REGULAR_ALARM_PREF, isChecked);
        editor.apply();
    }

    void setAlarmPeriod(int position) {
        editor.putInt(ALARM_PERIOD_POS, position);
        editor.apply();
    }

    int getAlarmPeriod() {
        return sharedPref.getInt(ALARM_PERIOD_POS, DEF_ALARM_PERIOD_POS);
    }

    void setWeekDaySelected(boolean isChecked, int index) {
        editor.putBoolean(WEEK_DAY_BOX + index, isChecked);
        editor.apply();
    }

    ArrayList<Boolean> getWeekDaysStates() {
        ArrayList<Boolean> weekDays = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            weekDays.add(isWeekDaySelected(i));
        }
        return weekDays;
    }

    private boolean isWeekDaySelected(int index) {
        return sharedPref.getBoolean(WEEK_DAY_BOX + index, false);
    }
}
