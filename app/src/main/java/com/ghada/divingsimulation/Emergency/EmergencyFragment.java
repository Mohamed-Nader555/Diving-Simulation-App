package com.ghada.divingsimulation.Emergency;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Dive.Accident.AccidentActivity;
import com.ghada.divingsimulation.Emergency.Adapters.InstructionsAdapter;
import com.ghada.divingsimulation.Emergency.Maps.HospitalsMapActivity;
import com.ghada.divingsimulation.Models.Diving.Instructions;
import com.ghada.divingsimulation.R;

import java.util.ArrayList;


public class EmergencyFragment extends Fragment {

    View view;
    CardView card_ambulance, card_open_map, card_fill_accident;
    TextView dan_emergency_hotline, dan_more_info;
    RecyclerView recyclerView;
    String number = "";


    public EmergencyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_emergency, container, false);

        initViews();

        return view;
    }

    private void initViews() {


        card_ambulance = view.findViewById(R.id.card_ambulance);
        card_open_map = view.findViewById(R.id.card_open_map);
        card_fill_accident = view.findViewById(R.id.card_fill_accident);
        dan_emergency_hotline = view.findViewById(R.id.dan_emergency_hotline);
        dan_more_info = view.findViewById(R.id.dan_more_info);
        recyclerView = view.findViewById(R.id.instructions_recyclerView);

        ArrayList<Instructions> instructions = Instructions.createInstructions();
        InstructionsAdapter instructionsAdapter = new InstructionsAdapter(getContext(), instructions);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(instructionsAdapter);

        card_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = "123";
                makeEmergencyCall(number);
            }
        });

        card_open_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), HospitalsMapActivity.class));
            }
        });


        card_fill_accident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), AccidentActivity.class));
            }
        });

        dan_emergency_hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = "+39-06-4211-5685";
                makeEmergencyCall(number);
            }
        });

        dan_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder descDialog;
                descDialog = new AlertDialog.Builder(requireContext())
                        .setTitle(requireContext().getString(R.string.dan_name))
                        .setMessage(requireContext().getString(R.string.dan_expectaion));
                descDialog.setPositiveButton("CLOSE", (dialog, which) -> dialog.dismiss());
                descDialog.show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeEmergencyCall(number);
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(requireContext(), "Permission to make calls denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void makeEmergencyCall(String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));

        // Check if permission is granted before making the call
        if (requireContext().checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        } else {
            // Request permission if not granted
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1);
        }
    }

}
