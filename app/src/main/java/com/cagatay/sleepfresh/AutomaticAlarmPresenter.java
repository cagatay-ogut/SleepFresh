package com.cagatay.sleepfresh;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class AutomaticAlarmPresenter extends BasePresenter<AutomaticAlarmView> {

    private final SharedPreferencesManager spManager;
    private final AlarmController alarmController;
    private final BroadcastReceiver screenStatusReceiver = new ScreenStatusReceiver();

    AutomaticAlarmPresenter(AutomaticAlarmView view) {
        super(view);

        spManager = new SharedPreferencesManager(view.getContext());
        alarmController = new AlarmController(view.getContext());
    }

    void onResume() {
        boolean isAutoAlarmEnabled = spManager.isAutoAlarmOn();
        view.setSavedSwitchState(isAutoAlarmEnabled);
        view.toggleViewState(isAutoAlarmEnabled);
        view.setSavedAlarmPeriodSelection(spManager.getAlarmPeriod());

        registerScreenStatusIntent(isAutoAlarmEnabled);
    }

    public void onAutoAlarmSwitched(boolean isChecked) {
        spManager.setAutoAlarmPref(isChecked);
        view.toggleViewState(isChecked);
        registerScreenStatusIntent(isChecked);
    }

    public void onAlarmPeriodSelected(int position) {
        spManager.setAlarmPeriod(position);
    }

    private void registerScreenStatusIntent(boolean shouldListen) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT); // user unlocks the device
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        if (shouldListen) {
            view.getContext().registerReceiver(screenStatusReceiver, filter);
        } else {
            alarmController.cancelAutoAlarm();
            try {
                view.getContext().unregisterReceiver(screenStatusReceiver);
            } catch (IllegalArgumentException e) {
                Log.i("AutomaticAlarmPresenter", "broadcast receiver is not registered");
            }
        }
    }
}
