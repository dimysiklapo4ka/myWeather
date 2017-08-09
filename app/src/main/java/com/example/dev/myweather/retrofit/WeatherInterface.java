package com.example.dev.myweather.retrofit;

import com.example.dev.myweather.modelsWeather.Example;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dev on 09.08.17.
 */

public interface WeatherInterface {
    //?key=3badc9398a6d4a38b47120442170908&q=Dnepropetrovsk
   // @FormUrlEncoded
    @GET("/v1/current.json")
    Call<Example> getAllData(@Query("key") String key, @Query("q") String city);
}
