package com.mygeekbranch.tempapplication.modelWeather;
//Класс получение данных с погодного сервера 2-й способ
// и сохранения результата в строке

import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.mygeekbranch.tempapplication.MainActivity;
import com.mygeekbranch.tempapplication.Singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class GetUrlData {
    private final static String TAG = "Weather";
    private final static String WEATHER_URL =
            // "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&appid=";
            //"https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&units=metric&appid=";
            "https://api.openweathermap.org/data/2.5/weather?q=Cheboksary,RU&units=metric&appid=";
    private static final String WEATHER_API_KEY = "f61adcb6ab99fd0e42d9728a4eea3df7";
    public static String result;


    public static String Init() {
        Log.d("WeatherInit", "Init");
        try {
            final URL uri = new URL(WEATHER_URL + WEATHER_API_KEY);
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpsURLConnection urlConnection = null;

                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        result = geLines(in);
                        Log.d(TAG, result);
                        MainActivity.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GetWeatherData.getData(result);
                            }
                        });


                    } catch (IOException e) {
                        Log.e(TAG, "FAIL CONECTION", e);
                        e.printStackTrace();

                        MainActivity.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.getInstance().showError();
                            }
                        });

//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                MainActivity.getInstance().showError();
//                            }
//                        });
                    } finally {
                        if (urlConnection != null)
                            urlConnection.disconnect();

                    }
                }
            }).start();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG, "MalformedURLException", e);
        }
        return result;
    }

    private static String geLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }




}
