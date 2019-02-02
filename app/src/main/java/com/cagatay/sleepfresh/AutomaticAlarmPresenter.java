package com.cagatay.sleepfresh;

public class AutomaticAlarmPresenter extends BasePresenter<AutomaticAlarmView> {

    private final SharedPreferencesManager spManager;

    AutomaticAlarmPresenter(AutomaticAlarmView view) {
        super(view);

        spManager = new SharedPreferencesManager(view.getContext());
    }

    void onResume() {
        Boolean isAutoAlarmEnabled = spManager.isAutoAlarmOn();
        view.setSwitchState(isAutoAlarmEnabled);
        view.toggleViewState(isAutoAlarmEnabled);
    }

    public void onAutoAlarmSwitched(boolean isChecked) {
        spManager.setAutoAlarmPref(isChecked);
        view.toggleViewState(isChecked);
    }
}
