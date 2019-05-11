package com.cagatay.sleepfresh;

import android.os.Bundle;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class RegularAlarmPresenter extends BasePresenter<RegularAlarmView> implements TimeSelectionListener {

    private final SharedPreferencesManager spManager;
    private final AlarmController alarmController;

    RegularAlarmPresenter(RegularAlarmView view) {
        super(view);

        spManager = new SharedPreferencesManager(view.getContext());
        alarmController = new AlarmController(view.getContext());
    }

    @Override
    public void onTimeSelected(int hourOfDay, int minute) {
        spManager.setAlarmTime(hourOfDay, minute);
        cancelRegularAlarms();
        createRegularAlarms();
        setTimePickerButtonText();
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
        if (isChecked) {
            createRegularAlarms();
        } else {
            cancelRegularAlarms();
        }
    }

    public void onCheckWeekDay(boolean isChecked, int index) {
        int calendarValue = dayIndexToCalendarValue(index);
        spManager.setWeekDaySelected(isChecked, calendarValue);
        if (isChecked) {
            createRegularAlarm(index);
        } else {
            cancelRegularAlarm(index);
        }
    }

    public void onSetTime() {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setTimeSelectionListener(this);

        Bundle bundle = new Bundle();
        bundle.putInt(TimePickerFragment.BUNDLE_HOUR, spManager.getAlarmTimeHour());
        bundle.putInt(TimePickerFragment.BUNDLE_MINUTE, spManager.getAlarmTimeMinute());
        timePickerFragment.setArguments(bundle);

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

    private void createRegularAlarms() {
        ArrayList<Boolean> dayStates = spManager.getWeekDaysStates();
        for (int i = 0; i < dayStates.size(); i++) {
            if (dayStates.get(i)) {
                createRegularAlarm(i);
            }
        }
    }

    private void cancelRegularAlarms() {
        for (int i = 0; i < 7; i++) {
            cancelRegularAlarm(i);
        }
    }

    private void createRegularAlarm(int day) {
        alarmController.createRegularAlarm(day, getAlarmTime(day));
    }

    private void cancelRegularAlarm(int day) {
        alarmController.cancelRegularAlarm(day);
    }

    private long getAlarmTime(int day) {
        int calendarDay = dayIndexToCalendarValue(day);

        Calendar alarmTime = Calendar.getInstance();
        alarmTime.set(Calendar.HOUR_OF_DAY, spManager.getAlarmTimeHour());
        alarmTime.set(Calendar.MINUTE, spManager.getAlarmTimeMinute());
        alarmTime.set(Calendar.SECOND, 0);
        alarmTime.set(Calendar.DAY_OF_WEEK, calendarDay);

        if (Calendar.getInstance().getTimeInMillis() > alarmTime.getTimeInMillis()) {
            alarmTime.add(Calendar.WEEK_OF_YEAR, 1);
        }
        return alarmTime.getTimeInMillis();
    }

    private int dayIndexToCalendarValue(int day) {
        // Sunday is starting day in Calendar library, and its value is 1
        // M T W T F S S
        // 0 1 2 3 4 5 6 -> Our index values
        // 2 3 4 5 6 7 1 -> Calendar values
        return ((day + 1) % 7 ) + 1;
    }
}
