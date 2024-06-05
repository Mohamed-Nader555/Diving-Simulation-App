package com.ghada.divingsimulation.Chart;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Chart.Adapters.DailyAdapter;
import com.ghada.divingsimulation.Chart.Adapters.HourlyAdapter;
import com.ghada.divingsimulation.Chart.Maps.MapsActivity;
import com.ghada.divingsimulation.Common.APIService;
import com.ghada.divingsimulation.Common.WebServiceClient;
import com.ghada.divingsimulation.Models.Weather.Daily;
import com.ghada.divingsimulation.Models.Weather.Hourly;
import com.ghada.divingsimulation.Models.Weather.WeatherResponse;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.ChooseImage;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.ghada.divingsimulation.Utils.DeviceLocation;
import com.ghada.divingsimulation.Utils.DeviceLocationCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChartFragment extends Fragment {

    public static final int LOCATION_PICK = 11;
    View view;
    //Views
    private RecyclerView mHourlyRecyclerView;
    private ImageView mWeatherImage, mWeatherPin, mWeatherDownload;
    private TextView mWeatherDes, mWeatherTemp, mWeatherHumidity, mWeatherWindSpeed, mWeatherClouds, mWeatherAddress;
    //Variables
    private LinearLayoutManager mLayoutManagerHourly;
    private HourlyAdapter mHourlyAdapter;
    private ArrayList<Hourly> mHourlyList;
    private CustomProgress mCustomProgress = CustomProgress.getInstance();
    private String downloadAddress;
    private String mPath;

    //Views
    private RecyclerView mDailyRecyclerView;

    //Variables
    private LinearLayoutManager mLayoutManagerDaily;
    private DailyAdapter mDailyAdapter;
    private ArrayList<Daily> mDailyList;

    public ChartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chart, container, false);

        Places.initialize(requireContext(), getResources().getString(R.string.google_maps_api_key));
        initViews(view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
            mPath = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/NewFile.csv";
        } else {
            mPath = Environment.getExternalStorageDirectory().toString() + "/NewFile.csv";
        }

        return view;
    }

    public void setDownloadAddress(String downloadAddress) {
        this.downloadAddress = downloadAddress;
    }

    private void initViews(View view) {

        mCustomProgress.showProgress(requireContext(), "Loading...", true);

        mWeatherImage = view.findViewById(R.id.weather_image);
        mWeatherDes = view.findViewById(R.id.weather_des);
        mWeatherTemp = view.findViewById(R.id.weather_temp);
        mWeatherHumidity = view.findViewById(R.id.weather_humidity);
        mWeatherWindSpeed = view.findViewById(R.id.weather_wind_speed);
        mWeatherClouds = view.findViewById(R.id.weather_clouds);
        mWeatherAddress = view.findViewById(R.id.weather_address);
        mWeatherDownload = view.findViewById(R.id.weather_temps_download);

        mWeatherPin = view.findViewById(R.id.weather_location_pin);
        mWeatherPin.setOnClickListener(v1 -> openMapFragment());
        mWeatherDownload.setOnClickListener(v3 -> downloadWeatherData());

        mHourlyList = new ArrayList<>();
        mHourlyRecyclerView = view.findViewById(R.id.hourly_recycler_view);
        mHourlyRecyclerView.setHasFixedSize(true);
        mLayoutManagerHourly = new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false);
        mHourlyRecyclerView.setLayoutManager(mLayoutManagerHourly);


        mDailyList = new ArrayList<>();
        mDailyRecyclerView = view.findViewById(R.id.daily_recycler_view);
        mDailyRecyclerView.setHasFixedSize(true);
        mLayoutManagerDaily = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        mDailyRecyclerView.setLayoutManager(mLayoutManagerDaily);


        DeviceLocation.getDeviceLocation(requireContext(), new DeviceLocationCallback() {
            @Override
            public void deviceLocationResponse(LatLng latLng, String address) {
                requestOutput(latLng.latitude, latLng.longitude, address);
                setDownloadAddress(address);
            }
        });
    }


    private void downloadWeatherData() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());


        final View customLayout = getLayoutInflater().inflate(R.layout.custom_confirm_download, null);
        Button createBtn = customLayout.findViewById(R.id.create_btn);
        Button appendBtn = customLayout.findViewById(R.id.append_btn);
        alertDialog.setView(customLayout);
        alertDialog.create();
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDataLineByLine(mPath, downloadAddress, mDailyList, false);

            }
        });
        appendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDataLineByLine(mPath, downloadAddress, mDailyList, true);

            }
        });
        alertDialog.show();
    }

    private void openMapFragment() {
        checkPermmisions();
    }

    @AfterPermissionGranted(1111)
    private void checkPermmisions() {

        String[] locationPermmsions = new String[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            locationPermmsions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(requireContext(), locationPermmsions)) {

            Intent resMapView = new Intent(requireContext(), MapsActivity.class);
            startActivityForResult(resMapView, LOCATION_PICK);

            //  startActivity(resMapView);
        } else {
            EasyPermissions.requestPermissions(this, "Location Access", 1111, locationPermmsions);
        }
    }

    public void requestOutput(double deviceLat, double deviceLon, String address) {

        Map<String, String> map = new HashMap<>();
        map.put("lat", String.valueOf(deviceLat));
        map.put("lon", String.valueOf(deviceLon));
        map.put("appid", getResources().getString(R.string.api_key));
        map.put("units", getResources().getString(R.string.units));

        APIService apiService = WebServiceClient.getWeatherRetrofit().create(APIService.class);
        Call<WeatherResponse> call = apiService.Weather(map);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                try {
                    Log.e("Success", "onResponse: " + response.code());
                    Log.e("Success", "onResponse: " + response.toString());
                    Log.e("Success", "onResponse: " + response.body().getCurrent().getTemp());
                    Log.e("Success", "onResponse: " + response.body().getTimezone());

                    mWeatherImage.setImageResource(ChooseImage.getImageDrawable(response.body().getCurrent().getWeather().get(0).getIcon()));

                    String des = response.body().getCurrent().getWeather().get(0).getDescription();
                    String temp = String.valueOf(Math.round(Float.parseFloat(response.body().getCurrent().getTemp())));
                    String humidity = Math.round(Float.parseFloat(response.body().getCurrent().getHumidity())) + getResources().getString(R.string.percentage_symbol);
                    String windSpeed = Math.round(Float.parseFloat(response.body().getCurrent().getWindSpeed())) + getResources().getString(R.string.percentage_symbol);
                    String clouds = Math.round(Float.parseFloat(response.body().getCurrent().getClouds())) + getResources().getString(R.string.percentage_symbol);

                    mWeatherAddress.setText(address);
                    mWeatherDes.setText(des);
                    mWeatherTemp.setText(temp);
                    mWeatherHumidity.setText(humidity);
                    mWeatherWindSpeed.setText(windSpeed);
                    mWeatherClouds.setText(clouds);

                    mHourlyList = response.body().getHourly();
                    mHourlyAdapter = new HourlyAdapter(requireContext(), mHourlyList);
                    mHourlyRecyclerView.setAdapter(mHourlyAdapter);

                    mDailyList = response.body().getDaily();
                    mDailyAdapter = new DailyAdapter(requireContext(), mDailyList, address);
                    mDailyRecyclerView.setAdapter(mDailyAdapter);
                    mCustomProgress.hideProgress();

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


    public void writeDataLineByLine(String filePath, String address, ArrayList<Daily> dailyArrayList, Boolean append) {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        DateFormat dateFormat = new SimpleDateFormat("EEE dd-MM-yyyy");

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, append);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);


            // adding header to csv
            String[] header = {"Address", "Date", "Temp", "Description", "Humidity", "Clouds", "Wind Speed", "Min Temp", "Min Temp"};
            writer.writeNext(header);

            List<String[]> data = new ArrayList<String[]>();
            for (Daily day : dailyArrayList) {
                Date date = new Date(Long.valueOf(day.getDt()) * 1000);
                data.add(new String[]{address,
                        dateFormat.format(date),
                        Math.round(Float.parseFloat(day.getTemp().getDay())) + getResources().getString(R.string.degree_symbol),
                        day.getWeather().get(0).getDescription(),
                        day.getHumidity() + getResources().getString(R.string.percentage_symbol),
                        day.getClouds() + getResources().getString(R.string.percentage_symbol),
                        Math.round(Float.parseFloat(day.getWindSpeed())) + getResources().getString(R.string.meter_per_second),
                        Math.round(Float.parseFloat(day.getTemp().getMin())) + getResources().getString(R.string.degree_symbol),
                        Math.round(Float.parseFloat(day.getTemp().getMax())) + getResources().getString(R.string.degree_symbol)
                });
            }


            writer.writeAll(data);
            writer.close();

            Log.e("Success", "writeDataLineByLine: CSV : Doone");
            Toast.makeText(requireContext(), "Data Downloaded Successfully", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("Failed", "writeDataLineByLine: CSV : " + e.getMessage());
        }
    }

}

