package com.juliens.weatherlite;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.juliens.weatherlite.data.WeatherData;
import com.juliens.weatherlite.util.ActivityUtils;
import com.juliens.weatherlite.weatherDetail.WeatherDetailFragment;
import com.juliens.weatherlite.weatherDetail.WeatherDetailPresenter;
import com.juliens.weatherlite.weatherList.WeatherListFragment;
import com.juliens.weatherlite.weatherList.WeatherListPresenter;
import com.orhanobut.logger.Logger;

/**
 * Created by juliens on 22/10/2017. Update on 26/05/2018
 */
public class WeatherActivity extends AppCompatActivity implements FragmentCallback {
    private WeatherListPresenter mWeatherListPresenter;
    private WeatherDetailPresenter mWeatherDetailPresenter;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        Logger.d("OnCreate activity");
        //set the toolbar
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);*/

        //set the fragment
        WeatherListFragment weatherListFragment =
                (WeatherListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (weatherListFragment == null) {
            // Create the fragment
            Logger.d("Fragment create");
            weatherListFragment = WeatherListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), weatherListFragment, R.id.contentFrame);

            mWeatherListPresenter = new WeatherListPresenter(weatherListFragment);
        }

        mWeatherListPresenter.setOnItemSelected(this);

        //Tablet mode
        if (findViewById(R.id.layout_two_pane) != null) {
            mTwoPane = true;

            initDetailFragment();
        }


    }

    @Override
    public void loadDetailWeather(WeatherData weatherData) {
        initDetailFragment();
        mWeatherDetailPresenter.setData(weatherData);
    }

    @Override
    public void showWeatherList() {

    }

    private void initDetailFragment() {
        WeatherDetailFragment toto = WeatherDetailFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, toto);
        transaction.addToBackStack(null);
        transaction.commit();


        mWeatherDetailPresenter = new WeatherDetailPresenter(toto);

        //set the fragment
        /*WeatherDetailFragment weatherDetailFragment =
                (WeatherDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (weatherDetailFragment == null) {
            // Create the fragment
            Logger.d("Fragment create");
            weatherDetailFragment = WeatherDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), weatherDetailFragment, R.id.contentFrame);

            mWeatherDetailPresenter = new WeatherDetailPresenter(weatherDetailFragment);
        }*/
    }
}
