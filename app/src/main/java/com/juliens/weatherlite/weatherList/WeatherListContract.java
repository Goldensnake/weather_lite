package com.juliens.weatherlite.weatherList;

import android.support.annotation.NonNull;

import com.juliens.weatherlite.BasePresenter;
import com.juliens.weatherlite.BaseView;
import com.juliens.weatherlite.data.WeatherData;

import java.util.List;

/**
 * Created by juliens on 22/10/2017. Update on 26/05/2018
 */
public interface WeatherListContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean show);

        void showWeatherList(List<WeatherData> weatherData);

        void showLoadingWeatherError(String errorMessage);
    }

    interface Presenter extends BasePresenter {
        void loadWeather();

        void openWeatherDetails(@NonNull WeatherData weatherData);
    }
}
