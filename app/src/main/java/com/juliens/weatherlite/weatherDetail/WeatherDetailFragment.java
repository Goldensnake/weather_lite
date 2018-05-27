package com.juliens.weatherlite.weatherDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juliens.weatherlite.R;
import com.juliens.weatherlite.data.WeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by juliens on 27/05/2018
 */
public class WeatherDetailFragment extends Fragment implements WeatherDetailContract.View {
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_weather_description)
    TextView tvWeatherDescription;
    @BindView(R.id.tv_high_temperature)
    TextView tvHighTemperature;
    @BindView(R.id.tv_low_temperature)
    TextView tvLowTemperature;
    @BindView(R.id.tv_humidity)
    TextView tvHumidity;
    @BindView(R.id.tv_pressure)
    TextView tvPressure;
    @BindView(R.id.tv_wind_measurement)
    TextView tvWindMeasurement;
    @BindView(R.id.iv_weather_icon)
    ImageView ivWeatherIcon;

    private WeatherDetailContract.Presenter mPresenter;

    public WeatherDetailFragment() {
        // Requires empty public constructor
    }

    public static WeatherDetailFragment newInstance() {
        return new WeatherDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, root);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(WeatherDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showDetail(WeatherData data) {
        tvDate.setText("");
        tvLowTemperature.setText(data.getTemp().getMin());
        tvHighTemperature.setText(data.getTemp().getMax());
    }
}
