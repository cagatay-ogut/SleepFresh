package com.cagatay.sleepfresh;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import android.text.format.DateFormat;

interface TimeSelectionListener {
    void onTimeSelected(int hourOfDay, int minute);
}

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public static final String BUNDLE_HOUR = "hour";
    public static final String BUNDLE_MINUTE = "minute";

    private TimeSelectionListener timeSelectionListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        int hour = 0, minute = 0;
        if (getArguments() != null) {
            hour = getArguments().getInt(BUNDLE_HOUR);
            minute = getArguments().getInt(BUNDLE_MINUTE);
        }
        return new TimePickerDialog(getContext(), this, hour, minute, DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeSelectionListener.onTimeSelected(hourOfDay, minute);
    }

    void setTimeSelectionListener(TimeSelectionListener listener) {
        timeSelectionListener = listener;
    }
}
