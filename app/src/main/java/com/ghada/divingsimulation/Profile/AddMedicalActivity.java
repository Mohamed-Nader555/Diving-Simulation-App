package com.ghada.divingsimulation.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Models.User.Medical;
import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMedicalActivity extends AppCompatActivity {

    private static final String TAG = "AddMedicalActivity";
    private RadioGroup radioGroupBloodPressure, radioGroupAge, radioGroupPastInjuries, radioGroupChronicDiseases, radioGroupEarDiseases, radioGroupHeartDiseases;
    private RadioButton radioBloodPressureYes, radioBloodPressureNo, radioAgeYes, radioAgeNo, radioPastInjuriesYes, radioPastInjuriesNo, radioChronicDiseasesYes, radioChronicDiseasesNo, radioEarDiseasesYes, radioEarDiseasesNo, radioHeartDiseasesYes, radioHeartDiseasesNo;


    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    private CustomProgress mCustomProgress = CustomProgress.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical);

        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);


        radioGroupBloodPressure = findViewById(R.id.radio_group_blood_pressure_question);
        radioGroupAge = findViewById(R.id.radio_group_age_question);
        radioGroupPastInjuries = findViewById(R.id.radio_group_past_inj_question);
        radioGroupChronicDiseases = findViewById(R.id.radio_group_chronic_diseases_question);
        radioGroupEarDiseases = findViewById(R.id.radio_group_ear_diseases_question);
        radioGroupHeartDiseases = findViewById(R.id.radio_group_heart_diseases_question);

        radioBloodPressureYes = findViewById(R.id.radio_blood_pressure_yes_btn);
        radioBloodPressureNo = findViewById(R.id.radio_blood_pressure_no_btn);
        radioAgeYes = findViewById(R.id.radio_age_yes_btn);
        radioAgeNo = findViewById(R.id.radio_age_no_btn);
        radioPastInjuriesYes = findViewById(R.id.radio_past_inj_yes_btn);
        radioPastInjuriesNo = findViewById(R.id.radio_past_inj_no_btn);
        radioChronicDiseasesYes = findViewById(R.id.radio_chronic_diseases_yes_btn);
        radioChronicDiseasesNo = findViewById(R.id.radio_chronic_diseases_no_btn);
        radioEarDiseasesYes = findViewById(R.id.radio_ear_diseases_yes_btn);
        radioEarDiseasesNo = findViewById(R.id.radio_ear_diseases_no_btn);
        radioHeartDiseasesYes = findViewById(R.id.radio_heart_diseases_yes_btn);
        radioHeartDiseasesNo = findViewById(R.id.radio_heart_diseases_no_btn);

        findViewById(R.id.cert_save_cert_book_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMedicalInfo();
            }
        });
    }

    private void saveMedicalInfo() {
        if (!validateInputs()) {
            return;
        }

        String bloodPressure = getSelectedRadioButtonText(radioGroupBloodPressure);
        String age = getSelectedRadioButtonText(radioGroupAge);
        String pastInjuries = getSelectedRadioButtonText(radioGroupPastInjuries);
        String chronicDiseases = getSelectedRadioButtonText(radioGroupChronicDiseases);
        String earDiseases = getSelectedRadioButtonText(radioGroupEarDiseases);
        String heartDiseases = getSelectedRadioButtonText(radioGroupHeartDiseases);

        // Create Medical object
        Medical medical = new Medical(bloodPressure, age, pastInjuries, chronicDiseases, earDiseases, heartDiseases);

        mCustomProgress.showProgress(this, "Loading...", false);


        mUsersRef.child(getResources().getString(R.string.database_medical)).setValue(medical).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mCustomProgress.hideProgress();

            }
        });


        Log.d(TAG,"saveMedicalInfo: "+medical);
        Toast.makeText(this,"Medical info saved successfully!",Toast.LENGTH_SHORT).

    show();

}

    private boolean validateInputs() {
        if (radioGroupBloodPressure.getCheckedRadioButtonId() == -1) {
            showToast("Please answer the blood pressure question.");
            return false;
        }
        if (radioGroupAge.getCheckedRadioButtonId() == -1) {
            showToast("Please answer the age question.");
            return false;
        }
        if (radioGroupPastInjuries.getCheckedRadioButtonId() == -1) {
            showToast("Please answer the past injuries question.");
            return false;
        }
        if (radioGroupChronicDiseases.getCheckedRadioButtonId() == -1) {
            showToast("Please answer the chronic diseases question.");
            return false;
        }
        if (radioGroupEarDiseases.getCheckedRadioButtonId() == -1) {
            showToast("Please answer the ear diseases question.");
            return false;
        }
        if (radioGroupHeartDiseases.getCheckedRadioButtonId() == -1) {
            showToast("Please answer the heart diseases question.");
            return false;
        }
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String getSelectedRadioButtonText(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        return selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";
    }
}