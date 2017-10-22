package com.juliens.weatherlite;

/**
 * Created by juliens on 11/10/2017.
 */

// From google android architecture
public interface BaseView<T> {

    void setPresenter(T presenter);

}
