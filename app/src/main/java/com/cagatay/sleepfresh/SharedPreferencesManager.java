package com.cagatay.sleepfresh;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class SharedPreferencesManager {

    private final String AUTO_ALARM_PREF = "is_auto_alarm_set";
    private final String REGULAR_ALARM_PREF = "is_regular_alarm_set";

    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;

    SharedPreferencesManager(Context context){
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        editor.apply();
    }

    Boolean isAutoAlarmOn() {
        return sharedPref.getBoolean(AUTO_ALARM_PREF, false);
    }

    void setAutoAlarmPref(boolean isChecked){
        editor.putBoolean(AUTO_ALARM_PREF, isChecked);
        editor.apply();
    }

    Boolean isRegularAlarmOn() {
        return sharedPref.getBoolean(REGULAR_ALARM_PREF, false);
    }

    void setRegularAlarmPref(boolean isChecked){
        editor.putBoolean(REGULAR_ALARM_PREF, isChecked);
        editor.apply();
    }

}
