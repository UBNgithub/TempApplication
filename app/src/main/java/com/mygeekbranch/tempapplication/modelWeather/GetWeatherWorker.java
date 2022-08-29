package com.mygeekbranch.tempapplication.modelWeather;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.mygeekbranch.tempapplication.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class GetWeatherWorker extends Worker {
    private static final String WORK_MANAGER ="WorkManager";
    private final static String TAG = "Weather";
    private final static String WEATHER_URL =
            "https://api.openweathermap.org/data/2.5/weather?q=Cheboksary,RU&units=metric&appid=";
    private static final String WEATHER_API_KEY = "f61adcb6ab99fd0e42d9728a4eea3df7";
    public static String result;


    public GetWeatherWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
//        Log.d(WORK_MANAGER,"doWork: start");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//
//        }
//        Log.d(WORK_MANAGER,"doWork: finish");
        //GetUrlData.Init();
        Init();



        return Result.success();
    }

    public static String Init() {
        Log.d("WeatherInit", "Init");
        try {
            final URL uri = new URL(WEATHER_URL + WEATHER_API_KEY);


                    HttpsURLConnection urlConnection = null;

                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(10000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        result = geLines(in);
                        Log.d(TAG, result);

                        GetWeatherData.getData(result);

                    } catch (IOException e) {
                        Log.e(TAG, "FAIL CONECTION", e);
                        e.printStackTrace();
                        MainActivity.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.getInstance().showError();
                            }
                        });
                       // MainActivity.getInstance().showError();


                    } finally {
                        if (urlConnection != null)
                            urlConnection.disconnect();

                    }


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
