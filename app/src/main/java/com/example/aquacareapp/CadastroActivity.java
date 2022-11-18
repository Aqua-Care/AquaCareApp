package com.example.aquacareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquacareapp.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {

    private Button btCadastrarCadastro;
    private TextView btLogarCadastro;
    private EditText etUsuarioCadastro;
    private EditText etEmailCadastro;
    private EditText etTelefoneCadastro;
    private EditText etSenhaCadastro;
    private EditText etRepetirSenhaCadastro;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        btCadastrarCadastro = findViewById(R.id.btCadastrarCadastro);
        btLogarCadastro = findViewById(R.id.textViewLogarCadastro);
        etUsuarioCadastro = findViewById(R.id.etUsuarioCadastro);
        etEmailCadastro = findViewById(R.id.etEmailCadastro);
        etTelefoneCadastro = findViewById(R.id.etTelefoneCadastro);
        etSenhaCadastro = findViewById(R.id.etSenhaCadastro);

        mAuth = FirebaseAuth.getInstance();


        btLogarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TelaLogin();
            }
        });

        btCadastrarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CadastrarUsuario();

            }
        });
    }

    private void CadastrarUsuario() {

        String usuarioCadastro = etUsuarioCadastro.getText().toString().trim();
        String emailCadastro = etEmailCadastro.getText().toString().trim();
        String telefoneCadastro = etTelefoneCadastro.getText().toString().trim();
        String senhaCadastro = etSenhaCadastro.getText().toString().trim();
        String repetir_senhaCadastro = etRepetirSenhaCadastro.getText().toString().trim();


        if (usuarioCadastro.isEmpty()){

            etUsuarioCadastro.setError("Crie um usuário!");
            etUsuarioCadastro.requestFocus();
            return;
        }

        if (emailCadastro.isEmpty()){

            etEmailCadastro.setError("Preencha o campo de Email!");
            etEmailCadastro.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailCadastro).matches()){

            etEmailCadastro.setError("Email invalido!");
            etEmailCadastro.requestFocus();
            return;
        }

        if (telefoneCadastro.isEmpty()){

            etTelefoneCadastro.setError("Preencha o campo de Telefone!");
            etTelefoneCadastro.requestFocus();
            return;
        }

        if (senhaCadastro.isEmpty()){

            etSenhaCadastro.setError("Preencha o campo de Senha!");
            etSenhaCadastro.requestFocus();
            return;
        }

        if (repetir_senhaCadastro.isEmpty()){

            etRepetirSenhaCadastro.setError("Preencha o campo de Repetir Senha!");
            etRepetirSenhaCadastro.requestFocus();
            return;
        }

        if (senhaCadastro.length() < 6){

            etSenhaCadastro.setError("Digite uma senha com mais de 6 dígitos!");
            etSenhaCadastro.requestFocus();
            return;
        }

        /*if (senhaCadastro == repetir_senhaCadastro) {

            etRepetirSenhaCadastro.setError("Senhas não coincidem!");
            etRepetirSenhaCadastro.requestFocus();
            return;
        }*/


        mAuth.createUserWithEmailAndPassword(emailCadastro, senhaCadastro)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Usuario user = new Usuario(usuarioCadastro, emailCadastro, telefoneCadastro, senhaCadastro);

                            FirebaseDatabase.getInstance().getReference("Usuarios")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){

                                                Toast.makeText(CadastroActivity.this, "Usuário cadastrado", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                                            }
                                            else {

                                                Toast.makeText(CadastroActivity.this, "Falha ao cadastrar", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                            }
                        else {

                            Toast.makeText(CadastroActivity.this, "Falha ao cadastrar", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    private void TelaLogin() {

        startActivity(new Intent(this, LoginActivity.class));
    }
}