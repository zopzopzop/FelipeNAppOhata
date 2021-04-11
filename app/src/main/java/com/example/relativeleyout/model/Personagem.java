package com.example.relativeleyout.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {

    //criei o controtor com Alt+ enter nos nomes das variaveis
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento) {

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;

    }

    public Personagem() {

    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public boolean IdValido() {
        return id > 0;
    }
}
