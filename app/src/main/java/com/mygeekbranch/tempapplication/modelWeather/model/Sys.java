package com.mygeekbranch.tempapplication.modelWeather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("type")
    @Expose
    private  int type;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("country")
    @Expose
    private  String country;
    @SerializedName("sunrise")
    @Expose
    public int sunrise;
    @SerializedName("sunset")
    @Expose
    public int sunset;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}
