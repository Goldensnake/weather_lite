package com.juliens.weatherlite.data.network;

import android.support.annotation.NonNull;

import com.juliens.weatherlite.data.WeatherList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by juliens on 12/10/2017. Update on 26/05/2018
 */
public interface NetworkService {
    @GET("forecast/daily")
    Observable<WeatherList> getWeatherForTown(@NonNull @Query("q") String Town,
                                              @Query("units") String unitFormat,
                                              @Query("cnt") int daysNumber,
                                              @Query("appid") String appId);
}
