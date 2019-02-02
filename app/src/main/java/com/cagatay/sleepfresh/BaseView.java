package com.cagatay.sleepfresh;

import android.content.Context;

interface BaseView<T extends BasePresenter> {

    Context getContext();
}
