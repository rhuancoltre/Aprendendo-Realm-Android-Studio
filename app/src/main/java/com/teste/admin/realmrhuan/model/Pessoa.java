package com.teste.admin.realmrhuan.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Admin on 13/06/2017.
 */

public class Pessoa extends RealmObject {

    @PrimaryKey
    private int id;
    private String nome;
    private String sobrenome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + "\n Nome: " + getNome() + "\n Sobrenome: " + getSobrenome();
    }

}
