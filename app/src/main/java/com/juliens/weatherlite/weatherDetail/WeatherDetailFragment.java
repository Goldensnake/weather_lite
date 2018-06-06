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

import com.bumptech.glide.Glide;
import com.juliens.weatherlite.R;
import com.juliens.weatherlite.data.WeatherData;

import java.util.Calendar;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, root);

        return root;
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
        if (data != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(data.getDtInMillis());
            tvDate.setText(calendar.getTime().toString());
            tvWeatherDescription.setText(data.getWeather().get(0).getDescription());
            tvHighTemperature.setText(String.valueOf(data.getTemp().getMax()));
            tvLowTemperature.setText(String.valueOf(data.getTemp().getMin()));
            tvHumidity.setText(String.valueOf(data.getHumidity().toString()));
            tvPressure.setText(String.valueOf(data.getPressure()));
            tvWindMeasurement.setText(String.valueOf(data.getSpeed()));
            Glide.with(getContext())
                    .load("http://openweathermap.org/img/w/" + data.getWeather().get(0).getIcon() + ".png")
                    .into(ivWeatherIcon);
        }
    }
}
