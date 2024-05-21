package com.ghada.divingsimulation.Chart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghada.divingsimulation.R;


public class ChartFragment extends Fragment {

    View view;

    public ChartFragment() {
     }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chart, container, false);

        initViews();

        return view;
    }

    private void initViews() {

    }

}