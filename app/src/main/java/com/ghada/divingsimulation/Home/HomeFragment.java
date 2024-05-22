package com.ghada.divingsimulation.Home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ghada.divingsimulation.Home.Adapters.DiveSitesAdapter;
import com.ghada.divingsimulation.Home.Adapters.TipsAdapter;
import com.ghada.divingsimulation.Models.Diving.DiveSite;
import com.ghada.divingsimulation.Models.Diving.Tip;
import com.ghada.divingsimulation.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imageDiveSite, imageDiveSiteBg;
    TextView textDiveSite;
    DiveSitesAdapter diveSiteAdapter;


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
        imageDiveSite = view.findViewById(R.id.imageDiveSite);
        imageDiveSiteBg = view.findViewById(R.id.imageDiveSiteBg);
        textDiveSite = view.findViewById(R.id.textDiveSite);
        tipsViewPager = view.findViewById(R.id.tips_view_pager);

        ArrayList<Tip> tips = Tip.createTips();
        TipsAdapter tipsAdapter = new TipsAdapter(getContext(),tips);
        tipsViewPager.setAdapter(tipsAdapter);


    }


    private void recyclerViewConfig() {
        ArrayList<DiveSite> diveSites = DiveSite.createDiveSites();
        diveSiteAdapter = new DiveSitesAdapter(getActivity(), diveSites);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(diveSiteAdapter);
    }

}