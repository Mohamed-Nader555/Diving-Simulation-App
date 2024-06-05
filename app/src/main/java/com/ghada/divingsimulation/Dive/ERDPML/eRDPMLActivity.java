package com.ghada.divingsimulation.Dive.ERDPML;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ghada.divingsimulation.Dive.Accident.AccidentActivity;
import com.ghada.divingsimulation.Dive.Accident.AddAccidentActivity;
import com.ghada.divingsimulation.Models.eRDPML.Dive;
import com.ghada.divingsimulation.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class eRDPMLActivity extends AppCompatActivity {

    private TextView responseText;
    private CardView checkSafetyBtn;

    private TextInputLayout maxDepthLayout, bottomTimeLayout, previousPgLayout, surfaceIntervalLayout;
    private TextInputEditText maxDepthEditText, bottomTimeEditText, previousPgEditText, surfaceIntervalEditText;

    private RadioGroup firstDiveRadioGroup;
    private boolean isFirst;
    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erdpmlactivity);

        initViews();

    }

    private void initViews() {
        responseText = findViewById(R.id.response);
        checkSafetyBtn = findViewById(R.id.check_safety_btn);

        maxDepthLayout = findViewById(R.id.max_depth_tvl);
        bottomTimeLayout = findViewById(R.id.bottom_time_tvl);
        previousPgLayout = findViewById(R.id.previous_pg_tvl);
        surfaceIntervalLayout = findViewById(R.id.surface_interval_tvl);

        maxDepthEditText = findViewById(R.id.max_depth_et);
        bottomTimeEditText = findViewById(R.id.bottom_time_et);
        previousPgEditText = findViewById(R.id.previous_pg_et);
        surfaceIntervalEditText = findViewById(R.id.surface_interval_et);

        firstDiveRadioGroup = findViewById(R.id.radio_group_first_dive);
        firstDiveRadioGroup.check(R.id.radio_first_dive_yes_btn);
        isFirst = true;

        firstDiveRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_first_dive_yes_btn) {
                    previousPgLayout.setVisibility(View.GONE);
                    surfaceIntervalLayout.setVisibility(View.GONE);
                    isFirst = true;
                } else if (checkedId == R.id.radio_first_dive_no_btn) {
                    previousPgLayout.setVisibility(View.VISIBLE);
                    surfaceIntervalLayout.setVisibility(View.VISIBLE);
                    isFirst = false;
                }
            }
        });


        checkSafetyBtn.setOnClickListener(v -> {
            if (validateInputs()) {
                responseText.setVisibility(View.VISIBLE);
                String result = getThePressureGroup();
                responseText.setText(result);
            }
        });

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private boolean validateInputs() {
        boolean valid = true;

        // Validate max depth
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

        // Validate bottom time
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

        if (!isFirst) {
            // Validate previous pressure group
            String previousPg = previousPgEditText.getText().toString().trim();
            if (previousPg.isEmpty()) {
                previousPgLayout.setError("Please enter the previous pressure group.");
                valid = false;
            } else if (!isValidPressureGroup(previousPg)) {
                previousPgLayout.setError("Previous pressure group must be a single uppercase letter.");
                valid = false;
            } else {
                previousPgLayout.setError(null);
            }

            // Validate surface interval
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
        }

        // Validate if a radio button is selected
        if (firstDiveRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select if this is your first dive today.", Toast.LENGTH_SHORT).show();
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

    private boolean isValidPressureGroup(String str) {
        return str.length() == 1 && Character.isUpperCase(str.charAt(0));
    }

    private String getThePressureGroup() {
        String maxDepth;
        String bottomTime;
        String surfaceInterval;
        String previousPG;

        String result = "";

        List<Dive> dives = new ArrayList<>();
        PlannerClass planner = PlannerClass.getInstance();

        if (isFirst) {
            maxDepth = maxDepthEditText.getText().toString().trim();
            bottomTime = bottomTimeEditText.getText().toString().trim();

            int depthInt = Integer.parseInt(maxDepth);
            int bottomTimeInt = Integer.parseInt(bottomTime);
            Dive firstDive = new Dive(depthInt, bottomTimeInt, null);
            dives.add(firstDive);

            char pressureGroup = planner.getPressureGroup(depthInt, bottomTimeInt);
            if (pressureGroup == '-') {
                result = "Warning: Dive exceeds the no-decompression limit!";
            } else {
                result = "Pressure Group after First dive: " + pressureGroup;
                firstDive.setPressureGroupAfterDive(pressureGroup);
            }
        } else {
            maxDepth = maxDepthEditText.getText().toString().trim();
            bottomTime = bottomTimeEditText.getText().toString().trim();
            int depthInt = Integer.parseInt(maxDepth);
            int bottomTimeInt = Integer.parseInt(bottomTime);

            surfaceInterval = surfaceIntervalEditText.getText().toString().trim();
            previousPG = previousPgEditText.getText().toString().trim();

            char previousPressureGroup = previousPG.charAt(0);
            Duration surfaceIntervalText = Duration.parse("PT" + surfaceInterval.replace(":", "H") + "M0S");


            Dive dive = new Dive(depthInt, bottomTimeInt, surfaceIntervalText);
            dives.add(dive);

            char newPressureGroup = planner.getPressureGroupAfterSurfaceInterval(previousPressureGroup, surfaceIntervalText);
            dive.setPressureGroupBeforeDive(newPressureGroup);

            int residualNitrogenTime = planner.calculateResidualNitrogenTime(newPressureGroup, depthInt);
            dive.setResidualNitrogenTimeBroughtForward(residualNitrogenTime);

            int noDecompressionLimit = planner.getTheDecompressionLimit(newPressureGroup, depthInt);
            System.out.println("Here is the no-decompression limit: " + noDecompressionLimit);
            dive.setAdjustedNoDecompressionLimitBroughtForward(noDecompressionLimit);

            if (bottomTimeInt > noDecompressionLimit) {
                result = "Warning: Dive exceeds the no-decompression limit!";
            }
            int totalBottomTime = bottomTimeInt + residualNitrogenTime;
            dive.setTotalBottomTime(totalBottomTime);

            char pressureGroup = planner.getPressureGroup(depthInt, totalBottomTime);
            dive.setPressureGroupAfterDive(pressureGroup);
            if (pressureGroup == '-') {
                result = "Warning: Dive exceeds the no-decompression limit!";
            } else {
                result = "Pressure Group after dive: " + pressureGroup;
            }

        }

        return result;
    }

}