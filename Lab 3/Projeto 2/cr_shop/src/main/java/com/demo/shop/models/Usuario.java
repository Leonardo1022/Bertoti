package com.demo.shop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Long id;

    @Column(name = "us_nome", nullable = false)
    private String nome;

    @Column(name = "us_senha", nullable = false)
    private String senha;

    @Column(name = "us_email", nullable = false, unique = true)
    private String email;

    public Usuario(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public Long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
