package com.juliens.weatherlite.weatherDetail;

import com.juliens.weatherlite.BasePresenter;
import com.juliens.weatherlite.BaseView;
import com.juliens.weatherlite.data.WeatherData;

/**
 * Created by juliens on 27/05/2018
 */
public interface WeatherDetailContract {

    public interface View extends BaseView<Presenter> {
        void showDetail(WeatherData data);
    }

    public interface Presenter extends BasePresenter {
        void back();

        void setData(WeatherData data);
    }
}
