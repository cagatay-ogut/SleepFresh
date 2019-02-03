package com.cagatay.sleepfresh;

import android.support.v4.app.DialogFragment;

interface RegularAlarmView extends BaseView {

    void setSavedSwitchState(boolean isChecked);
    void setSavedWeekDaySelection(boolean isChecked, int index);
    void toggleViewState(boolean isEnabled);
    void showTimePickerFragment(DialogFragment fragment);
    void setSavedTimeText(String text);
}
