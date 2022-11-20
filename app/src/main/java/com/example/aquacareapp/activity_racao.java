package com.example.aquacareapp;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class activity_racao extends AppCompatActivity {

    private Button btAlimentoRacao;
    private TextView tvDataRacao;

    private DatabaseReference referencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racao);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        btAlimentoRacao = findViewById(R.id.btAlimentoRacao);
        tvDataRacao = findViewById(R.id.tvDataRacao);


        referencia = FirebaseDatabase.getInstance().getReference("Usuarios")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Racao");



        btAlimentoRacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                referencia.child("Alimentar").setValue(1);

                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Toast.makeText(activity_racao.this, "Uma porção de alimento foi oferecida", Toast.LENGTH_LONG).show();
                referencia.child("Alimentar").setValue(0);

                SimpleDateFormat formatoDataHora = new SimpleDateFormat("hh:mm:ss dd/M/yyyy");
                String data = formatoDataHora.format(new Date());
                tvDataRacao.setText("Ultima Porção: " + data);
            }
        });
    }
}
