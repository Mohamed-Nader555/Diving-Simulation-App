package com.ghada.divingsimulation.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ghada.divingsimulation.Dive.DiveSites.DiveSitesActivity;
import com.ghada.divingsimulation.Home.Adapters.DiveSitesAdapter;
import com.ghada.divingsimulation.Home.Adapters.TipsAdapter;
import com.ghada.divingsimulation.Models.Diving.DiveSite;
import com.ghada.divingsimulation.Models.Diving.Tip;
import com.ghada.divingsimulation.R;

import java.util.ArrayList;
import java.util.Collections;


public class HomeFragment extends Fragment {


    View view;
    RecyclerView recyclerView;
    DiveSitesAdapter diveSiteAdapter;
    CardView card_view_all_dive_sites;
    private ViewPager2 tipsViewPager;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews();
        recyclerViewConfig();

        return view;
    }


    private void initViews() {

        recyclerView = view.findViewById(R.id.recyclerView);
        tipsViewPager = view.findViewById(R.id.tips_view_pager);
        card_view_all_dive_sites = view.findViewById(R.id.card_view_all_dive_sites);

        card_view_all_dive_sites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiveSitesActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Tip> tips = Tip.createTips();
        TipsAdapter tipsAdapter = new TipsAdapter(getContext(), tips);
        tipsViewPager.setAdapter(tipsAdapter);
    }


    private void recyclerViewConfig() {
        ArrayList<DiveSite> diveSites = DiveSite.createDiveSites();


        Collections.shuffle(diveSites);
        ArrayList<DiveSite> randomDiveSites = new ArrayList<>();
        for (int i = 0; i < Math.min(6, diveSites.size()); i++) {
            randomDiveSites.add(diveSites.get(i));
        }

        diveSiteAdapter = new DiveSitesAdapter(getActivity(), randomDiveSites);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(diveSiteAdapter);
    }

}