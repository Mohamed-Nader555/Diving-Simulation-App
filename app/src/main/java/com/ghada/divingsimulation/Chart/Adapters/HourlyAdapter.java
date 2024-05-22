package com.ghada.divingsimulation.Chart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Models.Weather.Hourly;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.ChooseImage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.myHourlyViewHolder> {

    protected ArrayList<Hourly> hourlyList;
    private Context context;


    public HourlyAdapter(Context context, ArrayList<Hourly> hourlyList) {
        this.context = context;
        this.hourlyList = hourlyList;
    }

    @NonNull
    @Override
    public myHourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hourly, parent, false);

        myHourlyViewHolder viewHolder = new myHourlyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myHourlyViewHolder holder, int position) {
        Hourly hourly = hourlyList.get(position);

        holder.hourlyItemImage.setImageResource(ChooseImage.getImageDrawable(hourly.getWeather().get(0).getIcon()));
        holder.hourlyItemWeatherTemp.setText(String.valueOf(Math.round(Float.parseFloat(hourly.getTemp()))));

        DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        Date date = new Date(Long.valueOf(hourly.getDt()) * 1000);
        holder.hourlyItemTime.setText(dateFormat.format(date));

    }

    @Override
    public int getItemCount() {
        return 24;
    }


    public class myHourlyViewHolder extends RecyclerView.ViewHolder {

        ImageView hourlyItemImage;
        TextView hourlyItemTime, hourlyItemWeatherTemp;


        public myHourlyViewHolder(@NonNull View itemView) {
            super(itemView);

            hourlyItemImage = itemView.findViewById(R.id.hourly_image);
            hourlyItemTime = itemView.findViewById(R.id.hourly_time);
            hourlyItemWeatherTemp = itemView.findViewById(R.id.hourly_weather_temp);


        }
    }


}
