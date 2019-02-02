package com.cagatay.sleepfresh;

import java.util.ArrayList;

public class RegularAlarmPresenter extends BasePresenter<RegularAlarmView> {

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
    }

    public void onRegularAlarmSwitched(boolean isChecked) {
        spManager.setRegularAlarmPref(isChecked);
        view.toggleViewState(isChecked);
    }

    public void onCheckWeekDay(boolean isChecked, int index) {
        spManager.setWeekDaySelected(isChecked, index);
    }
}
