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

    private TimeSelectionListener timeSelectionListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new TimePickerDialog(getContext(), this, 9, 9, DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeSelectionListener.onTimeSelected(hourOfDay, minute);
    }

    void setTimeSelectionListener(TimeSelectionListener listener) {
        timeSelectionListener = listener;
    }
}
