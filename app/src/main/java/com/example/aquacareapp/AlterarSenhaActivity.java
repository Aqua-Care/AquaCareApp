package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class AlterarSenhaActivity extends AppCompatActivity {

    private EditText etEmailAlterarSenha;
    private Button btAlterarSenhaAlterarSenha;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);

        etEmailAlterarSenha = findViewById(R.id.etEmailAlterarSenha);
        btAlterarSenhaAlterarSenha = findViewById(R.id.btAlterarSenhaAlterarSenha);

        mAuth = FirebaseAuth.getInstance();


        btAlterarSenhaAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TrocarSenha();
            }
        });
    }


    private void TrocarSenha() {

        String emailAlterarSenha = etEmailAlterarSenha.getText().toString().trim();


        if (emailAlterarSenha.isEmpty()){

            etEmailAlterarSenha.setError("Preencha o campo de Usuário!");
            etEmailAlterarSenha.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAlterarSenha).matches()){

            etEmailAlterarSenha.setError("Email invalido!");
            etEmailAlterarSenha.requestFocus();
            return;
        }


        mAuth.sendPasswordResetEmail(emailAlterarSenha).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    Toast.makeText(AlterarSenhaActivity.this, "Chegue seu email para alteração", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AlterarSenhaActivity.this, LoginActivity.class));
                }
                else {

                    Toast.makeText(AlterarSenhaActivity.this, "Falha ao encontrar o email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}