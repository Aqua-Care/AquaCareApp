package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_agua extends AppCompatActivity {

    private TextView tvTemperaturaAgua;
    private TextView tvInfoAgua;

    //private String temperatura;

    private DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agua);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();


        tvTemperaturaAgua = findViewById(R.id.tvTemperaturaAgua);
        tvInfoAgua = findViewById(R.id.tvInfoAgua);


        referencia = FirebaseDatabase.getInstance().getReference("Usuarios")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Agua");

        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String temperatura = dataSnapshot.child("Temperatura").getValue().toString();

                tvTemperaturaAgua.setText(temperatura);

                int tempBase = Integer.parseInt(temperatura);

                if (tempBase<10){

                    tvInfoAgua.setText("Frio");
                }
                if (tempBase>10){

                    tvInfoAgua.setText("Quente");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(activity_agua.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
            }
        });
    }
}