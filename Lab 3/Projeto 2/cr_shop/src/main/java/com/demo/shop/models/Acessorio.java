package com.demo.shop.models;

import jakarta.persistence.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Acessorio")
public class Acessorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id")
    private final int id;

    @Column(name = "ac_nome", nullable = false)
    private String nome;

    @Column(name = "ac_foto", unique = true)
    private File foto;

    @Column(name = "ac_preco", nullable = false)
    private float preco;

    //FKs

    private List<Cor> cores = new ArrayList<>();

    public Acessorio(int id, String nome, File foto, float preco) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
        this.preco = preco;
    }

    public int getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public File getFoto() {return foto;}
    public void setFoto(File foto) {this.foto = foto;}

    public float getPreco() {return preco;}
    public void setPreco(float preco) {this.preco = preco;}

    public List<Cor> getCores() {return cores;}
    public void setCores(List<Cor> cores) {this.cores = cores;}
}
