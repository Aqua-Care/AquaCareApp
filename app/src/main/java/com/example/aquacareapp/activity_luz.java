package com.example.aquacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_luz extends AppCompatActivity {

    private Button btOnLuz;
    private Button btOffLuz;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luz);

        btOnLuz = findViewById(R.id.btOnLuz);
        btOffLuz = findViewById(R.id.btOffLuz);

        mAuth = FirebaseAuth.getInstance();


        btOnLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference("Usuarios")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("LED").setValue(1);
            }
        });

        btOffLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference("Usuarios")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("LED").setValue(0);
            }
        });
    }
}
