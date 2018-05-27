package com.juliens.weatherlite;

import com.juliens.weatherlite.data.WeatherData;

/**
 * Created by juliens on 26/05/2018
 */
public interface FragmentCallback {
    void loadDetailWeather(WeatherData weatherData);

    void showWeatherList();
}
