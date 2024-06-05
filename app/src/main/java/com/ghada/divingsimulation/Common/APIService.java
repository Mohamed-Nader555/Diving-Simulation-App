package com.ghada.divingsimulation.Common;

import com.ghada.divingsimulation.Models.API.PredictionResponse;
import com.ghada.divingsimulation.Models.Weather.WeatherResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface APIService {


    @GET("/data/3.0/onecall")
    Call<WeatherResponse> Weather(@QueryMap Map<String, String> params);


    @Headers({"Content-Type:application/json",
            "Accept:text/plain"})
    @POST("/predict")
    Call<PredictionResponse> Prediction_AI(@Body Map<String, String> params);

}
