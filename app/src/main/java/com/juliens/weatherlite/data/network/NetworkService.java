package com.juliens.weatherlite.data.network;

import com.juliens.weatherlite.data.Weather;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by juliens on 12/10/2017.
 */

public interface NetworkService {
    @GET ("photos")
    Observable<List<Weather>> getListPhoto();
}
