package com.cagatay.sleepfresh;

public class AutomaticAlarmPresenter extends BasePresenter<AutomaticAlarmView> {

    private final SharedPreferencesManager spManager;

    AutomaticAlarmPresenter(AutomaticAlarmView view) {
        super(view);

        spManager = new SharedPreferencesManager(view.getContext());
    }

    void onResume() {
        boolean isAutoAlarmEnabled = spManager.isAutoAlarmOn();
        view.setSavedSwitchState(isAutoAlarmEnabled);
        view.toggleViewState(isAutoAlarmEnabled);
        view.setSavedAlarmPeriodSelection(spManager.getAlarmPeriod());
    }

    public void onAutoAlarmSwitched(boolean isChecked) {
        spManager.setAutoAlarmPref(isChecked);
        view.toggleViewState(isChecked);
    }

    public void onAlarmPeriodSelected(int position) {
        spManager.setAlarmPeriod(position);
    }
}
