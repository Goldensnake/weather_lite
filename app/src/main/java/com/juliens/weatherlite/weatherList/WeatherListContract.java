package com.juliens.weatherlite.weatherList;

import com.juliens.weatherlite.BaseContract;

/**
 * Created by juliens on 22/10/2017.
 */

public interface WeatherListContract {
    interface View extends BaseContract.View{

    }

    interface Presenter extends BaseContract.Presenter<WeatherListContract.View>{

    }

    interface WeatherItemView{

    }
}
