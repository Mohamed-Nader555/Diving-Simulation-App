package com.ghada.divingsimulation.Splash;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ghada.divingsimulation.Auth.LoginActivity;
import com.ghada.divingsimulation.MainHome.MainHomeActivity;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CheckInternetConnection;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {


    public static final int LOCATION_PICK = 11;
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
                } else {
                    checkPermmisions();
                }
            }
        }, 2000);
    }


    @AfterPermissionGranted(1111)
    private void checkPermmisions() {

        String[] locationPermmsions = new String[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            locationPermmsions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    , Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, locationPermmsions)) {
            checkUser();
            finish();
        } else {
            EasyPermissions.requestPermissions(this, "Location Access"
                    , 1111, locationPermmsions);
        }
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
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivityForResult(intent, LOCATION_PICK);
        finish();
    }

    void sendUserToHome() {
        Intent intent = new Intent(SplashActivity.this, MainHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivityForResult(intent, LOCATION_PICK);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "Permission Needed To Run The App", Toast.LENGTH_LONG).show();
        checkPermmisions();
    }


}