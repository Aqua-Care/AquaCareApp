package com.example.aquacareapp;

import androidx.appcompat.app.AppCompatActivity;

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


    private FirebaseAuth mAuth;

    private Switch switchLamp;
    private ImageView lamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luz);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();


        mAuth = FirebaseAuth.getInstance();

        lamp = findViewById(R.id.imageView4);

        switchLamp = findViewById(R.id.switch_lamp);

        switchLamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    lamp.setEnabled(true);
                    switchLamp.setText("Ligado");

                    FirebaseDatabase.getInstance().getReference("Usuarios")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Luz").child("LED").setValue(1);

                    FirebaseDatabase.getInstance().getReference("Usuarios")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Luz").child("Estado").setValue("Ligado");
                }
                else {
                    lamp.setEnabled(false);
                    switchLamp.setText("Desligado");

                    FirebaseDatabase.getInstance().getReference("Usuarios")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Luz").child("LED").setValue(0);

                    FirebaseDatabase.getInstance().getReference("Usuarios")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Luz").child("Estado").setValue("Desligado");
                }
            }
        });

    }
}
