package com.ghada.divingsimulation.Dive.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Dive.Accident.AccidentDetailsActivity;
import com.ghada.divingsimulation.Dive.Certificate.CertificatesDetailsActivity;
import com.ghada.divingsimulation.Dive.LogBook.LogBookDetailsActivity;
import com.ghada.divingsimulation.Models.User.Accident;
import com.ghada.divingsimulation.Models.User.Certificates;
import com.ghada.divingsimulation.Models.User.LogBook;
import com.ghada.divingsimulation.R;

import java.util.List;

public class GenericItemsAdapter<T> extends RecyclerView.Adapter<GenericItemsAdapter.ViewHolder> {
    private final List<T> itemsList;
    Context context;
    String type;

    public GenericItemsAdapter(Context context, List<T> itemsList, String type) {
        this.context = context;
        this.itemsList = itemsList;
        this.type = type;
    }

    @NonNull
    @Override
    public GenericItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_data, parent, false);
        GenericItemsAdapter.ViewHolder holder = new GenericItemsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericItemsAdapter.ViewHolder holder, int position) {
        T item = itemsList.get(position);
        final int itemPosition = position;

        if (type.equals("LogBookType")) {
            LogBook logBook = (LogBook) item;
            holder.itemImage.setImageResource(R.drawable.dive_logbook);
            holder.titleTextView.setText(logBook.getLocation());
            String subTitle = logBook.getDiveSite() + ", BT:" + logBook.getBottomTime() + ", MD:" + logBook.getMaxDepth();
            holder.subTitleTextView.setText(subTitle);
            holder.dateTextView.setText(logBook.getDate());

            holder.itemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogBook log = (LogBook) itemsList.get(itemPosition);
                    Intent itemDetails = new Intent(context, LogBookDetailsActivity.class);
                    itemDetails.putExtra("item", log);
                    context.startActivity(itemDetails);
                }
            });
        } else if (type.equals("CertificatesType")) {
            Certificates cert = (Certificates) item;
            holder.itemImage.setImageResource(R.drawable.cert_logo);
            holder.titleTextView.setText(cert.getCetType());
            String subTitle = cert.getCertOrg() + ", Level:" + cert.getCertLevel();
            holder.subTitleTextView.setText(subTitle);
            holder.dateTextView.setText(cert.getCertDate());

            holder.itemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Certificates cert = (Certificates) itemsList.get(itemPosition);
                    Intent itemDetails = new Intent(context, CertificatesDetailsActivity.class);
                    itemDetails.putExtra("item", cert);
                    context.startActivity(itemDetails);
                }
            });

        } else if (type.equals("AccidentsType")) {
            Accident accident = (Accident) item;
            holder.itemImage.setImageResource(R.drawable.cert_logo);
            holder.titleTextView.setText(accident.getVicName());
            String subTitle = accident.getRescuerName() + ", " + accident.getCity();
            holder.subTitleTextView.setText(subTitle);
            holder.dateTextView.setText(accident.getDate());

            holder.itemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Accident accident = (Accident) itemsList.get(itemPosition);
                    Intent itemDetails = new Intent(context, AccidentDetailsActivity.class);
                    itemDetails.putExtra("item", accident);
                    context.startActivity(itemDetails);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, subTitleTextView, dateTextView;
        CardView itemCardView;
        ImageView itemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_photo_img);
            itemCardView = itemView.findViewById(R.id.item_card_view);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            dateTextView = itemView.findViewById(R.id.item_date);
            subTitleTextView = itemView.findViewById(R.id.sub_title_text_view);

        }
    }
}
