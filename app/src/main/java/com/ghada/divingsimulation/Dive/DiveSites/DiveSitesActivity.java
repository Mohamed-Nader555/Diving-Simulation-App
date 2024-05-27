package com.ghada.divingsimulation.Dive.DiveSites;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Home.Adapters.DiveSitesAdapter;
import com.ghada.divingsimulation.Models.Diving.DiveSite;
import com.ghada.divingsimulation.R;

import java.util.ArrayList;

public class DiveSitesActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    DiveSitesAdapter diveSiteAdapter;
    ImageView back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dive_sites);


        initViews();


    }

    private void initViews() {

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<DiveSite> diveSites = DiveSite.createDiveSites();
        diveSiteAdapter = new DiveSitesAdapter(DiveSitesActivity.this, diveSites);
        recyclerView.setLayoutManager(new GridLayoutManager(DiveSitesActivity.this, 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(diveSiteAdapter);


    }
}