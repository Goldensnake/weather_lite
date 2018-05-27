package com.juliens.weatherlite.weatherDetail;

import android.support.annotation.NonNull;

import com.juliens.weatherlite.data.WeatherData;

/**
 * Created by juliens on 27/05/2018
 */
public class WeatherDetailPresenter implements WeatherDetailContract.Presenter {
    private WeatherDetailContract.View mWeatherView;
    private WeatherData weatherData;

    public WeatherDetailPresenter(@NonNull WeatherDetailContract.View weatherView) {
        mWeatherView = weatherView;
        mWeatherView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void back() {

    }

    @Override
    public void setData(WeatherData weatherData) {
        this.weatherData = weatherData;
        mWeatherView.showDetail(weatherData);
    }
}
