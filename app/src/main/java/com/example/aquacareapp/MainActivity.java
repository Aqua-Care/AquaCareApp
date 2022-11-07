package com.example.aquacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btLuzMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLuzMain = findViewById(R.id.btLuzMain);

        btLuzMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TelaLuz();
            }
        });
    }

    private void TelaLuz() {

        startActivity(new Intent(this, activity_luz.class));
    }
}