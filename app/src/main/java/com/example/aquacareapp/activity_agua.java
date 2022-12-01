package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
    private Button btVoltarAgua;

    //private String temperatura;

    private DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agua);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        btVoltarAgua = findViewById(R.id.btVoltarAgua);

        btVoltarAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(activity_agua.this, MainActivity.class));
            }
        });


        tvTemperaturaAgua = findViewById(R.id.tvTemperaturaAgua);
        tvInfoAgua = findViewById(R.id.tvInfoAgua);


        referencia = FirebaseDatabase.getInstance().getReference("Aquario")
                .child("Agua");

        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String temperatura = dataSnapshot.child("Temperatura").getValue().toString();

                tvTemperaturaAgua.setText(temperatura);

                int tempBase = Integer.parseInt(temperatura);

                if (tempBase<=21){

                    tvInfoAgua.setTextColor(Color.parseColor("#00BFFF"));
                    tvInfoAgua.setText("Temperatura abaixo do ideal");
                    Toast.makeText(activity_agua.this, "Regule a temperatura no aquário", Toast.LENGTH_LONG).show();
                }
                if (tempBase>=27){

                    tvInfoAgua.setTextColor(Color.parseColor("#B22222"));
                    tvInfoAgua.setText("Temperatura acima do ideal");
                    Toast.makeText(activity_agua.this, "Regule a temperatura no aquário", Toast.LENGTH_LONG).show();
                }
                if (tempBase>21 && tempBase<27){

                    tvInfoAgua.setTextColor(Color.parseColor("#7CFC00"));
                    tvInfoAgua.setText("Temperatura ideal");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(activity_agua.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
            }
        });
    }
}