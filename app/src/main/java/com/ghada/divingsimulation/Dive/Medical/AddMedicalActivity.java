package com.ghada.divingsimulation.Dive.Medical;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    ImageView back_btn;
    CardView saveBtn;
    private CustomProgress mCustomProgress = CustomProgress.getInstance();
    private RadioGroup radioGroupQuestion1;
    private RadioGroup radioGroupQuestion2;
    private RadioGroup radioGroupQuestion3;
    private RadioGroup radioGroupQuestion4;
    private RadioGroup radioGroupQuestion5;
    private RadioGroup radioGroupQuestion6;
    private RadioGroup radioGroupQuestion7;
    private RadioGroup radioGroupQuestion8;
    private RadioGroup radioGroupQuestion9;
    private RadioGroup radioGroupQuestion10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical);

        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);


        radioGroupQuestion1 = findViewById(R.id.radio_group_question1);
        radioGroupQuestion2 = findViewById(R.id.radio_group_question2);
        radioGroupQuestion3 = findViewById(R.id.radio_group_question3);
        radioGroupQuestion4 = findViewById(R.id.radio_group_question4);
        radioGroupQuestion5 = findViewById(R.id.radio_group_question5);
        radioGroupQuestion6 = findViewById(R.id.radio_group_question6);
        radioGroupQuestion7 = findViewById(R.id.radio_group_question7);
        radioGroupQuestion8 = findViewById(R.id.radio_group_question8);
        radioGroupQuestion9 = findViewById(R.id.radio_group_question9);
        radioGroupQuestion10 = findViewById(R.id.radio_group_question10);


        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn = findViewById(R.id.add_medical_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
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

        // Create Medical object
        Medical medical = new Medical(getSelectedRadioButtonText(radioGroupQuestion1), getSelectedRadioButtonText(radioGroupQuestion2), getSelectedRadioButtonText(radioGroupQuestion3), getSelectedRadioButtonText(radioGroupQuestion4), getSelectedRadioButtonText(radioGroupQuestion5), getSelectedRadioButtonText(radioGroupQuestion6), getSelectedRadioButtonText(radioGroupQuestion7), getSelectedRadioButtonText(radioGroupQuestion8), getSelectedRadioButtonText(radioGroupQuestion9), getSelectedRadioButtonText(radioGroupQuestion10));

        mCustomProgress.showProgress(this, "Loading...", false);


        mUsersRef.child(getResources().getString(R.string.database_medical)).setValue(medical).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mCustomProgress.hideProgress();

            }
        });


        Log.d(TAG, "saveMedicalInfo: " + medical);
        Toast.makeText(this, "Medical info saved successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean validateInputs() {
        if (radioGroupQuestion1.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 1.");
            return false;
        }
        if (radioGroupQuestion2.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 2.");
            return false;
        }
        if (radioGroupQuestion3.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 3.");
            return false;
        }
        if (radioGroupQuestion4.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 4.");
            return false;
        }
        if (radioGroupQuestion5.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 5.");
            return false;
        }
        if (radioGroupQuestion6.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 6.");
            return false;
        }
        if (radioGroupQuestion7.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 7.");
            return false;
        }
        if (radioGroupQuestion8.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 8.");
            return false;
        }
        if (radioGroupQuestion9.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 9.");
            return false;
        }
        if (radioGroupQuestion10.getCheckedRadioButtonId() == -1) {
            showToast("Please answer question 10.");
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