package com.ghada.divingsimulation.Dive.Accident;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ghada.divingsimulation.Models.User.Accident;
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

public class AddAccidentActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    TextInputLayout dateTextInputLayout, timeTextInputLayout, depthAtAccidentInputLayout;
    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    private TextInputEditText rescuerNameEditText, extentOfInjuryEditText, vicNameEditText, vicNationalityEditText, vicAgeEditText, vicCertLevelEditText, depthAtAccidentEditText, cityEditText, currentDiveSiteEditText, currentUnderWaterEditText, currentSurfaceEditText, EANxEditText, dateEditText, timeEditText;
    private RadioGroup fatalRadioGroup, trainingRadioGroup, vicGenderRadioGroup, foundOnSurfaceRadioGroup, drySuitRadioGroup, equipmentRentedRadioGroup;
    private String selectedDate;
    private CardView saveButton;
    private ImageView backBtn;
    private CustomProgress mCustomProgress = CustomProgress.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accident);

        initViews();

    }

    private void initViews() {

        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);

        rescuerNameEditText = findViewById(R.id.rescuer_name_et);
        extentOfInjuryEditText = findViewById(R.id.extent_of_injury_et);
        vicNameEditText = findViewById(R.id.vic_name_et);
        vicNationalityEditText = findViewById(R.id.vic_nationality_et);
        vicAgeEditText = findViewById(R.id.vic_age_et);
        vicCertLevelEditText = findViewById(R.id.vic_cert_level_et);
        depthAtAccidentEditText = findViewById(R.id.depth_at_accident_et);
        cityEditText = findViewById(R.id.city_et);
        currentDiveSiteEditText = findViewById(R.id.current_dive_site_et);
        currentUnderWaterEditText = findViewById(R.id.current_underwater_et);
        currentSurfaceEditText = findViewById(R.id.current_surface_et);
        EANxEditText = findViewById(R.id.EANx_et);
        dateEditText = findViewById(R.id.date_et);
        timeEditText = findViewById(R.id.time_et);

        fatalRadioGroup = findViewById(R.id.radio_group_fatal);
        trainingRadioGroup = findViewById(R.id.radio_group_training);
        vicGenderRadioGroup = findViewById(R.id.radio_group_vic_gender);
        foundOnSurfaceRadioGroup = findViewById(R.id.radio_group_found_on_surface);
        drySuitRadioGroup = findViewById(R.id.radio_group_dry_suit);
        equipmentRentedRadioGroup = findViewById(R.id.radio_group_equipment_rented);

        depthAtAccidentInputLayout = findViewById(R.id.depth_at_accident_tvl);
        dateTextInputLayout = findViewById(R.id.date_tvl);
        timeTextInputLayout = findViewById(R.id.time_tvl);
        dateTextInputLayout.setEndIconOnClickListener(v -> showDatePicker());
        timeTextInputLayout.setEndIconOnClickListener(v -> showTimePicker());


        foundOnSurfaceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_found_on_surface_yes_btn) {
                    depthAtAccidentInputLayout.setVisibility(View.GONE);
                    depthAtAccidentEditText.setText("0");
                } else if (checkedId == R.id.radio_found_on_surface_no_btn) {
                    depthAtAccidentInputLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        saveButton = findViewById(R.id.save_accident_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccident();
            }
        });

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAccidentActivity.this, AccidentActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void saveAccident() {
        // Get values from EditText fields
        String rescuerName = rescuerNameEditText.getText().toString();
        String accidentDate = dateEditText.getText().toString();
        String vicAge = vicAgeEditText.getText().toString();
        String vicCertLevel = vicCertLevelEditText.getText().toString();
        String depthAtAccident = depthAtAccidentEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String currentDiveSite = currentDiveSiteEditText.getText().toString();
        String currentUnderwater = currentUnderWaterEditText.getText().toString();
        String currentSurface = currentSurfaceEditText.getText().toString();
        String eanx = EANxEditText.getText().toString();
        String extentOfInjury = extentOfInjuryEditText.getText().toString();
        String vicName = vicNameEditText.getText().toString();
        String vicNationality = vicNationalityEditText.getText().toString();
        String accidentTime = timeEditText.getText().toString();


        // Get selected values from RadioGroups
        int fatalId = fatalRadioGroup.getCheckedRadioButtonId();
        int trainingId = trainingRadioGroup.getCheckedRadioButtonId();
        int vicGenderId = vicGenderRadioGroup.getCheckedRadioButtonId();
        int foundOnSurfaceId = foundOnSurfaceRadioGroup.getCheckedRadioButtonId();
        int drySuitId = drySuitRadioGroup.getCheckedRadioButtonId();
        int equipmentRentedId = equipmentRentedRadioGroup.getCheckedRadioButtonId();

        if (fatalId == -1 || trainingId == -1 || vicGenderId == -1 || foundOnSurfaceId == -1 || drySuitId == -1 || equipmentRentedId == -1) {
            Toast.makeText(this, "Please fill all the fields correctly", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton fatalRadioButton = findViewById(fatalId);
        RadioButton trainingRadioButton = findViewById(trainingId);
        RadioButton vicGenderRadioButton = findViewById(vicGenderId);
        RadioButton foundOnSurfaceRadioButton = findViewById(foundOnSurfaceId);
        RadioButton drySuitRadioButton = findViewById(drySuitId);
        RadioButton equipmentRentedRadioButton = findViewById(equipmentRentedId);

        String fatal = fatalRadioButton.getText().toString();
        String training = trainingRadioButton.getText().toString();
        String vicGender = vicGenderRadioButton.getText().toString();
        String foundOnSurface = foundOnSurfaceRadioButton.getText().toString();
        String drySuit = drySuitRadioButton.getText().toString();
        String equipmentRented = equipmentRentedRadioButton.getText().toString();

        // Validate input fields
        if (validateInputs(rescuerName, accidentDate, vicAge, vicCertLevel, depthAtAccident, city, currentDiveSite, currentUnderwater, currentSurface, eanx, extentOfInjury, vicName, vicNationality)) {

            Accident newAccident = new Accident(rescuerName, fatal, training, accidentDate, accidentTime, extentOfInjury, vicName, vicNationality, vicAge, vicGender, vicCertLevel, foundOnSurface, depthAtAccident, city, currentDiveSite, currentUnderwater, currentSurface, equipmentRented, drySuit, eanx);


            mCustomProgress.showProgress(this, "Loading...", false);
            mUsersRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        userData = snapshot.getValue(UserDataModel.class);
                        ArrayList<Accident> accidentsList = new ArrayList<>();
                        if (userData.getAccidents() != null) {
                            accidentsList.addAll(userData.getAccidents());
                        }
                        accidentsList.add(newAccident);
                        mUsersRef.child(getResources().getString(R.string.database_accidents)).setValue(accidentsList);
                    }
                    mCustomProgress.hideProgress();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle onCancelled
                    mCustomProgress.hideProgress();
                }
            });

            Toast.makeText(this, "Accident Saved", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AddAccidentActivity.this, AccidentActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }


    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(AddAccidentActivity.this, R.style.DialogTheme, this, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), DateFormat.is24HourFormat(AddAccidentActivity.this));
        timePickerDialog.show();
        timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.main_color_dark));
        timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.main_color_dark));
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddAccidentActivity.this, R.style.DialogTheme, this, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
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
        timeEditText.setText(selectedTime);
    }


    private boolean validateInputs(String... inputs) {
        for (String input : inputs) {
            if (input.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields correctly", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (fatalRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select whether the accident was fatal", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (trainingRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select whether the victim had training", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (vicGenderRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select the victim's gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (foundOnSurfaceRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select whether the victim was found on the surface", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (drySuitRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select whether the suit was dry", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (equipmentRentedRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select whether equipment are rented", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}