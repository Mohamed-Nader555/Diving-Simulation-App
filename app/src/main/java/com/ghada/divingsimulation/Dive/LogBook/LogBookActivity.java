package com.ghada.divingsimulation.Dive.LogBook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.Dive.Adapters.GenericItemsAdapter;
import com.ghada.divingsimulation.Models.User.LogBook;
import com.ghada.divingsimulation.Models.User.UserDataModel;
import com.ghada.divingsimulation.R;
import com.ghada.divingsimulation.Utils.CustomProgress;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LogBookActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    GenericItemsAdapter adapter;
    FloatingActionButton addButton;
    ImageView back_btn;
    TextView notFoundHintTV;
    String currentUserID;
    FirebaseUser user;
    DatabaseReference mUsersRef;
    FirebaseAuth mAuth;
    UserDataModel userData;
    ArrayList<LogBook> logBooksList = new ArrayList<>();
    private CustomProgress mCustomProgress = CustomProgress.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book);

        initView();

    }

    private void initView() {


        userData = new UserDataModel();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        currentUserID = user.getUid();
        mUsersRef = FirebaseDatabase.getInstance().getReference("Users").child(currentUserID);

        notFoundHintTV = findViewById(R.id.not_found_hint);
        recyclerView = findViewById(R.id.logbook_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(LogBookActivity.this, RecyclerView.VERTICAL, false));

        addButton = findViewById(R.id.fab_add_logbook);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LogBookActivity.this, AddNewLogBookActivity.class);
                startActivity(intent);
                finish();

            }
        });

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
        mUsersRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    logBooksList.clear();
                    userData = snapshot.getValue(UserDataModel.class);
                    if (userData.getLogBook() != null) {
                        logBooksList.addAll(userData.getLogBook());
                    }
                    adapter = new GenericItemsAdapter(LogBookActivity.this, logBooksList, "LogBookType");
                    recyclerView.setAdapter(adapter);
                }
                if (logBooksList.size() == 0) {
                    notFoundHintTV.setVisibility(View.VISIBLE);
                } else {
                    notFoundHintTV.setVisibility(View.GONE);
                }
                mCustomProgress.hideProgress();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled
                mCustomProgress.hideProgress();
            }
        });

    }

}