package com.juliens.weatherlite.weatherList;

import android.os.Bundle;

import com.juliens.weatherlite.BaseFragment;

/**
 * Created by juliens on 22/10/2017.
 */

public class WeatherListFragment extends BaseFragment<WeatherListContract.View,WeatherListContract.Presenter> implements WeatherListContract.View {
    @Override
    protected WeatherListContract.Presenter initPresenter() {
        return new WeatherListPresenter();
    }

    public WeatherListFragment() {
        // Requires empty public constructor
    }

    public static WeatherListFragment newInstance() {
        Bundle arguments = new Bundle();
        WeatherListFragment fragment = new WeatherListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
}
