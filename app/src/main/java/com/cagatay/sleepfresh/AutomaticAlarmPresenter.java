package com.cagatay.sleepfresh;

public class AutomaticAlarmPresenter extends BasePresenter<AutomaticAlarmView> {

    AutomaticAlarmPresenter(AutomaticAlarmView view) {
        super(view);
    }

    public void onAutoAlarmSwitched(boolean isChecked) {
        view.toggleViewState(isChecked);
    }
}
