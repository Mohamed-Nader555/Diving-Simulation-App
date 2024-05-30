package com.ghada.divingsimulation.Dive.LogBook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Models.User.LogBook;
import com.ghada.divingsimulation.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Collections;
import java.util.List;

public class LogBookDetailsActivity extends AppCompatActivity {

    TextInputLayout dateTextInputLayout, startTimeTextInputLayout, bottomTimeTextInputLayout;
    ImageView back_btn;
    private LogBook logBook;
    private String TAG = "AddNewLogBookActivity";
    private Spinner diveTypeSpinner, waterTypeSpinner, visibilitySpinner, seaConditionSpinner, gasMixtureSpinner;
    private TextInputEditText dateEditText, startTimeEditText, bottomTimeEditText, startTankEditText, endTankEditText;
    private TextInputEditText diveSiteEditText, buddyEditText, instructorEditText, notesEditText, maxDepthEditText, locationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book_details);


        initViews();

    }

    private void initViews() {

        Intent intent = getIntent();
        logBook = (LogBook) intent.getSerializableExtra("item");

        diveTypeSpinner = findViewById(R.id.log_dive_type_spinner);
        waterTypeSpinner = findViewById(R.id.log_water_type_spinner);
        visibilitySpinner = findViewById(R.id.log_visibility_spinner);
        seaConditionSpinner = findViewById(R.id.log_sea_condition_spinner);
        gasMixtureSpinner = findViewById(R.id.log_gas_mixture_spinner);
        dateEditText = findViewById(R.id.log_date_et);
        startTimeEditText = findViewById(R.id.log_start_time_et);
        bottomTimeEditText = findViewById(R.id.log_bottom_time_et);
        startTankEditText = findViewById(R.id.log_start_tank_et);
        endTankEditText = findViewById(R.id.log_end_tank_et);
        diveSiteEditText = findViewById(R.id.log_dive_site_et);
        buddyEditText = findViewById(R.id.log_buddy_et);
        instructorEditText = findViewById(R.id.log_instructor_et);
        notesEditText = findViewById(R.id.log_notes_et);
        maxDepthEditText = findViewById(R.id.log_max_depth_et);
        locationEditText = findViewById(R.id.log_location_et);

        dateTextInputLayout = findViewById(R.id.log_date_tvl);
        startTimeTextInputLayout = findViewById(R.id.log_start_time_tvl);
        bottomTimeTextInputLayout = findViewById(R.id.log_bottom_time_tvl);


        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Populate data
        populateData(logBook);

    }


    private void populateData(LogBook logBook) {
        dateEditText.setText(logBook.getDate());
        startTimeEditText.setText(logBook.getStartTime());
        bottomTimeEditText.setText(logBook.getBottomTime());
        startTankEditText.setText(logBook.getStartTank());
        endTankEditText.setText(logBook.getEndTank());
        diveSiteEditText.setText(logBook.getDiveSite());
        buddyEditText.setText(logBook.getBuddy());
        instructorEditText.setText(logBook.getInstructor());
        notesEditText.setText(logBook.getNotes());
        maxDepthEditText.setText(logBook.getMaxDepth());
        locationEditText.setText(logBook.getLocation());

        // Assuming you have set adapters for the spinners, find the position of the current value and set it
        populateSpinner(diveTypeSpinner, logBook.getDiveType());
        populateSpinner(waterTypeSpinner, logBook.getWaterType());
        populateSpinner(visibilitySpinner, logBook.getVisibility());
        populateSpinner(seaConditionSpinner, logBook.getSeaCondition());
        populateSpinner(gasMixtureSpinner, logBook.getGasMixture());

        disableFields();

    }


    private void disableFields() {
        dateEditText.setEnabled(false);
        startTimeEditText.setEnabled(false);
        bottomTimeEditText.setEnabled(false);
        startTankEditText.setEnabled(false);
        endTankEditText.setEnabled(false);
        diveSiteEditText.setEnabled(false);
        buddyEditText.setEnabled(false);
        instructorEditText.setEnabled(false);
        notesEditText.setEnabled(false);
        maxDepthEditText.setEnabled(false);
        locationEditText.setEnabled(false);

        diveTypeSpinner.setEnabled(false);
        waterTypeSpinner.setEnabled(false);
        visibilitySpinner.setEnabled(false);
        seaConditionSpinner.setEnabled(false);
        gasMixtureSpinner.setEnabled(false);

        dateTextInputLayout.setEnabled(false);
        startTimeTextInputLayout.setEnabled(false);
        bottomTimeTextInputLayout.setEnabled(false);
    }


    private void populateSpinner(Spinner spinner, String certificationType) {
        List<String> certTypes = Collections.singletonList(certificationType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, certTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setEnabled(false);
    }


}