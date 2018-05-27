package com.juliens.weatherlite.weatherList;

import android.support.annotation.NonNull;

import com.juliens.weatherlite.FragmentCallback;
import com.juliens.weatherlite.data.Temp;
import com.juliens.weatherlite.data.WeatherData;
import com.juliens.weatherlite.data.WeatherList;
import com.juliens.weatherlite.data.network.Service;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.Disposable;

/**
 * Created by juliens on 22/10/2017. Update on 26/05/2018
 */
public class WeatherListPresenter implements WeatherListContract.Presenter {
    @NonNull
    private final WeatherListContract.View mWeatherView;
    private Disposable mWeatherNetworkCall;
    private FragmentCallback callback;

    public WeatherListPresenter(@NonNull WeatherListContract.View weatherView) {
        mWeatherView = weatherView;
        mWeatherView.setPresenter(this);

    }

    @Override
    public void subscribe() {
        loadWeather();
    }

    @Override
    public void unsubscribe() {
        mWeatherNetworkCall.dispose();
    }

    @Override
    public void loadWeather() {
        mWeatherView.setLoadingIndicator(true);
        mWeatherNetworkCall = Service.getInstance().getListWeather("Paris", Temp.UnitFormat.METRIC, 5).subscribe(this::receiveWeatherDataList, this::receiveError);
        Logger.d("Load network weather data");
    }

    @Override
    public void openWeatherDetails(@NonNull WeatherData weatherData) {
        callback.loadDetailWeather(weatherData);
    }

    public void setOnItemSelected(FragmentCallback callback) {
        this.callback = callback;
    }

    private void receiveWeatherDataList(WeatherList weatherList) {
        mWeatherView.setLoadingIndicator(false);
        if (weatherList.getCod().equals("200"))
            mWeatherView.showWeatherList(weatherList.getList());
        else {
            mWeatherView.setLoadingIndicator(false);
            mWeatherView.showLoadingWeatherError(weatherList.getMessage());
        }
    }

    private void receiveError(Throwable throwable) {
        Logger.e("Reception error weather data: " + throwable.getMessage());
        mWeatherView.setLoadingIndicator(false);
        mWeatherView.showLoadingWeatherError(throwable.toString());
    }
}
