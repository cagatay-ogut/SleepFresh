package com.cagatay.sleepfresh;

interface RegularAlarmView extends BaseView {

    void setSavedSwitchState(boolean isChecked);
    void setSavedWeekDaySelection(boolean isChecked, int index);
    void toggleViewState(boolean isEnabled);
}
