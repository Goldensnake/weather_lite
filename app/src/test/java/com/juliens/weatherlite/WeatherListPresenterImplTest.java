package com.juliens.weatherlite;

import com.juliens.weatherlite.data.Temp;
import com.juliens.weatherlite.data.WeatherData;
import com.juliens.weatherlite.data.WeatherList;
import com.juliens.weatherlite.data.network.Service;
import com.juliens.weatherlite.weatherList.WeatherListContract;
import com.juliens.weatherlite.weatherList.WeatherListPresenter;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherListPresenterImplTest {
    @Mock
    private Service service;
    @Mock
    private WeatherListContract.View view;

    private WeatherListPresenter presenter;

    @BeforeClass
    public static void setUpClass() {

        // Override the default "out of the box" AndroidSchedulers.mainThread() Scheduler
        //
        // This is necessary here because otherwise if the static initialization block in AndroidSchedulers
        // is executed before this, then the Android SDK dependent version will be provided instead.
        //
        // This would cause a java.lang.ExceptionInInitializerError when running the test as a
        // Java JUnit test as any attempt to resolve the default underlying implementation of the
        // AndroidSchedulers.mainThread() will fail as it relies on unavailable Android dependencies.

        // Comment out this line to see the java.lang.ExceptionInInitializerError
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
    }

    @AfterClass
    public static void tearDownClass() {
        // Not strictly necessary because we can't reset the value set by setInitMainThreadSchedulerHandler,
        // but it doesn't hurt to clean up anyway.
        RxAndroidPlugins.reset();
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new WeatherListPresenter(view);
    }

    @Test
    public final void loadWeather() {
        //init
        ArrayList<WeatherData> listWeather = new ArrayList<>();
        listWeather.add(new WeatherData());
        WeatherList weatherList = new WeatherList("200", null, listWeather);
        when(service.getListWeather("Paris", Temp.UnitFormat.METRIC, 5))
                .thenReturn(Observable.just(weatherList));

        //InOrder inOrder = Mockito.inOrder(view); //test
        presenter.loadWeather();
        verify(view, times(1)).setLoadingIndicator(true);
        verify(view, times(1)).setLoadingIndicator(false);
        verify(view, times(1)).showWeatherList(weatherList.getList());
    }


}
