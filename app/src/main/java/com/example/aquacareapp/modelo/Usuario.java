package com.example.aquacareapp.modelo;

public class Usuario {

    public String usuario;
    public String email;
    public String telefone;
    public String senha;


    public Usuario(){

    }

    public Usuario(String usuario, String email, String telefone, String senha){

        this.usuario = usuario;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
}