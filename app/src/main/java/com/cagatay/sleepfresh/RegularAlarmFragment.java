package com.cagatay.sleepfresh;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.cagatay.sleepfresh.databinding.FragmentRegularAlarmBinding;

public class RegularAlarmFragment extends Fragment implements RegularAlarmView {

    private FragmentRegularAlarmBinding binding;
    private RegularAlarmPresenter presenter;

    public RegularAlarmFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new RegularAlarmPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_regular_alarm, container, false);
        binding.setPresenter(presenter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.onResume();
    }

    @Override
    public void setSavedSwitchState(boolean isChecked) {
        binding.switchRegularAlarm.setChecked(isChecked);
    }

    @Override
    public void setSavedWeekDaySelection(boolean isChecked, int index) {
        CheckBox weekDay = (CheckBox) binding.layoutDayCheckboxes.getChildAt(index);
        weekDay.setChecked(isChecked);
    }

    @Override
    public void toggleViewState(boolean isEnabled) {
        int backgroundColor = isEnabled ? getResources().getColor(R.color.primaryLightColor) :
                getResources().getColor(R.color.primaryDarkColor);
        binding.layoutRegularAlarm.setBackgroundColor(backgroundColor);
        switchButtonStates(isEnabled);
    }

    @Override
    public void showTimePickerFragment(DialogFragment fragment) {
        if (isAdded()) {
            fragment.show(requireActivity().getSupportFragmentManager(), "TimePicker");
        }
    }

    @Override
    public void setSavedTimeText(String text) {
        binding.buttonTimePicker.setText(text);
    }

    private void switchButtonStates(boolean isEnabled) {
        for(int i = 0; i < binding.layoutDayCheckboxes.getChildCount(); i++) {
            View child = binding.layoutDayCheckboxes.getChildAt(i);
            child.setEnabled(isEnabled);
        }
        binding.buttonTimePicker.setEnabled(isEnabled);
    }
}
