package com.mygeekbranch.tempapplication.modelWeather;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mygeekbranch.tempapplication.MainActivity;

// Класс Servise Для получения данных из API
public class GetWeatherService extends IntentService {

    public GetWeatherService() {
        super("GetWeatherService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // to do
        Toast.makeText(getApplicationContext(), "Запущен запрос погоды", Toast.LENGTH_SHORT).show();
        //MainActivity.getInstance().WeatherInit();
        //WeatherInit.Init();
        GetUrlData.Init();

    }



}
