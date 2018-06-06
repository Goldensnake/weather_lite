package com.juliens.weatherlite.weatherDetail;

import android.support.annotation.NonNull;

import com.juliens.weatherlite.data.WeatherData;

/**
 * Created by juliens on 27/05/2018
 */
public class WeatherDetailPresenter implements WeatherDetailContract.Presenter {
    private WeatherDetailContract.View mWeatherView;
    private WeatherData mWeatherData;

    public WeatherDetailPresenter(@NonNull WeatherDetailContract.View weatherView) {
        mWeatherView = weatherView;
        mWeatherView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        mWeatherView.showDetail(mWeatherData);
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void back() {

    }

    @Override
    public void setData(@NonNull WeatherData weatherData) {
        mWeatherData = weatherData;
    }
}
