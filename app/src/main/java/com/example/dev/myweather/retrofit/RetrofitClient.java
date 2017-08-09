package com.example.dev.myweather.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dev on 09.08.17.
 */
public class RetrofitClient {

    private static String BASE_URL = "http://api.apixu.com";

    private static Retrofit getRetrofitInstance(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static WeatherInterface getWeatherInterface(){
        return getRetrofitInstance().create(WeatherInterface.class);
    }


}
