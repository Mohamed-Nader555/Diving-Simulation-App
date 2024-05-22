package com.ghada.divingsimulation.Chart.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Chart.Dialogs.DailyResultDialog;
import com.ghada.divingsimulation.Models.Weather.Daily;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.ChooseImage;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.myDailyViewHolder> {


    private Context context;
    private ArrayList<Daily> dailyList;
    private Bundle bundle = new Bundle();
    private String address;

    public DailyAdapter(Context context, ArrayList<Daily> dailyList, String address) {
        this.context = context;
        this.dailyList = dailyList;
        this.address = address;
    }

    @NonNull
    @Override
    public DailyAdapter.myDailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily, parent, false);

        DailyAdapter.myDailyViewHolder viewHolder = new DailyAdapter.myDailyViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyAdapter.myDailyViewHolder holder, int position) {

        Daily daily = dailyList.get(position);
        DailyResultDialog dailyResultDialog = new DailyResultDialog();

        holder.dailyItemImage.setImageResource(ChooseImage.getImageDrawable(daily.getWeather().get(0).getIcon()));
        holder.dailyItemTemp.setText(String.valueOf(Math.round(Float.parseFloat(daily.getTemp().getDay()))));

        DateFormat dateFormat = new SimpleDateFormat("EEE");
        Date date = new Date(Long.valueOf(daily.getDt()) * 1000);
        holder.dailyItemDay.setText(dateFormat.format(date));


        holder.dailyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putSerializable("result", (Serializable) daily);
                bundle.putString("address", address);
                dailyResultDialog.setArguments(bundle);
                dailyResultDialog.show(((FragmentActivity) context).getSupportFragmentManager(), "DailyResultDialog");
            }
        });

    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }


    public class myDailyViewHolder extends RecyclerView.ViewHolder {

        ImageView dailyItemImage;
        TextView dailyItemDay, dailyItemTemp, dailyItemDes;
        RelativeLayout dailyItem;

        public myDailyViewHolder(@NonNull View itemView) {
            super(itemView);


            dailyItem = itemView.findViewById(R.id.daily_item);
            dailyItemImage = itemView.findViewById(R.id.daily_image);
            dailyItemDay = itemView.findViewById(R.id.daily_day);
            dailyItemTemp = itemView.findViewById(R.id.daily_temp);
            dailyItemDes = itemView.findViewById(R.id.daily_des);


        }
    }


}
