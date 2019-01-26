package com.cagatay.sleepfresh;

public class RegularAlarmPresenter extends BasePresenter<RegularAlarmView> {

    public RegularAlarmPresenter(RegularAlarmView view) {
        super(view);
    }

    public void onRegularAlarmSwitched(boolean isChecked) {
        view.toggleViewState(isChecked);
    }
}
