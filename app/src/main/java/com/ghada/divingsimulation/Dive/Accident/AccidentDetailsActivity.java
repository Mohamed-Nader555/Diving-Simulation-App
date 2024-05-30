package com.ghada.divingsimulation.Dive.Accident;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Models.User.Accident;
import com.ghada.divingsimulation.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AccidentDetailsActivity extends AppCompatActivity {

    Accident accident;
    TextInputLayout dateTextInputLayout, timeTextInputLayout;
    ImageView back_btn;
    private TextInputEditText rescuerNameEditText, extentOfInjuryEditText, vicNameEditText, vicNationalityEditText, vicAgeEditText, vicCertLevelEditText, depthAtAccidentEditText, cityEditText, currentDiveSiteEditText, currentUnderWaterEditText, currentSurfaceEditText, EANxEditText, dateEditText, timeEditText;
    private RadioGroup fatalRadioGroup, trainingRadioGroup, vicGenderRadioGroup, foundOnSurfaceRadioGroup, drySuitRadioGroup, equipmentRentedRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_details);

        initViews();

    }

    private void initViews() {

        Intent intent = getIntent();
        accident = (Accident) intent.getSerializableExtra("item");

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


        dateTextInputLayout = findViewById(R.id.date_tvl);
        timeTextInputLayout = findViewById(R.id.time_tvl);


        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fillDataFromAccident(accident);

    }


    private void fillDataFromAccident(Accident accident) {
        // Fill the data
        rescuerNameEditText.setText(accident.getRescuerName());
        extentOfInjuryEditText.setText(accident.getExtentOfInjury());
        vicNameEditText.setText(accident.getVicName());
        vicNationalityEditText.setText(accident.getVicNationality());
        vicAgeEditText.setText(String.valueOf(accident.getVicAge()));
        vicCertLevelEditText.setText(accident.getVicCertLevel());
        depthAtAccidentEditText.setText(String.valueOf(accident.getDepthAtAccident()));
        cityEditText.setText(accident.getCity());
        currentDiveSiteEditText.setText(accident.getCurrentDiveSite());
        currentUnderWaterEditText.setText(accident.getCurrentUnderWater());
        currentSurfaceEditText.setText(accident.getCurrentSurface());
        EANxEditText.setText(accident.getEANx() + " %");
        dateEditText.setText(accident.getDate());
        timeEditText.setText(accident.getTime());

        // Set RadioGroup values
        setRadioGroupValue(fatalRadioGroup, accident.getFetal());
        setRadioGroupValue(trainingRadioGroup, accident.getTraining());
        setRadioGroupValue(vicGenderRadioGroup, accident.getVicGender());
        setRadioGroupValue(foundOnSurfaceRadioGroup, accident.getFoundOnSurface());
        setRadioGroupValue(drySuitRadioGroup, accident.getDrySuit());
        setRadioGroupValue(equipmentRentedRadioGroup, accident.getEquipmentRented());


        disableAllInputs();


    }

    private void setRadioGroupValue(RadioGroup radioGroup, String value) {
        int yesId, noId;

        switch (radioGroup.getId()) {
            case R.id.radio_group_fatal:
                yesId = R.id.radio_fatal_yes_btn;
                noId = R.id.radio_fatal_no_btn;
                break;
            case R.id.radio_group_training:
                yesId = R.id.radio_training_yes_btn;
                noId = R.id.radio_training_no_btn;
                break;
            case R.id.radio_group_vic_gender:
                if (value.equalsIgnoreCase("Male")) {
                    radioGroup.check(R.id.radio_vic_gender_male_btn);
                } else if (value.equalsIgnoreCase("Female")) {
                    radioGroup.check(R.id.radio_vic_gender_female_btn);
                }
                return;
            case R.id.radio_group_found_on_surface:
                yesId = R.id.radio_found_on_surface_yes_btn;
                noId = R.id.radio_found_on_surface_no_btn;
                break;
            case R.id.radio_group_dry_suit:
                yesId = R.id.radio_dry_suit_yes_btn;
                noId = R.id.radio_dry_suit_no_btn;
                break;
            case R.id.radio_group_equipment_rented:
                yesId = R.id.radio_equipment_rented_yes_btn;
                noId = R.id.radio_equipment_rented_no_btn;
                break;
            default:
                return;
        }

        radioGroup.check(value.equalsIgnoreCase("Yes") ? yesId : noId);
    }


    private void disableAllInputs() {
        rescuerNameEditText.setEnabled(false);
        extentOfInjuryEditText.setEnabled(false);
        vicNameEditText.setEnabled(false);
        vicNationalityEditText.setEnabled(false);
        vicAgeEditText.setEnabled(false);
        vicCertLevelEditText.setEnabled(false);
        depthAtAccidentEditText.setEnabled(false);
        cityEditText.setEnabled(false);
        currentDiveSiteEditText.setEnabled(false);
        currentUnderWaterEditText.setEnabled(false);
        currentSurfaceEditText.setEnabled(false);
        EANxEditText.setEnabled(false);
        dateEditText.setEnabled(false);
        timeEditText.setEnabled(false);

        fatalRadioGroup.setEnabled(false);
        trainingRadioGroup.setEnabled(false);
        vicGenderRadioGroup.setEnabled(false);
        foundOnSurfaceRadioGroup.setEnabled(false);
        drySuitRadioGroup.setEnabled(false);
        equipmentRentedRadioGroup.setEnabled(false);

        for (int i = 0; i < fatalRadioGroup.getChildCount(); i++) {
            fatalRadioGroup.getChildAt(i).setEnabled(false);
        }
        for (int i = 0; i < trainingRadioGroup.getChildCount(); i++) {
            trainingRadioGroup.getChildAt(i).setEnabled(false);
        }
        for (int i = 0; i < vicGenderRadioGroup.getChildCount(); i++) {
            vicGenderRadioGroup.getChildAt(i).setEnabled(false);
        }
        for (int i = 0; i < foundOnSurfaceRadioGroup.getChildCount(); i++) {
            foundOnSurfaceRadioGroup.getChildAt(i).setEnabled(false);
        }
        for (int i = 0; i < drySuitRadioGroup.getChildCount(); i++) {
            drySuitRadioGroup.getChildAt(i).setEnabled(false);
        }
        for (int i = 0; i < equipmentRentedRadioGroup.getChildCount(); i++) {
            equipmentRentedRadioGroup.getChildAt(i).setEnabled(false);
        }

    }


}