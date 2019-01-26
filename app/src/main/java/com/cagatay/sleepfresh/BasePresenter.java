package com.cagatay.sleepfresh;

public class BasePresenter<T extends BaseView> {

    T view;

    public BasePresenter(T view) {
        this.view = view;
    }
}
