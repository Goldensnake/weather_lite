package com.juliens.weatherlite.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by juliens on 26/05/2018
 * <p>
 * From http://www.jsonschema2pojo.org/
 */
public class WeatherList implements Parcelable {
    public final static Parcelable.Creator<WeatherList> CREATOR = new Creator<WeatherList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WeatherList createFromParcel(Parcel in) {
            return new WeatherList(in);
        }

        public WeatherList[] newArray(int size) {
            return (new WeatherList[size]);
        }

    };
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("list")
    @Expose
    private List<WeatherData> list = null;

    protected WeatherList(Parcel in) {
        this.cod = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((Double.class.getClassLoader())));
        in.readList(this.list, (WeatherData.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public WeatherList() {
    }

    /**
     * @param message
     * @param cod
     * @param list
     */
    public WeatherList(String cod, String message, List<WeatherData> list) {
        super();
        this.cod = cod;
        this.message = message;
        this.list = list;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cod);
        dest.writeValue(message);
        dest.writeList(list);
    }

    public int describeContents() {
        return 0;
    }

}
