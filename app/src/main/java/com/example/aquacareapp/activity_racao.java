package com.example.aquacareapp;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class activity_racao extends AppCompatActivity {

    private Button btAlimentoRacao;
    private TextView tvDataRacao;
    private Button btVoltarRacao;

    private DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racao);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        btAlimentoRacao = findViewById(R.id.btAlimentoRacao);
        tvDataRacao = findViewById(R.id.tvDataRacao);
        btVoltarRacao = findViewById(R.id.btVoltarRacao);

        btVoltarRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(activity_racao.this, MainActivity.class));
            }
        });


        referencia = FirebaseDatabase.getInstance().getReference("Aquario")
                .child("Racao");


        btAlimentoRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                referencia.child("Alimentar").setValue(1);
                Toast.makeText(activity_racao.this, "Uma porção de alimento foi oferecida", Toast.LENGTH_LONG).show();

                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                referencia.child("Alimentar").setValue(0);

                SimpleDateFormat formatoDataHora = new SimpleDateFormat("hh:mm:ss dd/M/yyyy");
                String data = formatoDataHora.format(new Date());
                referencia.child("DataRacao").setValue(data);

                try {
                    btAlimentoRacao.setClickable(false);
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                btAlimentoRacao.setClickable(true);

            }
        });


        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String dataBd = dataSnapshot.child("DataRacao").getValue().toString();

                tvDataRacao.setText("Ultima Porção: " + dataBd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(activity_racao.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
