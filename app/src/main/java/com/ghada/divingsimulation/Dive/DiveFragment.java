package com.ghada.divingsimulation.Dive;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ghada.divingsimulation.Dive.CheckLists.CheckListActivity;
import com.ghada.divingsimulation.Dive.DiveSites.DiveSitesActivity;
import com.ghada.divingsimulation.Dive.LogBook.LogBookActivity;
import com.ghada.divingsimulation.Dive.Simulation.SimulationActivity;
import com.ghada.divingsimulation.R;


public class DiveFragment extends Fragment {

    View view;
    CardView logbook_card_view, checklist_card_view, dive_sties_card_view, simulation_card_view;

    public DiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dive, container, false);

        initViews();
        return view;
    }

    private void initViews() {
        logbook_card_view = view.findViewById(R.id.logbook_card_view);
        checklist_card_view = view.findViewById(R.id.checklist_card_view);
        dive_sties_card_view = view.findViewById(R.id.dive_sties_card_view);
        simulation_card_view = view.findViewById(R.id.simulation_card_view);

        logbook_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogBookActivity.class);
                startActivity(intent);
            }
        });


        checklist_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckListActivity.class);
                startActivity(intent);
            }
        });


        dive_sties_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiveSitesActivity.class);
                startActivity(intent);
            }
        });


        simulation_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SimulationActivity.class);
                startActivity(intent);
            }
        });
    }


}