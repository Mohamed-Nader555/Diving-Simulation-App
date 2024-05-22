package com.ghada.divingsimulation.Home.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ghada.divingsimulation.Home.Dialogs.DiveSiteResultDialog;
import com.ghada.divingsimulation.Models.Diving.DiveSite;
import com.ghada.divingsimulation.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiveSitesAdapter extends RecyclerView.Adapter<DiveSitesAdapter.ViewHolder> {

    private static final String TAG = "DiveSites_Adapter";
    private List<DiveSite> diveSites = new ArrayList<>();
    private Context context;
    private Bundle bundle = new Bundle();


    public DiveSitesAdapter(Context context, List<DiveSite> meals) {
        this.diveSites = meals;
        this.context = context;
    }


    @NonNull
    @Override
    public DiveSitesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycler_dive_site, recyclerView, false);
        DiveSitesAdapter.ViewHolder viewHolder = new DiveSitesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiveSitesAdapter.ViewHolder holder, int position) {
        DiveSite diveSite = diveSites.get(position);
        DiveSiteResultDialog diveSiteResultDialog = new DiveSiteResultDialog();

        holder.diveSiteNameTxtView.setText(diveSite.getName());

        int resourceId = context.getResources().getIdentifier(diveSite.getImg(), "drawable", context.getPackageName());
        Glide.with(context).load(resourceId)
                .apply(new RequestOptions().override(200, 200))
                .into(holder.diveSiteImg);

        holder.diveSiteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putSerializable("result", (Serializable) diveSite);
                diveSiteResultDialog.setArguments(bundle);
                diveSiteResultDialog.show(((FragmentActivity) context).getSupportFragmentManager(), "DiveSiteResultDialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return diveSites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView diveSiteImg;
        TextView diveSiteNameTxtView;
        CardView diveSiteCardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            diveSiteImg = view.findViewById(R.id.dive_site_thumb);
            diveSiteNameTxtView = view.findViewById(R.id.dive_site_name);
            diveSiteCardView = view.findViewById(R.id.dive_site_card_view);
        }

    }
}
