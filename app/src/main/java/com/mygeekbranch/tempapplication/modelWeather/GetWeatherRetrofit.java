package com.mygeekbranch.tempapplication.modelWeather;
//Класс получение данных с погодного сервера используя Retrofit

import android.content.SharedPreferences;
import android.util.Log;

import com.mygeekbranch.tempapplication.MainActivity;
import com.mygeekbranch.tempapplication.Singleton;
import com.mygeekbranch.tempapplication.dataBase.App;
import com.mygeekbranch.tempapplication.modelWeather.model.Weather;
import com.mygeekbranch.tempapplication.modelWeather.model.WeatherRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetWeatherRetrofit {
    private static final float AbsoluteZero = -273.15f;
    //private static final String city = "Tokyo";
    // private static final String city = Singleton.getSingleton().getCurrentCity();
//    private static  final SharedPreferences sharedPreferences = MainActivity.getInstance().getSharedPreferences("CitySP",0);
//    private static final String city2 = sharedPreferences.getString("currentCity", "City");


    private static final String WEATHER_API_KEY = "f61adcb6ab99fd0e42d9728a4eea3df7";
    public static OpenWeather openWeather;

    public static void initRetrofit() {
       String city = Singleton.getSingleton().getCurrentCity();


        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
       // "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&units=metric&appid="
        //"https://api.openweathermap.org/data/2.5/weather?q=Cheboksary,RU&units=metric&appid=
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openWeather = retrofit.create(OpenWeather.class);
        requestRetrofit(city, WEATHER_API_KEY);

        Log.d("RETROFIT","initRetrofit");
        Log.d("RETROFIT", "город " +city);


    }
     public static void requestRetrofit(String city, String keyApi){
         Log.d("RETROFIT","requestRetrofit");
        openWeather.loadWeather(city, keyApi, "metric" )
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        Log.d("RETROFIT","onResponse");
                        if (response.body() != null){
                            float result = response.body().getMain().getTemp();
                            Singleton.getSingleton().setTemperatureFloat(result);

                            String icon = response.body().getWeather().get(0).getIcon();
                            Singleton.getSingleton().setIcon(icon);

                            Log.d("RETROFIT", "teerature " + String.valueOf(result ));

                            Log.d("RETROFIT", "icon "+ icon);

                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        String tt = t.toString();
                        Log.d("RETROFIT  ","onFailure =" +tt);


                    }
                });

     }

}
