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

public class CadastroActivity extends AppCompatActivity {

    private Button btCadastrarCadastro;
    private Button btLogarCadastro;
    private EditText etUsuarioCadastro;
    private EditText etEmailCadastro;
    private EditText etTelefoneCadastro;
    private EditText etSenhaCadastro;
    private EditText etRepetirSenhaCadastro;
    private FirebaseAuth mAuth;
    private Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btCadastrarCadastro = findViewById(R.id.btCadastrarCadastro);
        btLogarCadastro = findViewById(R.id.btLogarCadastro);
        etUsuarioCadastro = findViewById(R.id.etUsuarioCadastro);
        etEmailCadastro = findViewById(R.id.etEmailCadastro);
        etTelefoneCadastro = findViewById(R.id.etTelefoneCadastro);
        etSenhaCadastro = findViewById(R.id.etSenhaCadastro);
        etRepetirSenhaCadastro = findViewById(R.id.etRepetirSenhaCadastro);
        mAuth = FirebaseAuth.getInstance();


        btCadastrarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                capturarDados();
                criarLogin();
            }
        });

        btLogarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TelaLogin();
            }
        });
    }


    private void TelaLogin() {

        startActivity(new Intent(this, LoginActivity.class));
    }


    private void criarLogin() {

        mAuth.createUserWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            FirebaseUser user = mAuth.getCurrentUser();
                            u.setId(user.getUid());
                            u.salvarDados();

                            Toast.makeText(CadastroActivity.this, "Login criado com sucesso", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                        }
                        else {

                            Toast.makeText(CadastroActivity.this, "Error ao criar um login", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void capturarDados() {

        if (etUsuarioCadastro.getText().toString() == "" || etEmailCadastro.getText().toString() == "" || etSenhaCadastro.getText().toString() == "" || etTelefoneCadastro.getText().toString() == ""){

            Toast.makeText(CadastroActivity.this, "Você deve preencher todos os dados", Toast.LENGTH_LONG).show();
        }
        /*if (etSenhaCadastro.getText().toString() != etRepetirSenhaCadastro.getText().toString()){

            Toast.makeText(CadastroActivity.this, "Senha não compatível", Toast.LENGTH_LONG).show();
        }*/
        else {

            u = new Usuario();

            u.setUsuario(etUsuarioCadastro.getText().toString());
            u.setEmail(etEmailCadastro.getText().toString());
            u.setSenha(etSenhaCadastro.getText().toString());
            u.setTelefone(etTelefoneCadastro.getText().toString());
        }
    }
}
