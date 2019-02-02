package com.cagatay.sleepfresh;

class BasePresenter<T extends BaseView> {

    final T view;

    BasePresenter(T view) {
        this.view = view;
    }
}
