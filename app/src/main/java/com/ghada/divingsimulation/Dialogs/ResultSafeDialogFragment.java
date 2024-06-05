package com.ghada.divingsimulation.Dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import com.ghada.divingsimulation.Common.APIService;
import com.ghada.divingsimulation.Common.WebServiceClient;
import com.ghada.divingsimulation.Models.API.PredictionResponse;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CustomProgress;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResultSafeDialogFragment extends DialogFragment {

    View view;
    TextView mResponse;
    String result = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.result_safe_dialog_fragment, container, false);

        result = getArguments().getString("result");
        initViews();


        return view;
    }

    private void initViews() {
        mResponse = view.findViewById(R.id.response);
        mResponse.setText(result);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}