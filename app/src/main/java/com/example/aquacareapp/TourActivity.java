package com.example.aquacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TourActivity extends AppCompatActivity {

    private Button btLogarTour;
    private Button btCadastrarTour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        btLogarTour = findViewById(R.id.btLogarTour);
        btCadastrarTour = findViewById(R.id.btCadastrarTour);

        btLogarTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaLogin();
            }
        });

        btCadastrarTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaCadastro();
            }
        });
    }

    private void TelaCadastro() {

        startActivity(new Intent(this, CadastroActivity.class));
    }

    private void TelaLogin() {

        startActivity(new Intent(this, LoginActivity.class));
    }
}
