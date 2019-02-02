package com.cagatay.sleepfresh;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cagatay.sleepfresh.databinding.FragmentAutomaticAlarmBinding;

@SuppressWarnings("ALL")
public class AutomaticAlarmFragment extends Fragment implements AutomaticAlarmView {

    private FragmentAutomaticAlarmBinding binding;
    private AutomaticAlarmPresenter presenter;

    public AutomaticAlarmFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new AutomaticAlarmPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_automatic_alarm, container, false);
        binding.setPresenter(presenter);
        return binding.getRoot();
    }

    @Override
    public void toggleViewState(boolean isEnabled) {
        int backgroundColor = isEnabled ? getResources().getColor(R.color.primaryLightColor) :
                getResources().getColor(R.color.primaryDarkColor);
        binding.layoutAutomaticAlarm.setBackgroundColor(backgroundColor);
        binding.spinnerAlarmPeriods.setClickable(isEnabled);
    }
}
