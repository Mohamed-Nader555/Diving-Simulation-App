package com.ghada.divingsimulation.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ghada.divingsimulation.Models.Accident;
import com.ghada.divingsimulation.Models.Certificates;
import com.ghada.divingsimulation.Models.LogBook;
import com.ghada.divingsimulation.Models.Medical;
import com.ghada.divingsimulation.Models.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.AESCrypt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "SignupActivity_TAG";
    ProgressDialog mLoading;
    private TextInputLayout fullNameInputLayout, emailInputLayout, passwordInputLayout, repeatPasswordInputLayout;
    private EditText fullNameEditText, emailEditText, passwordEditText, repeatPasswordEditText;
    private CardView signupButton;
    private TextView loginTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();

    }

    private void initViews() {

        mAuth = FirebaseAuth.getInstance();
        mLoading = new ProgressDialog(this);


        fullNameInputLayout = findViewById(R.id.full_name_input_et);
        emailInputLayout = findViewById(R.id.email_input_et);
        passwordInputLayout = findViewById(R.id.password_input_et);
        repeatPasswordInputLayout = findViewById(R.id.repeat_password_input_et);

        fullNameEditText = findViewById(R.id.full_name_et);
        emailEditText = findViewById(R.id.email_et);
        passwordEditText = findViewById(R.id.password_et);
        repeatPasswordEditText = findViewById(R.id.repeat_password_et);


        signupButton = findViewById(R.id.email_sign_up_btn);
        signupButton.setOnClickListener(this);

        loginTextView = findViewById(R.id.sign_in_tv);
        loginTextView.setOnClickListener(this);

        handelTextFields();

    }

    private void handelTextFields() {
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (!input.contains("@") || !input.contains("."))
                    emailInputLayout.setError("Invalid Email!");
                else emailInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (input.length() < 8) {
                    passwordInputLayout.setError("Short Password! Make it Longer!");
                } else if (input.length() > 10) {
                    passwordInputLayout.setError("Short Password! Make it Shorter!");
                } else {
                    passwordInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        repeatPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (input.length() < 8) {
                    repeatPasswordEditText.setError("Short Password! Make it Longer!");
                } else if (input.length() > 10) {
                    repeatPasswordEditText.setError("Short Password! Make it Shorter!");
                } else {
                    repeatPasswordEditText.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    private void saveAccountUserData() {
        String password;
        try {
            password = AESCrypt.encrypt(passwordEditText.getText().toString());
        } catch (Exception e) {
            Log.e("Diving Simulation", "saveAccountUserData: " + e.getMessage());
            password = passwordEditText.getText().toString();
        }


        String currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference("Users");
        UserDataModel dataModel = new UserDataModel(currentUserID,
                fullNameEditText.getText().toString(),
                emailEditText.getText().toString(),
                "",
                password,
                "",
                "",
                "",
                "",
                "",
                new ArrayList<Certificates>(),
                new Medical(),
                new ArrayList<LogBook>(),
                new ArrayList<Accident>());


        UsersRef.child(currentUserID).setValue(dataModel).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Data Saved Successfully", Toast.LENGTH_LONG).show();
                } else {
                    String message = task.getException().getMessage();
                    Toast.makeText(SignupActivity.this, "Error Occurred :" + message, Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.email_sign_up_btn:
                if (validate()) {
                    createNewAccount();
                }
                break;
            case R.id.sign_in_tv:
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    boolean validate() {
        if (fullNameEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (emailEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            emailEditText.setError("Password Don't Match");
            return false;
        } else if (passwordEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            repeatPasswordEditText.setError("Password Don't Match");
            return false;
        } else if (repeatPasswordEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Repeat Your Password", Toast.LENGTH_SHORT).show();
            repeatPasswordEditText.setError("Please Repeat You Password");
            return false;
        } else if (!passwordEditText.getText().toString().equals(repeatPasswordEditText.getText().toString())) {
            repeatPasswordEditText.setError("Password Don't Match");
            return false;
        }
        return true;
    }


    private void createNewAccount() {

        mLoading.setTitle("Creating New Account");
        mLoading.setMessage("Please Wait, while we are creating your new Account");
        mLoading.show();
        mLoading.setCanceledOnTouchOutside(true);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    SendEmailVerificationMessage();
                    saveAccountUserData();
                } else {
                    String message = task.getException().getMessage();
                    Toast.makeText(SignupActivity.this, "Error Occurred: " + message, Toast.LENGTH_LONG).show();
                    mLoading.dismiss();
                }


            }
        });
    }

    private void SendEmailVerificationMessage() {
        final FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        firebaseUser.reload();
                        Toast.makeText(SignupActivity.this, "Check your email and verify your email.. ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        mAuth.signOut();
                        finish();
                    } else {
                        String error = task.getException().toString();
                        Toast.makeText(SignupActivity.this, "Error : " + error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


}