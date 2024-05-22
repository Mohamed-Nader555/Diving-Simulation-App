package com.ghada.divingsimulation.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.ghada.divingsimulation.R;

public class MedicalDialogFragment extends DialogFragment {


    View view;
    Button mSaveMedicalBtn;
    private ImageView mDialogImage, mDialogClose;


    public MedicalDialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_medical_dialog, container, false);

        initViews();

        return view;
    }


    private void initViews() {

        mSaveMedicalBtn = view.findViewById(R.id.medical_info_save_btn);
        mDialogImage = view.findViewById(R.id.dialog_image);
        mDialogClose = view.findViewById(R.id.dialog_close);


        mDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        mSaveMedicalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Done Saving Data", Toast.LENGTH_SHORT).show();
            }
        });

    }

}