package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
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

public class PerfilActivity extends AppCompatActivity {

    private TextView tvUserPerfil;
    private TextView tvEmailPerfil;
    private TextView tvTelefonePerfil;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        tvUserPerfil = findViewById(R.id.tvUserPerfil);
        tvEmailPerfil = findViewById(R.id.tvEmailPerfil);
        tvTelefonePerfil = findViewById(R.id.tvTelefonePerfil);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Usuarios");
        usuarioID = user.getUid();

        reference.child(usuarioID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuarioperfil = snapshot.getValue(Usuario.class);

                if (usuarioperfil != null){

                    String usuario = usuarioperfil.usuario;
                    String telefoneusuario = usuarioperfil.telefone;
                    String emailusuario = usuarioperfil.email;

                    tvUserPerfil.setText(usuario);
                    tvEmailPerfil.setText(emailusuario);
                    tvTelefonePerfil.setText(telefoneusuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(PerfilActivity.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
            }
        });


    }
}