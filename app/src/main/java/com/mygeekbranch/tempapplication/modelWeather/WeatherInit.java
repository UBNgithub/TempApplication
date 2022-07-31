package com.mygeekbranch.tempapplication.modelWeather;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.mygeekbranch.tempapplication.MainActivity;
import com.mygeekbranch.tempapplication.MainFragment;
import com.mygeekbranch.tempapplication.R;
import com.mygeekbranch.tempapplication.Singleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;


public class WeatherInit {



    private final static String TAG = "Weather";
    private final static String WEATHER_URL =
            // "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&appid=";
            "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&units=metric&appid=";
    private static final String WEATHER_API_KEY = "f61adcb6ab99fd0e42d9728a4eea3df7";


    public static void Init(Context context) {
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
                        String result = geLines(in);
                        Log.d(TAG, result);

                        Gson gson = new Gson();
                        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                initWeather(weatherRequest);
                            }
                        });


                    } catch (IOException e) {
                        Log.e(TAG, "FAIL CONECTION", e);
                        e.printStackTrace();
                    } finally {
                        if (urlConnection != null)
                            urlConnection.disconnect();
                    }


                }
            }).start();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    private static String geLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    public static void initWeather(WeatherRequest w) {
        String temp = String.format("%.2f", w.getMain().getTemp());


        Singleton.getSingleton().setTemperature(temp);
        Log.d("TEMPERATURE =", temp);


    }
}
