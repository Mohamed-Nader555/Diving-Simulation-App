package com.ghada.divingsimulation.Common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceClient {

    private static Retrofit retrofitWeather = null;
    private static Retrofit retrofitAI = null;

    public static Retrofit getWeatherRetrofit() {
        if (retrofitWeather == null) {

            return retrofitWeather = new Retrofit.Builder()
                    .baseUrl(AppConstants.WEATHER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitWeather;
    }

    public static Retrofit getAIRetrofit() {
        if (retrofitAI == null) {

            return retrofitAI = new Retrofit.Builder()
                    .baseUrl(AppConstants.AI_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitAI;
    }

}
