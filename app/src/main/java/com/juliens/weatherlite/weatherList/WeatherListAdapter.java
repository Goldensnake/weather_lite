package com.juliens.weatherlite.weatherList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by juliens on 22/10/2017.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherListHolder>{


    @Override
    public WeatherListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WeatherListHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class WeatherListHolder extends RecyclerView.ViewHolder implements WeatherListContract.WeatherItemView {
        public WeatherListHolder(View itemView) {
            super(itemView);
        }
    }
}
