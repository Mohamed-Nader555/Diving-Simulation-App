package com.ghada.divingsimulation.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Splash.SplashActivity;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {

    //get the provider, so if it phoneNumber or gmail, hide the change password button
    //firebase.auth().currentUser.providerData[0].providerId

    final String TAG = "EditProfileActivity";
    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    GoogleSignInClient mGoogleSignInClient;


    private CustomProgress mCustomProgress = CustomProgress.getInstance();

    private EditText fullNameEditText, emailEditText;
    Button changePasswordBtn, logoutBtn;
    ImageView back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initViews();
        checkProvider();
        getData();

    }


    private void initViews() {


        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);


        fullNameEditText = findViewById(R.id.full_name_et);
        emailEditText = findViewById(R.id.email_et);
        fullNameEditText.setEnabled(false);
        emailEditText.setEnabled(false);
        logoutBtn = findViewById(R.id.logoutBtn);
        changePasswordBtn = findViewById(R.id.changePasswordBtn);
        back_btn = findViewById(R.id.back_btn);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, ChangePasswordActivity.class));
            }
        });


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


    }

    private void checkProvider() {
        boolean isEmailProvider = false;

        for (UserInfo userInfo : user.getProviderData()) {
            String providerId = userInfo.getProviderId();
            if (providerId.equals("password")) {
                isEmailProvider = true;
                break;
            }
        }

        if (isEmailProvider) {
            changePasswordBtn.setVisibility(View.VISIBLE);
        } else {
            changePasswordBtn.setVisibility(View.GONE);
        }
    }

    private void logout() {
        mGoogleSignInClient.signOut();
        mAuth.signOut();
        Intent userLogout = new Intent(EditProfileActivity.this, SplashActivity.class);
        userLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        userLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(userLogout);
        finish();
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
                    email = userData.getEmail();
                    fullNameEditText.setText(fullName);
                    emailEditText.setText(email);
                }
                mCustomProgress.hideProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}