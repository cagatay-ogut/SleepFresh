package com.cagatay.sleepfresh;

public class RegularAlarmPresenter extends BasePresenter<RegularAlarmView> {

    private final SharedPreferencesManager spManager;

    RegularAlarmPresenter(RegularAlarmView view) {
        super(view);

        spManager = new SharedPreferencesManager(view.getContext());
    }

    void onResume() {
        Boolean isRegularAlarmEnabled = spManager.isRegularAlarmOn();
        view.setSwitchState(isRegularAlarmEnabled);
        view.toggleViewState(isRegularAlarmEnabled);
    }

    public void onRegularAlarmSwitched(boolean isChecked) {
        spManager.setRegularAlarmPref(isChecked);
        view.toggleViewState(isChecked);
    }
}
