package com.ghada.divingsimulation.Dive.Accident;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccidentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AccidentAdapter adapter;
    FloatingActionButton addButton;
    ImageView back_btn;
    TextView notFoundHintTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident);

        initView();

    }

    private void initView() {

        notFoundHintTV = findViewById(R.id.not_found_hint);



        addButton = findViewById(R.id.fab_add_accident);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccidentActivity.this, AddAccidentActivity.class);
                startActivity(intent);

            }
        });


        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//
//        recyclerView = findViewById(R.id.accident_list_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new LogBookAdapter();
//        recyclerView.setAdapter(adapter);
    }

}