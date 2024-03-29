package com.mygeekbranch.tempapplication.modelWeather;

import android.util.Log;

import com.google.gson.Gson;
import com.mygeekbranch.tempapplication.Singleton;
import com.mygeekbranch.tempapplication.modelWeather.model.WeatherRequest;

// Класс обработки строки полученной из погодного сервера
//вызывается другим классом GetUrlData
public class GetWeatherData {
    public  static void getData(String result){
        Gson gson = new Gson();
        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
        initWeather(weatherRequest);

    }
    public static void initWeather(WeatherRequest w) {
        String temp = String.format("%.2f", w.getMain().getTemp());
        Float tempF = w.getMain().getTemp();
        Singleton.getSingleton().setTemperature(temp);
        Singleton.getSingleton().setTemperatureFloat(tempF);
        Log.d("TEMPERATURE =", tempF.toString());


    }
}
