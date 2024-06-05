package com.ghada.divingsimulation.Dive.Simulation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Common.APIService;
import com.ghada.divingsimulation.Common.WebServiceClient;
import com.ghada.divingsimulation.Dialogs.ResultNotSafeDialogFragment;
import com.ghada.divingsimulation.Dialogs.ResultSafeDialogFragment;
import com.ghada.divingsimulation.Models.API.PredictionResponse;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimulationActivity extends AppCompatActivity {

    private static final String TAG = "SimulationActivity";

    private TextInputEditText maxDepthEditText, bottomTimeEditText, o2EditText, ppo2EditText, surfaceIntervalEditText;
    private TextInputLayout maxDepthLayout, bottomTimeLayout, o2Layout, ppo2Layout, surfaceIntervalLayout;
    private Spinner diveNumberSpinner;

    CustomProgress mCustomProgress = CustomProgress.getInstance();
    String Output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);


        initViews();

    }

    private void initViews() {

        // Initialize UI components
        maxDepthEditText = findViewById(R.id.max_depth_et);
        bottomTimeEditText = findViewById(R.id.bottom_time_et);
        o2EditText = findViewById(R.id.o2_et);
        ppo2EditText = findViewById(R.id.ppo2_et);
        surfaceIntervalEditText = findViewById(R.id.surface_interval_et);

        maxDepthLayout = findViewById(R.id.max_depth_tvl);
        bottomTimeLayout = findViewById(R.id.bottom_time_tvl);
        o2Layout = findViewById(R.id.o2_tvl);
        ppo2Layout = findViewById(R.id.ppo2_tvl);
        surfaceIntervalLayout = findViewById(R.id.surface_interval_tvl);

        diveNumberSpinner = findViewById(R.id.dive_number_spinner);
        initDiveNumberSpinner();


        handelTextFields();

        findViewById(R.id.check_safety_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSafety();
            }
        });

        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void initDiveNumberSpinner() {

        ArrayList<String> types = new ArrayList<>();
        types.add("Select Dive Number...");
        types.add("1st Dive");
        types.add("2nd Dive");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diveNumberSpinner.setAdapter(adapter);
        diveNumberSpinner.setSelection(0);
    }


    private void checkSafety() {
        if (!validateInputs()) {
            return;
        }

        String maxDepth = maxDepthEditText.getText().toString().trim();
        String bottomTime = bottomTimeEditText.getText().toString().trim();
        String o2 = o2EditText.getText().toString().trim();
        String ppo2 = ppo2EditText.getText().toString().trim();
        String surfaceInterval = surfaceIntervalEditText.getText().toString().trim();
        String diveNumber = diveNumberSpinner.getSelectedItem().toString();
        String diveNumberNumber = diveNumber.equals("1st Dive") ? "1" : "2";


        ResultSafeDialogFragment resultSafeDialog = new ResultSafeDialogFragment();
        ResultNotSafeDialogFragment resultNotSafeDialog = new ResultNotSafeDialogFragment();
        mCustomProgress.showProgress(this, "Please Wait..", false);


        Map<String, String> map = new HashMap<>();


        map.put("max_depth", maxDepth);
        map.put("bottom_time", bottomTime);
        map.put("O2", o2);
        map.put("PPO2", ppo2);
        map.put("surface_interval", surfaceInterval);
        map.put("dive_number", diveNumberNumber);

        Log.e(TAG, "checkSafety: " + map );

        APIService apiService = WebServiceClient.getAIRetrofit().create(APIService.class);
        Call<PredictionResponse> call = apiService.Prediction_AI(map);

        call.enqueue(new Callback<PredictionResponse>() {
            @Override
            public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
                try {
                    Log.e("Success", "onResponse: " + response.code());
                    Log.e("Success", "onResponse: " + response.toString());
                    Log.e("Success", "onResponse: " + response.body().getOutput());
                    Output = response.body().getOutput();

                    if(Output.equals("The dive is safe.")){
                        mCustomProgress.hideProgress();
                        Bundle bundle = new Bundle();
                        bundle.putString("result", Output);
                        resultSafeDialog.setArguments(bundle);
                        resultSafeDialog.show(getSupportFragmentManager(), "ResultSafeDialog");
                    }else if(Output.equals("The dive is not safe.")){
                        mCustomProgress.hideProgress();
                        Bundle bundle = new Bundle();
                        bundle.putString("result", Output);
                        bundle.putString("maxDepth", maxDepth);
                        bundle.putString("bottomTime", bottomTime);
                        bundle.putString("o2", o2);
                        bundle.putString("ppo2", ppo2);
                        bundle.putString("surfaceInterval", surfaceInterval);
                        bundle.putString("diveNumber", diveNumberNumber);
                        resultNotSafeDialog.setArguments(bundle);
                        resultNotSafeDialog.show(getSupportFragmentManager(), "ResultNotSafeDialog");
                    }
                    Toast.makeText(SimulationActivity.this, Output, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    mCustomProgress.hideProgress();
                    Output = "There is no Result";
                    Bundle bundle = new Bundle();
                    bundle.putString("result", "There is no Result");
                    resultSafeDialog.setArguments(bundle);
                    resultSafeDialog.show(getSupportFragmentManager(), "ResultSafeDialog");
                    Log.e(TAG, "ErrorOnResponse: " + Output );
                    Toast.makeText(SimulationActivity.this, Output, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PredictionResponse> call, Throwable t) {
                Log.e("Fail", "onFailure: " + t.getMessage());
                mCustomProgress.hideProgress();
                Toast.makeText(SimulationActivity.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("result", Output);
                resultSafeDialog.setArguments(bundle);
                resultSafeDialog.show(getSupportFragmentManager(), "ResultSafeDialog");
            }

        });


    }

    private boolean validateInputs() {
        boolean valid = true;

        String maxDepth = maxDepthEditText.getText().toString().trim();
        if (maxDepth.isEmpty()) {
            maxDepthLayout.setError("Please enter the max depth.");
            valid = false;
        } else if (!isNumeric(maxDepth)) {
            maxDepthLayout.setError("Max depth must be a number.");
            valid = false;
        } else {
            maxDepthLayout.setError(null);
        }

        String bottomTime = bottomTimeEditText.getText().toString().trim();
        if (bottomTime.isEmpty()) {
            bottomTimeLayout.setError("Please enter the bottom time.");
            valid = false;
        } else if (!isNumeric(bottomTime)) {
            bottomTimeLayout.setError("Bottom time must be a number.");
            valid = false;
        } else {
            bottomTimeLayout.setError(null);
        }

        String o2 = o2EditText.getText().toString().trim();
        if (o2.isEmpty()) {
            o2Layout.setError("Please enter the O2.");
            valid = false;
        } else if (!isNumeric(o2)) {
            o2Layout.setError("O2 must be a number.");
            valid = false;
        } else {
            o2Layout.setError(null);
        }

        String surfaceInterval = surfaceIntervalEditText.getText().toString().trim();
        if (surfaceInterval.isEmpty()) {
            surfaceIntervalLayout.setError("Please enter the surface interval.");
            valid = false;
        } else if (!isValidTimeFormat(surfaceInterval)) {
            surfaceIntervalLayout.setError("Surface interval must be in HH:MM format.");
            valid = false;
        } else {
            surfaceIntervalLayout.setError(null);
        }

        if (diveNumberSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select whether the victim was found on the surface", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidTimeFormat(String time) {
        String timePattern = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(timePattern);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    private void handelTextFields() {
        final int minDepth = 1;
        final int maxDepth = 52;
        final double minO2 = 21;
        final double maxO2 = 36;

        maxDepthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (!input.isEmpty()) {
                    try {
                        double value = Integer.parseInt(input);
                        if (value < minDepth || value > maxDepth) {
                            maxDepthLayout.setError("Depth must be between " + minDepth + " and " + maxDepth);
                        } else {
                            maxDepthLayout.setError(null);
                        }
                    } catch (NumberFormatException e) {
                        maxDepthLayout.setError("Invalid number");
                    }
                } else {
                    maxDepthLayout.setError(null);
                }
                updatePPO2();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        o2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (!input.isEmpty()) {
                    try {
                        double value = Double.parseDouble(input);
                        if (value < minO2 || value > maxO2) {
                            o2Layout.setError("O2 must be between " + minO2 + " and " + maxO2);
                        } else {
                            o2Layout.setError(null);
                        }
                    } catch (NumberFormatException e) {
                        o2Layout.setError("Invalid number");
                    }
                } else {
                    o2Layout.setError(null);
                }
                updatePPO2();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void updatePPO2() {
        double depth = 0, o2 = 0;
        double ppo2 = 0;


        if (!maxDepthEditText.getText().toString().isEmpty()) {
            depth = Integer.parseInt(maxDepthEditText.getText().toString());
        } else {
            return;
        }
        if (!o2EditText.getText().toString().isEmpty()) {
            o2 = Double.parseDouble(o2EditText.getText().toString());
        } else {
            return;
        }

        if (depth > 0 && o2 > 0) {
            ppo2 = (o2 / 100) * ((depth / 10) + 1);
        } else {
            return;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        String roundedMOD = decimalFormat.format(ppo2);


        ppo2EditText.setText(roundedMOD);
    }


}