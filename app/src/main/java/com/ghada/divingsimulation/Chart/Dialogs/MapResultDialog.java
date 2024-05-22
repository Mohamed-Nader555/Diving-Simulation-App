package com.ghada.divingsimulation.Chart.Dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ghada.divingsimulation.Common.APIService;
import com.ghada.divingsimulation.Common.WebServiceClient;
import com.ghada.divingsimulation.Models.Weather.WeatherResponse;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.ChooseImage;
import com.ghada.divingsimulation.Utils.CustomProgress;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapResultDialog extends DialogFragment {


    //Views
    private View view;
    private ImageView mDialogImage, mDialogClose;
    private TextView mDialogTimeZone, mDialogAddress, mDialogDes, mDialogTemp, mDialogSunRise, mDialogSunSet, mDialogHumidity, mDialogWindSpeed, mDialogClouds;

    //Variables
    private CustomProgress mCustomProgress = CustomProgress.getInstance();
    private double lat, lon;
    private String address;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.map_result_dialog_fragment, container, false);

        mCustomProgress.showProgress(getContext(), "Loading...", true);

        lat = getArguments().getDouble("selectedLat");
        lon = getArguments().getDouble("selectedLong");
        address = getArguments().getString("selectedAddress");

        initViews();

        return view;
    }

    private void initViews() {
        mDialogClose = view.findViewById(R.id.dialog_close);
        mDialogImage = view.findViewById(R.id.dialog_image);

        mDialogTimeZone = view.findViewById(R.id.dialog_time_zone);
        mDialogAddress = view.findViewById(R.id.dialog_address);
        mDialogDes = view.findViewById(R.id.dialog_des);
        mDialogTemp = view.findViewById(R.id.dialog_temp);
        mDialogSunRise = view.findViewById(R.id.dialog_sun_rise);
        mDialogSunSet = view.findViewById(R.id.dialog_sun_set);
        mDialogHumidity = view.findViewById(R.id.dialog_humidity);
        mDialogWindSpeed = view.findViewById(R.id.dialog_wind_speed);
        mDialogClouds = view.findViewById(R.id.dialog_clouds);

        mDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        requestOutput(lat, lon);

    }

    public void requestOutput(double deviceLat, double deviceLon) {


        Map<String, String> map = new HashMap<>();
        map.put("lat", String.valueOf(deviceLat));
        map.put("lon", String.valueOf(deviceLon));
        map.put("appid", getResources().getString(R.string.api_key));
        map.put("units", getResources().getString(R.string.units));


        APIService apiService = WebServiceClient.getRetrofit().create(APIService.class);
        Call<WeatherResponse> call = apiService.Weather(map);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                try {

                    WeatherResponse weatherResponse = response.body();
                    Log.e("Success", "onResponse: " + response.code());
                    Log.e("Success", "onResponse: " + response.toString());
                    Log.e("Success", "onResponse: " + weatherResponse.getCurrent().getTemp());
                    Log.e("Success", "onResponse: " + weatherResponse.getTimezone());

                    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
                    Date dateSunSet = new Date(Long.valueOf(weatherResponse.getCurrent().getSunset()) * 1000);
                    Date dateSunRise = new Date(Long.valueOf(weatherResponse.getCurrent().getSunrise()) * 1000);

                    mDialogImage.setImageResource(ChooseImage.getImageDrawable(weatherResponse.getCurrent().getWeather().get(0).getIcon()));

                    String timeZone = "Time Zone: " + weatherResponse.getTimezone();
                    String des = weatherResponse.getCurrent().getWeather().get(0).getDescription();
                    String temp = String.valueOf(Math.round(Float.parseFloat(weatherResponse.getCurrent().getTemp())));
                    String humidity = Math.round(Float.parseFloat(weatherResponse.getCurrent().getHumidity())) + getResources().getString(R.string.percentage_symbol);
                    String windSpeed = Math.round(Float.parseFloat(weatherResponse.getCurrent().getWindSpeed())) + getResources().getString(R.string.percentage_symbol);
                    String clouds = Math.round(Float.parseFloat(weatherResponse.getCurrent().getClouds())) + getResources().getString(R.string.percentage_symbol);

                    mDialogAddress.setText(address);
                    mDialogDes.setText(des);
                    mDialogTemp.setText(temp);
                    mDialogTimeZone.setText(timeZone);
                    mDialogHumidity.setText(humidity);
                    mDialogWindSpeed.setText(windSpeed);
                    mDialogClouds.setText(clouds);
                    mDialogSunSet.setText(dateFormat.format(dateSunSet));
                    mDialogSunRise.setText(dateFormat.format(dateSunRise));

                    mCustomProgress.hideProgress();

                } catch (Exception e) {
                    Log.e("Failed", "onResponse: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("Fail", "onFailure: " + t.getMessage());
            }

        });
    }


}
