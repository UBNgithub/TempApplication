package com.mygeekbranch.tempapplication.modelWeather;

import com.mygeekbranch.tempapplication.modelWeather.model.WeatherRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeather {
  //  "https://api.openweathermap.org/data/2.5/weather?lat=55.75&lon=37.62&units=metric&appid="
  //"https://api.openweathermap.org/data/2.5/weather?q=Cheboksary,RU&units=metric&appid=
@GET("data/2.5/weather")
    Call<WeatherRequest> loadWeather (@Query("q") String citiCountry,
                                      @Query("appid")String keyApi,
@Query("units") String units);
}
