package com.example.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Call<WeatherData> getWeatherData(@Query("q") String city, @Query("lang") String lang, @Query("appid") String apiKey, @Query("units") String units);
}