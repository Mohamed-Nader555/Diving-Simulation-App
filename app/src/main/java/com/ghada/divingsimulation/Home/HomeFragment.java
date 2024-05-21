package com.ghada.divingsimulation.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ghada.divingsimulation.R;


public class HomeFragment extends Fragment {


    View view;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews();

        return view;
    }


    private void initViews() {

    }


}