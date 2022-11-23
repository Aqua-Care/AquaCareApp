package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquacareapp.modelo.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btLuzMain;
    private Button btAguaMain;
    private Button btRacaoMain;
    private Button btPerfilMain;

    private TextView tvUserMain;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String usuarioID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        btLuzMain = findViewById(R.id.btLuzMain);
        btAguaMain = findViewById(R.id.btAguaMain);
        btRacaoMain = findViewById(R.id.btRacaoMain);
        btPerfilMain = findViewById(R.id.btPerfilMain);

        btLuzMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, activity_luz.class));
            }
        });

        btRacaoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_racao.class));
            }
        });

        btAguaMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activity_agua.class));
            }
        });

        btPerfilMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, PerfilActivity.class));
            }
        });


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Usuarios");
        usuarioID = user.getUid();

        tvUserMain =findViewById(R.id.tvUserMain);

        reference.child(usuarioID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuarioperfil = snapshot.getValue(Usuario.class);

                if (usuarioperfil != null) {

                    String usuario = usuarioperfil.usuario;

                    tvUserMain.setText(usuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
            }
        });
    }
}