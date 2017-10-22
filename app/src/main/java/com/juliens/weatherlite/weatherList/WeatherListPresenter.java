package com.juliens.weatherlite.weatherList;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.util.Log;

import com.juliens.weatherlite.BasePresenter;

import static android.content.ContentValues.TAG;

/**
 * Created by juliens on 22/10/2017.
 */

public class WeatherListPresenter extends BasePresenter<WeatherListContract.View> implements WeatherListContract.Presenter {
    private Bundle viewStateBundle = getStateBundle();

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
        /*if (viewStateBundle.getBoolean(PROGRESS_BAR_STATE_KEY)) {
            if (isViewAttached())
                getView().showProgress();
        }*/
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        if (isViewAttached()){
            //getView()
        }

    }

    @Override
    public void onPresenterDestroy() {
        super.onPresenterDestroy();
        Log.d(TAG, "Presenter destroyed");
    }
}
