package com.ghada.divingsimulation.Dive.LogBook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ghada.divingsimulation.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LogBookActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LogBookAdapter adapter;
    FloatingActionButton addButton;
    ImageView back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book);

        initView();

    }

    private void initView() {


        addButton = findViewById(R.id.fab_add_logbook);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LogBookActivity.this, AddNewLogBookActivity.class);
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
//        recyclerView = findViewById(R.id.logbook_list_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new LogBookAdapter();
//        recyclerView.setAdapter(adapter);
    }

}