package com.mygeekbranch.tempapplication;

import android.content.SharedPreferences;

import com.mygeekbranch.tempapplication.modelWeather.WeatherInit;

import java.util.ArrayList;
import java.util.List;
// Класс синглтон для сохнанения сосояний
public class Singleton {
    private static Singleton sSingleton;
    String temperature;
    private float temperatureFloat;
    private boolean isCheckHumidity;  // для сохранения состояния чекбокс "Влажность"
    private boolean isCheckNightMode;
    private List<String> cityList;
    private  String currentCity;
    private  int mainFragmentCount;
    private String icon;




    public Singleton() {

        temperature = "0";
        temperatureFloat = 0.0f;
        isCheckHumidity = false;
        isCheckNightMode = false;
        cityList = new ArrayList<>();
        cityList.add("Cheboksary");
        currentCity = "Cheboksary";
        mainFragmentCount = 0;
        icon = "10d";

    }

    public static Singleton getSingleton() {
        if (sSingleton == null)
            sSingleton = new Singleton();
        return sSingleton;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMainFragmentCount() {
        return mainFragmentCount;
    }

    public void setMainFragmentCount(int mainFragmentCount) {
        this.mainFragmentCount = mainFragmentCount;
    }

    public float getTemperatureFloat() {
        return temperatureFloat;
    }

    public void setTemperatureFloat(float temperatureFloat) {
        this.temperatureFloat = temperatureFloat;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public List<String> getCityList() {
        return cityList;
    }



    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public boolean isCheckHumidity() {
        return isCheckHumidity;
    }

    public void setCheckHumidity(boolean checkHumidity) {
        isCheckHumidity = checkHumidity;
    }

    public boolean isCheckNightMode() {
        return isCheckNightMode;
    }

    public void setCheckNightMode(boolean checkNightMode) {
        isCheckNightMode = checkNightMode;
    }

    public void increaseTemperature() {
        temperatureFloat++;

    }

    public void decreaseTemperature() {
        temperatureFloat--;
    }


}

