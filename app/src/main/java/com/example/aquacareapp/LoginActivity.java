package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button btLogarLogin;
    private TextView tvCadastrarLogin;
    private TextView tvAlterarSenhaLogin;
    private EditText etEmailLogin;
    private EditText etSenhaLogin;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        btLogarLogin = findViewById(R.id.btLogarLogin);
        tvCadastrarLogin = findViewById(R.id.tvCadastrarLogin);
        tvAlterarSenhaLogin = findViewById(R.id.tvAlterarSenhaLogin);
        etEmailLogin = findViewById(R.id.etEmailLogin);
        etSenhaLogin = findViewById(R.id.etSenhaLogin);

        mAuth = FirebaseAuth.getInstance();


        tvCadastrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TelaCadastro();
            }
        });

        btLogarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogarUsuario();
            }
        });

        tvAlterarSenhaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TelaAlterarSenha();
            }
        });
    }


    private void TelaAlterarSenha() {

        startActivity(new Intent(this, AlterarSenhaActivity.class));
    }


    private void LogarUsuario() {

        String emailLogin = etEmailLogin.getText().toString().trim();
        String senhaLogin = etSenhaLogin.getText().toString().trim();


        if (emailLogin.isEmpty()){

            etEmailLogin.setError("Preencha o campo de Usuário!");
            etEmailLogin.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailLogin).matches()){

            etEmailLogin.setError("Email invalido!");
            etEmailLogin.requestFocus();
            return;
        }

        if (senhaLogin.isEmpty()){

            etSenhaLogin.setError("Preencha o campo de Senha!");
            etSenhaLogin.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(emailLogin, senhaLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()){

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Cheque seu email para verificar sua conta!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Falha ao logar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void TelaCadastro() {
        startActivity(new Intent(this, CadastroActivity.class));
    }
}