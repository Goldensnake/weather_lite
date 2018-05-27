package com.juliens.weatherlite.weatherList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juliens.weatherlite.R;
import com.juliens.weatherlite.data.WeatherData;
import com.orhanobut.logger.Logger;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by juliens on 22/10/2017. Update on 26/05/2018
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherListHolder>{
    private final PublishSubject<WeatherData> onClickSubject = PublishSubject.create();
    private List<WeatherData> mWeatherData;

    WeatherListAdapter(List<WeatherData> data) {
        mWeatherData = data;
        Logger.i("Adapter created");
    }

    @NonNull
    @Override
    public WeatherListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherListHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_list, parent, false));
    }

    @Override
    public void onBindViewHolder(WeatherListHolder holder, int position) {
        WeatherData data = mWeatherData.get(position);
        holder.setWeather(data);

        holder.mView.setOnClickListener(v -> onClickSubject.onNext(data));
    }

    public Observable<WeatherData> getPositionClicks() {
        return onClickSubject.hide();
    }

    @Override
    public int getItemCount() {
        return mWeatherData.size();
    }

    private void setList(List<WeatherData> data) {
        mWeatherData = checkNotNull(data);
    }

    public void replaceData(List<WeatherData> data) {
        setList(data);
        notifyDataSetChanged();
    }


    public class WeatherListHolder extends RecyclerView.ViewHolder {
        private final View mView;
        @BindView(R.id.date)
        TextView tvDate;
        @BindView(R.id.weather_icon)
        ImageView ivIcon;
        @BindView(R.id.weather_description)
        TextView tvWeatherDescription;
        @BindView(R.id.high_temperature)
        TextView tvHighTemperature;
        @BindView(R.id.low_temperature)
        TextView tvLowTemperature;

        WeatherListHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }

        void setWeather(WeatherData data) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(data.getDt());
            calendar.get(Calendar.DAY_OF_WEEK);
            tvDate.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
            tvWeatherDescription.setText(data.getWeather().get(0).getDescription());
            tvHighTemperature.setText(String.valueOf(data.getTemp().getMax()));
            tvLowTemperature.setText(String.valueOf(data.getTemp().getMin()));
            Glide.with(mView.getContext())
                    .load("http://openweathermap.org/img/w/" + data.getWeather().get(0).getIcon() + ".png")
                    .into(ivIcon);
        }
    }
}
