package com.ghada.divingsimulation.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.AESCrypt;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    final String TAG = "ProfileActivity";
    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    GoogleSignInClient mGoogleSignInClient;
    Button savePasswordBtn;
    ImageView back_btn;
    String password;
    private CustomProgress mCustomProgress = CustomProgress.getInstance();
    private TextInputLayout current_password_input_et, new_password_input_et, confirm_password_input_et;
    private EditText currentPasswordET, newPasswordET, confirmPasswordET;
    private int havePassword = 0;
    private AuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initViews();
        getData();


    }


    private void initViews() {


        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);


        currentPasswordET = findViewById(R.id.currentPasswordET);
        newPasswordET = findViewById(R.id.newPasswordET);
        confirmPasswordET = findViewById(R.id.confirmPasswordET);

        current_password_input_et = findViewById(R.id.current_password_input_et);
        new_password_input_et = findViewById(R.id.new_password_input_et);
        confirm_password_input_et = findViewById(R.id.confirm_password_input_et);

        savePasswordBtn = findViewById(R.id.savePasswordBtn);
        back_btn = findViewById(R.id.back_btn);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        savePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        handelTextFields();

    }

    private void getData() {
        mCustomProgress.showProgress(this, "Loading...", false);

        mUsersRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String fullName = "";
                    String email = "";

                    userData = snapshot.getValue(UserDataModel.class);
                    fullName = userData.getFullName();


                    try {
                        password = AESCrypt.decrypt(userData.getPassword());
                    } catch (Exception e) {
                        password = userData.getPassword();
                        Log.e(TAG, "onDataChange: " + e.getMessage());
                    }


                    if (password.equals("")) havePassword = 0;
                    else havePassword = 1;

                    if (havePassword == 0) {
                        currentPasswordET.setVisibility(View.GONE);
                    }


                }
                mCustomProgress.hideProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    boolean validate() {
        if (currentPasswordET.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Your Current Password", Toast.LENGTH_SHORT).show();
            currentPasswordET.setError("Password Enter Your Password");
            return false;
        } else if (newPasswordET.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            newPasswordET.setError("Password Enter New Password");
            return false;
        } else if (confirmPasswordET.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Repeat Your Password", Toast.LENGTH_SHORT).show();
            confirmPasswordET.setError("Please Repeat You Password");
            return false;
        } else if (!newPasswordET.getText().toString().equals(confirmPasswordET.getText().toString())) {
            newPasswordET.setError("Password Don't Match");
            return false;
        }
        return true;
    }


    private void updateData() {

        final String newPassword = newPasswordET.getText().toString();
        password = newPassword;
        String oldPassword = currentPasswordET.getText().toString();


        if (validate()) {

            if (havePassword == 0 && !(newPasswordET.getText().toString().equals(""))) {
                LoginWithGoogle();
            } else if (havePassword == 1 && !(oldPassword.equals("")) && !(newPassword.equals(""))) {
                final String email = user.getEmail();
                credential = EmailAuthProvider.getCredential(email, oldPassword);

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            updatePassword(newPassword);

                        } else {
                            String message = task.getException().toString();
                            Toast.makeText(ChangePasswordActivity.this, "There was an error ", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "onComplete: Error Occurred On Changing Password " + message);
                        }
                    }
                });

            }

        }
    }


    private void LoginWithGoogle() {
        Log.e(TAG, "LoginWithGoogle: ");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void updatePassword(final String newPassword) {

        String passwordEncrypted;
        try {
            passwordEncrypted = AESCrypt.encrypt(newPassword);
        } catch (Exception e) {
            passwordEncrypted = newPassword;
            Log.e(TAG, "updatePassword: " + e.getMessage());
        }


        final String finalPasswordEncrypted = passwordEncrypted;
        user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mUsersRef.child("password").setValue(finalPasswordEncrypted);
                    Toast.makeText(ChangePasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    Log.e(TAG, "onComplete: Change Password Successfully");
                } else {
                    Log.e(TAG, "onComplete: Failed To change Password ");
                }
            }
        });

    }


    private void handelTextFields() {


        currentPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (input.length() < 8) {
                    current_password_input_et.setError("Short Password! Make it Longer!");
                } else if (input.length() > 10) {
                    current_password_input_et.setError("Long Password! Make it Shorter!");
                } else {
                    current_password_input_et.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        newPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (input.length() < 8) {
                    new_password_input_et.setError("Short Password! Make it Longer!");
                } else if (input.length() > 10) {
                    new_password_input_et.setError("Long Password! Make it Shorter!");
                } else {
                    new_password_input_et.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        confirmPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = String.valueOf(s);
                if (input.length() < 8) {
                    confirm_password_input_et.setError("Short Password! Make it Longer!");
                } else if (input.length() > 10) {
                    confirm_password_input_et.setError("Long Password! Make it Shorter!");
                } else {
                    confirm_password_input_et.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}