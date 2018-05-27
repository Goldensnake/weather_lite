package com.juliens.weatherlite.data.network;

import com.juliens.weatherlite.data.Temp;
import com.juliens.weatherlite.data.WeatherList;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juliens on 12/10/2017. Update on 26/05/2018
 */
public class Service {
    @Nullable
    private static Service INSTANCE;
    private static NetworkService networkService;

    private Service(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        networkService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client) //for debug
                .build()
                .create(NetworkService.class);
    }

    //Singleton
    public static synchronized Service getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Service();
        }
        return INSTANCE;
    }

    public Observable<WeatherList> getListWeather(String town, Temp.UnitFormat unitFormat, int days) {
        return networkService.getWeatherForTown(town, Temp.UnitFormat.IMPERIAL.getValue(), 7, "8194ea842a9aef80a798c8ac0c320ec4")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
