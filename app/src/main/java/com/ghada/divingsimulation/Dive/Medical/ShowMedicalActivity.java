package com.ghada.divingsimulation.Dive.Medical;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Models.User.Medical;
import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowMedicalActivity extends AppCompatActivity {

    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    ImageView back_btn;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_medical);

        initViews();


    }

    private void initViews() {

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

        getData();

    }

    private void getData() {


        mCustomProgress.showProgress(this, "Loading...", false);


        mUsersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    userData = snapshot.getValue(UserDataModel.class);
                    Medical medical = userData.getMedical();

                    if (medical != null) {
                        setRadioGroupSelection(radioGroupQuestion1, medical.getQuestion1());
                        setRadioGroupSelection(radioGroupQuestion2, medical.getQuestion2());
                        setRadioGroupSelection(radioGroupQuestion3, medical.getQuestion3());
                        setRadioGroupSelection(radioGroupQuestion4, medical.getQuestion4());
                        setRadioGroupSelection(radioGroupQuestion5, medical.getQuestion5());
                        setRadioGroupSelection(radioGroupQuestion6, medical.getQuestion6());
                        setRadioGroupSelection(radioGroupQuestion7, medical.getQuestion7());
                        setRadioGroupSelection(radioGroupQuestion8, medical.getQuestion8());
                        setRadioGroupSelection(radioGroupQuestion9, medical.getQuestion9());
                        setRadioGroupSelection(radioGroupQuestion10, medical.getQuestion10());

                        disableRadioGroup(radioGroupQuestion1);
                        disableRadioGroup(radioGroupQuestion2);
                        disableRadioGroup(radioGroupQuestion3);
                        disableRadioGroup(radioGroupQuestion4);
                        disableRadioGroup(radioGroupQuestion5);
                        disableRadioGroup(radioGroupQuestion6);
                        disableRadioGroup(radioGroupQuestion7);
                        disableRadioGroup(radioGroupQuestion8);
                        disableRadioGroup(radioGroupQuestion9);
                        disableRadioGroup(radioGroupQuestion10);
                    }


                    mCustomProgress.hideProgress();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//    private void fillDataFromMedical(Medical medical) {
//        // Set RadioGroup values
//        setRadioGroupValue(radioGroupQuestion1, medical.getQuestion1());
//        setRadioGroupValue(radioGroupQuestion2, medical.getQuestion2());
//        setRadioGroupValue(radioGroupQuestion3, medical.getQuestion3());
//        setRadioGroupValue(radioGroupQuestion4, medical.getQuestion4());
//        setRadioGroupValue(radioGroupQuestion5, medical.getQuestion5());
//        setRadioGroupValue(radioGroupQuestion6, medical.getQuestion6());
//        setRadioGroupValue(radioGroupQuestion7, medical.getQuestion7());
//        setRadioGroupValue(radioGroupQuestion8, medical.getQuestion8());
//        setRadioGroupValue(radioGroupQuestion9, medical.getQuestion9());
//        setRadioGroupValue(radioGroupQuestion10, medical.getQuestion10());
//
//        // Disable all inputs
//        disableAllInputs();
//    }

    private void setRadioGroupSelection(RadioGroup radioGroup, String answer) {
        int count = radioGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) o;
                if (radioButton.getText().toString().equals(answer)) {
                    radioButton.setChecked(true);
                    break;
                }
            }
        }
    }

    private void disableRadioGroup(RadioGroup radioGroup) {
        int count = radioGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton) {
                o.setEnabled(false);
            }
        }
    }


}