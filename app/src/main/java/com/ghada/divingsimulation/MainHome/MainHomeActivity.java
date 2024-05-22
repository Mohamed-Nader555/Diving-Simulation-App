package com.ghada.divingsimulation.MainHome;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ghada.divingsimulation.Chart.ChartFragment;
import com.ghada.divingsimulation.Dive.DiveFragment;
import com.ghada.divingsimulation.Emergency.EmergencyFragment;
import com.ghada.divingsimulation.Home.HomeFragment;
import com.ghada.divingsimulation.Profile.ProfileFragment;
import com.ghada.divingsimulation.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainHomeActivity extends AppCompatActivity {

    FloatingActionButton fab;

    BottomNavigationView bottomNavigationView;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
        }

        replaceFragment(new HomeFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.chart:
                    replaceFragment(new ChartFragment());
                    break;
                case R.id.emergency:
                    replaceFragment(new EmergencyFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.dive:
                    replaceFragment(new DiveFragment());
                    break;
            }

            return true;
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new HomeFragment());
            }
        });


    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.frame_layout);

        if (currentFragment instanceof HomeFragment) {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            }

            backPressedTime = System.currentTimeMillis();
        } else {
            replaceFragment(new HomeFragment());
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }


}