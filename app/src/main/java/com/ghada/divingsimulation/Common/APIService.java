package com.ghada.divingsimulation.Common;

import com.ghada.divingsimulation.Models.Weather.WeatherResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIService {


    @GET("/data/3.0/onecall")
    Call<WeatherResponse> Weather(@QueryMap Map<String, String> params);

}
