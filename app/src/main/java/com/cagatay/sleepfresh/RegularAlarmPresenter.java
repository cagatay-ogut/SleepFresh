package com.cagatay.sleepfresh;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RegularAlarmPresenter extends BasePresenter<RegularAlarmView> implements TimeSelectionListener {

    private final SharedPreferencesManager spManager;

    RegularAlarmPresenter(RegularAlarmView view) {
        super(view);

        spManager = new SharedPreferencesManager(view.getContext());
    }

    void onResume() {
        boolean isRegularAlarmEnabled = spManager.isRegularAlarmOn();
        view.setSavedSwitchState(isRegularAlarmEnabled);
        view.toggleViewState(isRegularAlarmEnabled);

        ArrayList<Boolean> weekDaysSelection = spManager.getWeekDaysStates();
        for (int i = 0; i < weekDaysSelection.size(); i++) {
            view.setSavedWeekDaySelection(weekDaysSelection.get(i), i);
        }

        setTimePickerButtonText();
    }

    public void onRegularAlarmSwitched(boolean isChecked) {
        spManager.setRegularAlarmPref(isChecked);
        view.toggleViewState(isChecked);
    }

    public void onCheckWeekDay(boolean isChecked, int index) {
        spManager.setWeekDaySelected(isChecked, index);
    }

    public void onSetTime() {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setTimeSelectionListener(this);
        view.showTimePickerFragment(timePickerFragment);
    }

    private void setTimePickerButtonText() {
        int hour = spManager.getAlarmTimeHour();
        int minute = spManager.getAlarmTimeMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        boolean is24Hour = DateFormat.is24HourFormat(view.getContext());
        String datePattern = is24Hour ? "HH:mm" : "hh:mm a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern, Locale.getDefault());
        String selectedTime = dateFormat.format(calendar.getTime());
        view.setSavedTimeText(selectedTime);
    }

    @Override
    public void onTimeSelected(int hourOfDay, int minute) {
        spManager.setAlarmTime(hourOfDay, minute);
        setTimePickerButtonText();
    }
}
