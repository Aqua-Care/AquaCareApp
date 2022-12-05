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
    private TextView tvEspeciesAgua;

    private Button btBettaAgua;
    private Button btKinguioAgua;
    private Button btAcaraAgua;

    private DatabaseReference referenciaTemperatura;
    private DatabaseReference referenciaPeixe;


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


        btBettaAgua = findViewById(R.id.btBettaAgua);
        btKinguioAgua = findViewById(R.id.btKinguioAgua);
        btAcaraAgua = findViewById(R.id.btAcaraAgua);

        tvTemperaturaAgua = findViewById(R.id.tvTemperaturaAgua);
        tvInfoAgua = findViewById(R.id.tvInfoAgua);
        tvEspeciesAgua = findViewById(R.id.tvEspeciesAgua);

        referenciaTemperatura = FirebaseDatabase.getInstance().getReference("Aquario")
                .child("Agua");

        referenciaPeixe = FirebaseDatabase.getInstance().getReference("Peixe");


        btBettaAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                referenciaPeixe.child("Especies").setValue("Veil Tail, Delta, Halfmoon...");

                referenciaPeixe.child("TemperaturaMin").setValue(24);
                referenciaPeixe.child("TemperaturaMax").setValue(28);


                referenciaTemperatura.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String temperatura = dataSnapshot.child("Temperatura").getValue().toString();

                        referenciaPeixe.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot1t) {

                                String especies = dataSnapshot1t.child("Especies").getValue().toString();
                                tvEspeciesAgua.setText("Ex:  "+especies);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                                Toast.makeText(activity_agua.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
                            }
                        });

                        int tempBase = Integer.parseInt(temperatura);


                        if (tempBase<=24){

                            tvInfoAgua.setTextColor(Color.parseColor("#00BFFF"));
                            tvInfoAgua.setText("Temperatura abaixo do ideal");
                            Toast.makeText(activity_agua.this, "Regule a temperatura no aquário", Toast.LENGTH_LONG).show();
                        }
                        if (tempBase>=28){

                            tvInfoAgua.setTextColor(Color.parseColor("#B22222"));
                            tvInfoAgua.setText("Temperatura acima do ideal");
                            Toast.makeText(activity_agua.this, "Regule a temperatura no aquário", Toast.LENGTH_LONG).show();
                        }
                        if (tempBase>24 && tempBase<28){

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
        });


        btKinguioAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                referenciaPeixe.child("Especies").setValue("Cabeça de Leão, Pérola, Ryukin...");

                referenciaPeixe.child("TemperaturaMin").setValue(18);
                referenciaPeixe.child("TemperaturaMax").setValue(24);


                referenciaTemperatura.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String temperatura = dataSnapshot.child("Temperatura").getValue().toString();

                        referenciaPeixe.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot1t) {

                                String especies = dataSnapshot1t.child("Especies").getValue().toString();
                                tvEspeciesAgua.setText("Ex:  "+especies);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                                Toast.makeText(activity_agua.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
                            }
                        });

                        int tempBase = Integer.parseInt(temperatura);

                        if (tempBase<=18){

                            tvInfoAgua.setTextColor(Color.parseColor("#00BFFF"));
                            tvInfoAgua.setText("Temperatura abaixo do ideal");
                            Toast.makeText(activity_agua.this, "Regule a temperatura no aquário", Toast.LENGTH_LONG).show();
                        }
                        if (tempBase>=24){

                            tvInfoAgua.setTextColor(Color.parseColor("#B22222"));
                            tvInfoAgua.setText("Temperatura acima do ideal");
                            Toast.makeText(activity_agua.this, "Regule a temperatura no aquário", Toast.LENGTH_LONG).show();
                        }
                        if (tempBase>18 && tempBase<24){

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
        });


        btAcaraAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                referenciaPeixe.child("Especies").setValue("Coridoras, Cascudo, Ramirezi...");

                referenciaPeixe.child("TemperaturaMin").setValue(26);
                referenciaPeixe.child("TemperaturaMax").setValue(30);

                referenciaTemperatura.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String temperatura = dataSnapshot.child("Temperatura").getValue().toString();

                        referenciaPeixe.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot1t) {

                                String especies = dataSnapshot1t.child("Especies").getValue().toString();
                                tvEspeciesAgua.setText("Ex:  "+especies);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                                Toast.makeText(activity_agua.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
                            }
                        });

                        int tempBase = Integer.parseInt(temperatura);

                        if (tempBase<=26){

                            tvInfoAgua.setTextColor(Color.parseColor("#00BFFF"));
                            tvInfoAgua.setText("Temperatura abaixo do ideal");
                            Toast.makeText(activity_agua.this, "Regule a temperatura no aquário", Toast.LENGTH_LONG).show();
                        }
                        if (tempBase>=30){

                            tvInfoAgua.setTextColor(Color.parseColor("#B22222"));
                            tvInfoAgua.setText("Temperatura acima do ideal");
                            Toast.makeText(activity_agua.this, "Regule a temperatura no aquário", Toast.LENGTH_LONG).show();
                        }
                        if (tempBase>26 && tempBase<30){

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
        });


        referenciaTemperatura.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String temperatura = dataSnapshot.child("Temperatura").getValue().toString();

                tvTemperaturaAgua.setText(temperatura);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(activity_agua.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
            }
        });
    }
}