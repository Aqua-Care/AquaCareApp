package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aquacareapp.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button btLogarLogin;
    private Button btCadastrarLogin;
    private EditText etEmailLogin;
    private EditText etSenhaLogin;
    private FirebaseAuth mAuth;
    private Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogarLogin = findViewById(R.id.btLogarLogin);
        btCadastrarLogin = findViewById(R.id.btCadastrarLogin);
        etEmailLogin = findViewById(R.id.etEmailLogin);
        etSenhaLogin = findViewById(R.id.etSenhaLogin);
        mAuth = FirebaseAuth.getInstance();


        btLogarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                receberDados();
                Logar();
            }
        });

        btCadastrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TelaCadastro();
            }
        });
    }


    private void TelaCadastro() {

        startActivity(new Intent(this, CadastroActivity.class));
    }


    private void Logar() {

        mAuth.signInWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                        else {

                            Toast.makeText(LoginActivity.this, "Falha ao logar", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                        }

                    }
                });
    }


    private void receberDados() {

        u = new Usuario();

        u.setEmail(etEmailLogin.getText().toString());
        u.setSenha(etSenhaLogin.getText().toString());
    }
}
