package com.juliens.weatherlite.weatherList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.juliens.weatherlite.FragmentCallback;
import com.juliens.weatherlite.R;
import com.juliens.weatherlite.data.WeatherData;
import com.juliens.weatherlite.util.ActivityUtils;
import com.juliens.weatherlite.weatherDetail.WeatherDetailActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by juliens on 22/10/2017. Update on 26/05/2018
 */
public class WeatherListActivity extends AppCompatActivity implements FragmentCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        //set the fragment
        WeatherListFragment weatherListFragment =
                (WeatherListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (weatherListFragment == null) {
            // Create the fragment
            Logger.d("Fragment create");
            weatherListFragment = WeatherListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), weatherListFragment, R.id.contentFrame);
        }
        WeatherListPresenter mWeatherListPresenter = new WeatherListPresenter(weatherListFragment); //TODO dependancy injection
        mWeatherListPresenter.setOnItemSelected(this);
    }

    @Override
    public void loadDetailWeather(@NonNull WeatherData weatherData) {
        Bundle b = new Bundle();
        b.putParcelable("weather", weatherData);

        final Intent intent = new Intent(this, WeatherDetailActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
