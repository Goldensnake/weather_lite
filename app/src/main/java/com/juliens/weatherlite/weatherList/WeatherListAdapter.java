package com.juliens.weatherlite.weatherList;

import android.content.Context;
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
    private static final int VIEW_TYPE_TODAY = 0;
    private static final int VIEW_TYPE_FUTURE_DAY = 1;

    private final PublishSubject<WeatherData> onClickSubject = PublishSubject.create();
    private List<WeatherData> mWeatherData;

    private boolean mUseTodayLayout;

    WeatherListAdapter(Context context, List<WeatherData> data) {
        mWeatherData = data;
        mUseTodayLayout = context.getResources().getBoolean(R.bool.use_today_layout);
        Logger.i("Adapter created");
    }

    @NonNull
    @Override
    public WeatherListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId;
        switch (viewType) {
            case VIEW_TYPE_TODAY: {
                layoutId = R.layout.item_weather_today;
                break;
            }
            case VIEW_TYPE_FUTURE_DAY: {
                layoutId = R.layout.item_weather_futur;
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
        return new WeatherListHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(WeatherListHolder holder, int position) {
        WeatherData data = mWeatherData.get(position);
        holder.setWeather(data);

        holder.mView.setOnClickListener(v -> onClickSubject.onNext(data));
    }

    @Override
    public int getItemViewType(int position) {
        if (mUseTodayLayout && position == 0) {
            return VIEW_TYPE_TODAY;
        } else {
            return VIEW_TYPE_FUTURE_DAY;
        }
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
            int today = calendar.get(Calendar.DAY_OF_WEEK);
            calendar.setTimeInMillis(data.getDtInMillis());
            int itemDay = calendar.get(Calendar.DAY_OF_WEEK);

            if (today == itemDay)
                tvDate.setText("Today");
            else
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
