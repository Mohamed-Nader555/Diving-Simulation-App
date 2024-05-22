package com.ghada.divingsimulation.Chart.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ghada.divingsimulation.Models.Weather.Daily;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.ChooseImage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyResultDialog extends DialogFragment {

    //Views
    private View view;
    private ImageView mDialogImage, mDialogClose;
    private TextView mDialogAddress, mDialogDate, mDialogDes, mDialogTemp,
            mDialogMinTemp, mDialogMaxTemp, mDialogHumidity,
            mDialogWindSpeed, mDialogClouds, mDialogMornTemp, mDialogEveTemp;

    //Variables
    private Daily dailyItem;
    private String address;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.daily_result_dialog_fragment, container, false);

        dailyItem = (Daily) getArguments().getSerializable("result");
        address = getArguments().getString("address");

        initViews();

        return view;
    }

    private void initViews() {
        mDialogClose = view.findViewById(R.id.dialog_close);
        mDialogImage = view.findViewById(R.id.dialog_image);

        mDialogAddress = view.findViewById(R.id.dialog_address);
        mDialogDate = view.findViewById(R.id.dialog_date);
        mDialogDes = view.findViewById(R.id.dialog_des);
        mDialogTemp = view.findViewById(R.id.dialog_temp);
        mDialogMinTemp = view.findViewById(R.id.dialog_min_temp);
        mDialogMaxTemp = view.findViewById(R.id.dialog_max_temp);
        mDialogHumidity = view.findViewById(R.id.dialog_humidity);
        mDialogWindSpeed = view.findViewById(R.id.dialog_wind_speed);
        mDialogClouds = view.findViewById(R.id.dialog_clouds);
        mDialogMornTemp = view.findViewById(R.id.dialog_morning);
        mDialogEveTemp = view.findViewById(R.id.dialog_evening);

        mDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        fillData();
    }

    private void fillData() {
        DateFormat dateFormat = new SimpleDateFormat("EEE dd-MM-yyyy");
        Date date = new Date(Long.valueOf(dailyItem.getDt()) * 1000);

        String des = dailyItem.getWeather().get(0).getDescription();
        String temp = Math.round(Float.parseFloat(dailyItem.getTemp().getDay())) + getContext().getResources().getString(R.string.degree_symbol);
        String minTemp = Math.round(Float.parseFloat(dailyItem.getTemp().getMin())) + getContext().getResources().getString(R.string.degree_symbol);
        String maxTemp = Math.round(Float.parseFloat(dailyItem.getTemp().getMax())) + getContext().getResources().getString(R.string.degree_symbol);
        String humidity = dailyItem.getHumidity() + getContext().getResources().getString(R.string.percentage_symbol);
        String windSpeed = Math.round(Float.parseFloat(dailyItem.getWindSpeed())) + getContext().getResources().getString(R.string.meter_per_second);
        String clouds = dailyItem.getClouds() + getContext().getResources().getString(R.string.percentage_symbol);
        String mornTemp = Math.round(Float.parseFloat(dailyItem.getTemp().getEve())) + getContext().getResources().getString(R.string.degree_symbol);
        String eveTemp = Math.round(Float.parseFloat(dailyItem.getTemp().getMorn())) + getContext().getResources().getString(R.string.degree_symbol);

        mDialogImage.setImageResource(ChooseImage.getImageDrawable(dailyItem.getWeather().get(0).getIcon()));
        mDialogAddress.setText(address);
        mDialogDate.setText(dateFormat.format(date));
        mDialogDes.setText(des);
        mDialogTemp.setText(temp);
        mDialogMinTemp.setText(minTemp);
        mDialogMaxTemp.setText(maxTemp);
        mDialogHumidity.setText(humidity);
        mDialogWindSpeed.setText(windSpeed);
        mDialogClouds.setText(clouds);
        mDialogMornTemp.setText(mornTemp);
        mDialogEveTemp.setText(eveTemp);
    }


}