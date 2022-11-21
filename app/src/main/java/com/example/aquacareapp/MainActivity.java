package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquacareapp.modelo.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    private Button btLuzMain;
    private Button btAguaMain;
    private Button btRacaoMain;

    private TextView tvUser1Main;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String usuarioID;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.item1:{

                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(MainActivity.this, "Saiu", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, TourActivity.class));
                    }
                    case  R.id.item2:{

                        startActivity(new Intent(MainActivity.this, PerfilActivity.class));
                    }
                }

                return false;
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////

        btLuzMain = findViewById(R.id.btLuzMain2);
        btAguaMain = findViewById(R.id.btAguaMain);
        btRacaoMain = findViewById(R.id.btRacaoMain);

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


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Usuarios");
        usuarioID = user.getUid();

        tvUser1Main = findViewById(R.id.tvUsuario1Main);

        reference.child(usuarioID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuarioperfil = snapshot.getValue(Usuario.class);

                if (usuarioperfil != null){

                    String usuario1 = usuarioperfil.usuario;
                    //String usuario2 = usuarioperfil.usuario;
                    //String telefoneusuario = usuarioperfil.telefone;
                    //String emailusuario = usuarioperfil.email;

                    tvUser1Main.setText(usuario1);
                    //tvUser2Main.setText(usuario2);
                    //tvTelefoneMain.setText(telefoneusuario);
                    //tvEmailMain.setText(emailusuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Algo de errado aconteceu!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}