package com.ghada.divingsimulation.Home.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ghada.divingsimulation.Models.Diving.Tip;
import com.ghada.divingsimulation.R;

import java.util.ArrayList;
import java.util.Random;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipViewHolder> {
    private final Context context;
    private ArrayList<Tip> tipsList;


    public TipsAdapter(Context context, ArrayList<Tip> tipsList) {
        this.context = context;
        this.tipsList = tipsList;
    }

    @NonNull
    @Override
    public TipsAdapter.TipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_tip, parent, false);
        TipsAdapter.TipViewHolder viewHolder = new TipsAdapter.TipViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TipViewHolder holder, int position) {
        Tip tip = tipsList.get(position);

        String imageName = "diving_site_";
        Random random = new Random();
        int randomNumber = random.nextInt(55) + 1;
        imageName = imageName + String.valueOf(randomNumber);

        AlertDialog.Builder descDialog;
        descDialog = new AlertDialog.Builder(context)
                .setTitle(tip.getTipName())
                .setMessage(tip.getTipDesc());

        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        Glide.with(context).load(resourceId)
                .apply(new RequestOptions().override(200, 200))
                .into(holder.imageDiveSiteBg);

        holder.textDiveSite.setText(tip.getTipDesc());

        holder.cardDiveSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descDialog.setPositiveButton("CLOSE", (dialog, which) -> dialog.dismiss());
                descDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }


    class TipViewHolder extends RecyclerView.ViewHolder {

        CardView cardDiveSite;
        ImageView imageDiveSiteBg, imageDiveSite;
        TextView textDiveSite;

        public TipViewHolder(@NonNull View view) {
            super(view);
            cardDiveSite = view.findViewById(R.id.cardDiveSite);
            imageDiveSiteBg = view.findViewById(R.id.imageDiveSiteBg);
            imageDiveSite = view.findViewById(R.id.imageDiveSite);
            textDiveSite = view.findViewById(R.id.textDiveSite);

        }
    }


}



