package com.ghada.divingsimulation.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.ghada.divingsimulation.R;

import java.util.ArrayList;


public class AddCertDialogFragment extends DialogFragment {


    private View view;
    private ImageView mDialogImage, mDialogClose;
    private EditText mCertDateEt, mCertNumberEt, mCertLevelEt, mCertOrgEt;
    private Spinner mCertTypeSpinner;
    private Button mAddCertBtn;

    public AddCertDialogFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_cert_dialog, container, false);

        initViews();

        return view;
    }

    private void initViews() {
        mDialogImage = view.findViewById(R.id.dialog_image);
        mDialogClose = view.findViewById(R.id.dialog_close);
        mCertDateEt = view.findViewById(R.id.cert_date_et);
        mCertLevelEt = view.findViewById(R.id.cert_level_et);
        mCertNumberEt = view.findViewById(R.id.cert_number_et);
        mCertOrgEt = view.findViewById(R.id.cert_org_et);

        mCertTypeSpinner = view.findViewById(R.id.cert_type_spinner);
        mAddCertBtn = view.findViewById(R.id.add_cert_dialog_btn);


        ArrayList<String> types = new ArrayList<>();
        types.add("Select Cert Type...");
        types.add("Open Water");
        types.add("Advance Open Water");
        types.add("EFR");
        types.add("Rescue");
        types.add("Nitrox");
        types.add("Deep Diver");
        types.add("Dive Master");
        types.add("Open Water Scuba Instructor");
        types.add("Boat Diver");
        types.add("Cave Diver");
        types.add("Underwater Photography");
        types.add("Side Mount");

        initPlacesPricesSpinner(types);

        mDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        mAddCertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Done Getting Data", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void initPlacesPricesSpinner(final ArrayList<String> data) {
        ArrayAdapter typesAdapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_item, data);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCertTypeSpinner.setAdapter(typesAdapter);
        mCertTypeSpinner.setSelection(0);

        mCertTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(getContext(), "Please Select the Certification Type!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}