package com.example.aquacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class activity_luz extends AppCompatActivity {

    private Button btOnLuz;
    private Button btOffLuz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luz);

        btOnLuz = findViewById(R.id.btOnLuz);
        btOffLuz = findViewById(R.id.btOffLuz);

        /*final DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();


        btOnLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //firebase.child("Usuarios").child("Luz").child("Estado").setValue("Ligado");
                //firebase.child("Usuarios").child("Luz").child("LED").setValue(1);
                firebase.child("Luz").child("Estado").setValue("Ligado");
                firebase.child("Luz").child("LED").setValue(1);
            }
        });

        btOffLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //firebase.child("Usuarios").child("Luz").child("Estado").setValue("Desligado");
                //firebase.child("Usuarios").child("Luz").child("LED").setValue(0);
                firebase.child("Luz").child("Estado").setValue("Desligado");
                firebase.child("Luz").child("LED").setValue(0);
            }
        });*/
    }
}
