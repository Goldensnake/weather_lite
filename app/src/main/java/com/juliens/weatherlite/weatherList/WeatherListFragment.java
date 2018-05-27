package com.juliens.weatherlite.weatherList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.juliens.weatherlite.R;
import com.juliens.weatherlite.data.WeatherData;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by juliens on 22/10/2017. Update on 26/05/2018
 */

public class WeatherListFragment extends Fragment implements WeatherListContract.View {
    @BindView(R.id.rc_weather_list)
    RecyclerView rcWeatherList;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_error_message)
    TextView tvErrorMessage;
    private WeatherListContract.Presenter mPresenter;
    private WeatherListAdapter listAdapter;

    public WeatherListFragment() {
        // Requires empty public constructor
    }

    public static WeatherListFragment newInstance() {
        return new WeatherListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("Init fragment");
        listAdapter = new WeatherListAdapter(new ArrayList<>(0));
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weather_list, container, false);
        ButterKnife.bind(this, root);

        rcWeatherList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        rcWeatherList.setAdapter(listAdapter);
        Logger.d("Set recycler view layout and adapter");
        listAdapter.getPositionClicks().subscribe(weatherData -> weatherSelected(weatherData));
        //TODO ScrollChildSwipeRefreshLayout :https://github.com/googlesamples/android-architecture/blob/todo-mvp-rxjava/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/tasks/TasksFragment.java
        return root;

    }

    private void weatherSelected(WeatherData data) {
        mPresenter.openWeatherDetails(data);
    }

    @Override
    public void setPresenter(WeatherListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
        Logger.d("Set presenter");
    }

    @Override
    public void setLoadingIndicator(boolean show) {
        if (show) {
            rcWeatherList.setVisibility(View.GONE);
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            rcWeatherList.setVisibility(View.VISIBLE);
            pbLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void showWeatherList(List<WeatherData> weatherData) {
        listAdapter.replaceData(weatherData);
        Logger.d("New data in the adapter");
    }

    @Override
    public void showLoadingWeatherError(String errorMessage) {
        tvErrorMessage.setText(errorMessage);
    }
}
