package com.mygeekbranch.tempapplication;

import com.mygeekbranch.tempapplication.modelWeather.WeatherInit;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton sSingleton;
    String temperature;
    private float temperatureFloat;
    private boolean isCheckHumidity;
    private boolean isCheckNightMode;
    private List<String> cityList;
    private  String currentCity;
    private  int mainFragmentCount;



    public Singleton() {

        temperature = "0";
        temperatureFloat = 0.0f;
        isCheckHumidity = false;
        isCheckNightMode = false;
        cityList = new ArrayList<>();
        cityList.add("Cheboksary");
        currentCity = "Cheboksary";
        mainFragmentCount = 0;

    }

    public static Singleton getSingleton() {
        if (sSingleton == null)
            sSingleton = new Singleton();
        return sSingleton;
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

