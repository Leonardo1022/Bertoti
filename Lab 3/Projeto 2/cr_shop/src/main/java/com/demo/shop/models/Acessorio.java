package com.demo.shop.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acessorio")
public class Acessorio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ac_id")
    private Long id;

    @Column(name = "ac_nome", nullable = false)
    private String nome;

    @Column(name = "ac_foto", unique = true)
    private String foto;

    @Column(name = "ac_preco", nullable = false)
    private BigDecimal preco;

    //FKs
    @ManyToMany(mappedBy = "acessorios")
    private List<Cor> cores = new ArrayList<>();

    protected Acessorio() {}
    public Acessorio(String nome, String foto, BigDecimal preco) {
        this.nome = nome;
        this.foto = foto;
        this.preco = preco;
    }

    public Long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getFoto() {return foto;}
    public void setFoto(String foto) {this.foto = foto;}

    public BigDecimal getPreco() {return preco;}
    public void setPreco(BigDecimal preco) {this.preco = preco;}

    public List<Cor> getCores() {return cores;}
    public void setCores(List<Cor> cores) {this.cores = cores;}
}
