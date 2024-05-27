package com.ghada.divingsimulation.Dive.LogBook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ghada.divingsimulation.Models.User.LogBook;
import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewLogBookActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final int START_TIME_PICKER = 1;
    private static final int BOTTOM_TIME_PICKER = 2;
    TextInputLayout dateTextInputLayout, startTimeTextInputLayout, bottomTimeTextInputLayout;
    ImageView back_btn;
    private int activeTimePicker = 0;
    private String TAG = "AddNewLogBookActivity";
    private Spinner diveTypeSpinner, waterTypeSpinner, visibilitySpinner, seaConditionSpinner, gasMixtureSpinner;
    private TextInputEditText dateEditText, startTimeEditText, bottomTimeEditText, startTankEditText, endTankEditText;
    private TextInputEditText diveSiteEditText, buddyEditText, instructorEditText, notesEditText, maxDepthEditText, locationEditText;
    private CardView saveButton;
    private String selectedDate, selectedStartTime, selectedBottomTime;

    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    private CustomProgress mCustomProgress = CustomProgress.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_log_book);

        initViews();

    }

    private void initViews() {

        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);


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
        saveButton = findViewById(R.id.log_save_log_book_btn);

        dateTextInputLayout = findViewById(R.id.log_date_tvl);
        startTimeTextInputLayout = findViewById(R.id.log_start_time_tvl);
        bottomTimeTextInputLayout = findViewById(R.id.log_bottom_time_tvl);

        initDiveTypeSpinner();
        initWaterTypeSpinner();
        initVisibilitySpinner();
        initSeaConditionSpinner();
        initGasMixtureSpinner();


        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        dateTextInputLayout.setEndIconOnClickListener(v -> showDatePicker());
        startTimeTextInputLayout.setEndIconOnClickListener(v -> {
            activeTimePicker = START_TIME_PICKER;
            showTimePicker();
        });
        bottomTimeTextInputLayout.setEndIconOnClickListener(v -> {
            activeTimePicker = BOTTOM_TIME_PICKER;
            showTimePicker();
        });
        saveButton.setOnClickListener(v -> saveLogBook());

    }


    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddNewLogBookActivity.this, R.style.DialogTheme, this, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), DateFormat.is24HourFormat(AddNewLogBookActivity.this));
        timePickerDialog.show();
        timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.main_color_dark));
        timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.main_color_dark));
    }

    private void showDatePicker() {
        Log.e(TAG, "showDatePicker: test");
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewLogBookActivity.this, R.style.DialogTheme, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.main_color_dark));
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.main_color_dark));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        selectedDate = year + "/" + (month + 1) + "/" + dayOfMonth;
        dateEditText.setText(selectedDate);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String selectedTime = hourOfDay + ":" + minute + ":00";
        switch (activeTimePicker) {
            case START_TIME_PICKER:
                selectedStartTime = selectedTime;
                startTimeEditText.setText(selectedTime);
                Log.e(TAG, "onTimeSet: Start ::: " + selectedTime);
                break;
            case BOTTOM_TIME_PICKER:
                selectedBottomTime = selectedTime;
                bottomTimeEditText.setText(selectedTime);
                Log.e(TAG, "onTimeSet: Bottom ::: " + selectedTime);
                break;
        }
    }


    private void saveLogBook() {
        String date = dateEditText.getText().toString();
        String startTime = startTimeEditText.getText().toString();
        String bottomTime = bottomTimeEditText.getText().toString();
        String startTank = startTankEditText.getText().toString();
        String endTank = endTankEditText.getText().toString();
        String diveSite = diveSiteEditText.getText().toString();
        String buddy = buddyEditText.getText().toString();
        String instructor = instructorEditText.getText().toString();
        String notes = notesEditText.getText().toString();
        String maxDepth = maxDepthEditText.getText().toString();
        String location = locationEditText.getText().toString();

        String diveType = diveTypeSpinner.getSelectedItem().toString();
        String waterType = waterTypeSpinner.getSelectedItem().toString();
        String visibility = visibilitySpinner.getSelectedItem().toString();
        String seaCondition = seaConditionSpinner.getSelectedItem().toString();
        String gasMixture = gasMixtureSpinner.getSelectedItem().toString();

        if (validateInputs(date, startTime, bottomTime, startTank, endTank, diveSite, buddy, instructor, maxDepth, location, diveType, waterType, visibility, seaCondition, gasMixture)) {
            LogBook newLogBook = new LogBook(date, diveSite, buddy, instructor, startTime, bottomTime, startTank, endTank, seaCondition, visibility, notes, diveType, waterType, gasMixture, maxDepth, location);

            mCustomProgress.showProgress(this, "Loading...", false);
            mUsersRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        userData = snapshot.getValue(UserDataModel.class);
                        ArrayList<LogBook> logBooksList = new ArrayList<>();
                        if (userData.getLogBook() != null) {
                            logBooksList.addAll(userData.getLogBook());
                        }
                        logBooksList.add(newLogBook);
                        mUsersRef.child(getResources().getString(R.string.database_logbooks)).setValue(logBooksList);
                    }
                    mCustomProgress.hideProgress();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle onCancelled
                    mCustomProgress.hideProgress();
                }
            });
            Toast.makeText(this, "Log Book Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs(String... inputs) {
        for (String input : inputs) {
            if (input.isEmpty() || input.equals("Select Dive Type ...") || input.equals("Select Water Type ...") || input.equals("Select Visibility ...") || input.equals("Select Sea Condition ...") || input.equals("Select Gas Mixture ...")) {
                Toast.makeText(this, "Please fill all the fields correctly", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


    private void initDiveTypeSpinner() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Select Dive Type ...");
        list.add("Recreational");
        list.add("Technical");
        list.add("Cave");
        list.add("Wreck");
        list.add("Deep");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diveTypeSpinner.setAdapter(adapter);
        diveTypeSpinner.setSelection(0);
    }

    private void initWaterTypeSpinner() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Select Water Type ...");
        list.add("Freshwater");
        list.add("Saltwater");
        list.add("Brackish");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterTypeSpinner.setAdapter(adapter);
        waterTypeSpinner.setSelection(0);
    }

    private void initVisibilitySpinner() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Select Visibility ...");
        list.add("Poor (<5 meters)");
        list.add("Moderate (5-10 meters)");
        list.add("Good (10-20 meters)");
        list.add("Excellent (>20 meters)");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        visibilitySpinner.setAdapter(adapter);
        visibilitySpinner.setSelection(0);
    }


    private void initSeaConditionSpinner() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Select Sea Condition ...");
        list.add("Calm");
        list.add("Moderate");
        list.add("Rough");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seaConditionSpinner.setAdapter(adapter);
        seaConditionSpinner.setSelection(0);
    }

    private void initGasMixtureSpinner() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Select Gas Mixture ...");
        list.add("Air");
        list.add("Nitrox");
        list.add("Trimix");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gasMixtureSpinner.setAdapter(adapter);
        gasMixtureSpinner.setSelection(0);
    }

}