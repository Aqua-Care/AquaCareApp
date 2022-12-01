package com.example.aquacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_luz extends AppCompatActivity {

    private Switch switchLamp;
    private ImageView lamp;
    private Button btVoltarLuz;

    private DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luz);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        btVoltarLuz = findViewById(R.id.btVoltarLuz);

        btVoltarLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(activity_luz.this, MainActivity.class));
            }
        });


        lamp = findViewById(R.id.imageView4);
        switchLamp = findViewById(R.id.switch_lamp);

        referencia = FirebaseDatabase.getInstance().getReference("Aquario")
                .child("Luz");


        switchLamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    lamp.setEnabled(true);
                    switchLamp.setText("Ligado");

                    referencia.child("LED").setValue(1);

                    referencia.child("Estado").setValue("Ligado");
                }
                else {

                    lamp.setEnabled(false);
                    switchLamp.setText("Desligado");

                    referencia.child("LED").setValue(0);

                    referencia.child("Estado").setValue("Desligado");
                }
            }
        });

    }
}
