package com.ghada.divingsimulation.Dive;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ghada.divingsimulation.Dive.Accident.AccidentActivity;
import com.ghada.divingsimulation.Dive.Calculators.ModCalculatorActivity;
import com.ghada.divingsimulation.Dive.Certificate.CertificateActivity;
import com.ghada.divingsimulation.Dive.CheckLists.CheckListActivity;
import com.ghada.divingsimulation.Dive.DiveSites.DiveSitesActivity;
import com.ghada.divingsimulation.Dive.ERDPML.eRDPMLActivity;
import com.ghada.divingsimulation.Dive.LogBook.LogBookActivity;
import com.ghada.divingsimulation.Dive.Medical.AddMedicalActivity;
import com.ghada.divingsimulation.Dive.Medical.ShowMedicalActivity;
import com.ghada.divingsimulation.Dive.Simulation.SimulationActivity;
import com.ghada.divingsimulation.Models.User.Medical;
import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DiveFragment extends Fragment {

    View view;
    CardView logbook_card_view, mod_card_view, checklist_card_view, dive_sties_card_view, simulation_card_view, certificates_card_view, accidents_card_view, medical_card_view, erdpml_card_view, sos_card_view;

    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;

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

        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);


        logbook_card_view = view.findViewById(R.id.logbook_card_view);
        checklist_card_view = view.findViewById(R.id.checklist_card_view);
        dive_sties_card_view = view.findViewById(R.id.dive_sties_card_view);
        simulation_card_view = view.findViewById(R.id.simulation_card_view);
        certificates_card_view = view.findViewById(R.id.certificates_card_view);
        accidents_card_view = view.findViewById(R.id.accidents_card_view);
        medical_card_view = view.findViewById(R.id.medical_card_view);
        mod_card_view = view.findViewById(R.id.mod_card_view);
        erdpml_card_view = view.findViewById(R.id.erdpml_card_view);
        sos_card_view = view.findViewById(R.id.sos_card_view);


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


        certificates_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CertificateActivity.class);
                startActivity(intent);
            }
        });


        accidents_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccidentActivity.class);
                startActivity(intent);
            }
        });


        mod_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ModCalculatorActivity.class);
                startActivity(intent);
            }
        });

        erdpml_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), eRDPMLActivity.class);
                startActivity(intent);
            }
        });

        sos_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ModCalculatorActivity.class);
//                startActivity(intent);
            }
        });


        getData();


    }

    private void getData() {

        mUsersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    userData = snapshot.getValue(UserDataModel.class);
                    Medical medical = userData.getMedical();

                    if (medical == null || medical.getQuestion1().equals("")) {
                        medical_card_view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), AddMedicalActivity.class);
                                startActivity(intent);
                            }
                        });
                    } else {
                        medical_card_view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), ShowMedicalActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
