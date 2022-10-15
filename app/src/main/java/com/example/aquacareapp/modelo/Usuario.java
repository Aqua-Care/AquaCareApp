package com.example.aquacareapp.modelo;

import com.example.aquacareapp.config.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Usuario {

    private String id;
    private String usuario;
    private String email;
    private String senha;
    private String telefone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public void salvarDados() {

        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("Usuarios").child(this.id).setValue(this);
    }
}
