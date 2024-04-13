package com.ghada.divingsimulation.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.ghada.divingsimulation.Auth.LoginActivity;
import com.ghada.divingsimulation.MainHome.MainHomeActivity;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CheckInternetConnection;
import com.ghada.divingsimulation.Utils.Utils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {



    private CheckInternetConnection cd;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        cd = new CheckInternetConnection(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        splashTimer();
    }

    private void splashTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!cd.isConnected()) {
                    Toast.makeText(SplashActivity.this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
                    sendUserToHome();
                } else {
                    checkUser();
                }
            }
        }, 2000);
    }


    void checkUser() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            sendUserToLogin();
        } else {
            sendUserToHome();
        }
    }

    void sendUserToLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    void sendUserToHome() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

//        Utils.getSpEditor(this).putString(Utils.UserID, firebaseUser.getUid().toString());
//        Utils.getSpEditor(this).putBoolean(Utils.IsLoggedOn, true);
//        Utils.getSpEditor(this).commit();

        Intent intent = new Intent(SplashActivity.this, MainHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }



}