package com.cagatay.sleepfresh;

interface AutomaticAlarmView extends BaseView {

    void setSavedSwitchState(boolean isChecked);
    void setSavedAlarmPeriodSelection(int position);
    void toggleViewState(boolean isEnabled);
}
