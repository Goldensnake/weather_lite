package com.juliens.weatherlite.weatherList;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.juliens.weatherlite.R;
import com.juliens.weatherlite.util.ActivityUtils;

public class WeatherListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        //set the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //set the fragment
        WeatherListFragment listPhotoFragment =
                (WeatherListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (listPhotoFragment == null) {
            // Create the fragment
            listPhotoFragment = WeatherListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), listPhotoFragment, R.id.contentFrame);
        }
    }
}
