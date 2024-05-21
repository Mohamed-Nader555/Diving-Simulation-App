package com.ghada.divingsimulation.MainHome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ghada.divingsimulation.Chart.ChartFragment;
import com.ghada.divingsimulation.Dive.DiveFragment;
import com.ghada.divingsimulation.Emergency.EmergencyFragment;
import com.ghada.divingsimulation.Home.HomeFragment;
import com.ghada.divingsimulation.Profile.ProfileFragment;
import com.ghada.divingsimulation.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainHomeActivity extends AppCompatActivity {

    FloatingActionButton fab;

    DrawerLayout drawerLayout;

    BottomNavigationView bottomNavigationView;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);



        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        replaceFragment(new HomeFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.chart:
//
//                    CategoryHomeFragment categoryHomeFragment = new CategoryHomeFragment();
//                    Bundle argsCategory = new Bundle();
//                    argsCategory.putSerializable("category", (Serializable) categories);
//                    categoryHomeFragment.setArguments(argsCategory);
                    replaceFragment(new ChartFragment());

                    break;
                case R.id.emergency:

//                    AreaHomeFragment areaHomeFragment = new AreaHomeFragment();
//                    Bundle argsArea = new Bundle();
//                    argsArea.putSerializable("meal", (Serializable) areas);
//                    areaHomeFragment.setArguments(argsArea);
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
//                Intent intent = new Intent(MainHome.this, SearchActivity.class);
//                startActivity(intent);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.nav_settings:
                        // Handle settings menu item click
                        break;
                    case R.id.nav_share:
                        // Handle share menu item click
                        break;
                    case R.id.nav_about:
                        // Handle about menu item click
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
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


}