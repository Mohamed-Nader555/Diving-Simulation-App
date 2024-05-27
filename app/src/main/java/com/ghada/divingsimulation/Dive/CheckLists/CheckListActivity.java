package com.ghada.divingsimulation.Dive.CheckLists;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CheckListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CheckListAdapter adapter;
    FloatingActionButton resetButton;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        initView();
    }

    private void initView() {

        List<String> items = constructList();
        resetButton = findViewById(R.id.fab_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.reset();
            }
        });


        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.check_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CheckListAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    private List<String> constructList() {

        List<String> items = new ArrayList<>();

        items.add("Waterproof bandages (Band-Aids)");
        items.add("Ginger, Bonine, or Scopalamine Patches for seasickness");
        items.add("Moleskin");
        items.add("Elastic Wrap");
        items.add("Gauze Pads");
        items.add("Roll Of Gauze");
        items.add("Small bottle Of vinegar for marine Stings");
        items.add("Tweezers");
        items.add("Personal prescriptions like an asthma inhaler");
        items.add("Q-Tips");
        items.add("Cotton Balls");
        items.add("Antiseptic pads");
        items.add("Antibiotic ointment packets");
        items.add("Sunscreen");
        items.add("Mosquito Repellant Pads");
        items.add("Sinus spray");
        items.add("Pepto Bismol or Milk of Magnesia tablets");
        items.add("Anti-diarrheal");
        items.add("Cortisone cream for stings and bites");
        items.add("Benadryl for allergic reactions to marine stings");
        items.add("Mask");
        items.add("Shorty suit");
        items.add("Open-heel fins");
        items.add("First stage with primary and secondary pressure inflator hose");
        items.add("Air integrated weight belt");
        items.add("Mask defogger");
        items.add("ERDPML or RDP table");
        items.add("PADI (or PADI ecard)");
        items.add("Nitrox card");
        items.add("Spare, charged batteries for specialized dive gear");
        items.add("Dive gloves (on the boat for items, meats and destinations don't need to first)");
        items.add("Water bottle");
        items.add("Slate, pencil (either reusable)");
        items.add("Lycra socks for fit");
        items.add("Fin straps");
        items.add("Mask strap (or extra mask)");
        items.add("Regulator mouthpiece");
        items.add("Zip/cable ties");
        items.add("Nail clippers");
        items.add("O-rings");
        items.add("Duct tape");
        items.add("Batteries (including camera/flash batteries)");
        items.add("Silicone grease for o-rings");
        items.add("Multi-tool/adjustable wrench");
        items.add("Port plugs (both high pressure and low pressure)");
        items.add("Defog");
        items.add("Carabiners/bolt snaps");
        items.add("Dive insurance card");
        items.add("Spare PADI certification card (or PADI ecard)");
        items.add("Snorkel keeper");
        items.add("Dry suit zipper wax (if diving a drysuit)");
        items.add("Petty cash");
        items.add("Jar or small container to store all small, loose items in");
        items.add("Dry box or plastic container (to store everything in)");


        return items;

    }
}
