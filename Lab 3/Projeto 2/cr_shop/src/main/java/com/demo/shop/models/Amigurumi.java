package com.demo.shop.models;

import jakarta.persistence.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Amigurumi")
public class Amigurumi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "am_id")
    private Long id;

    @Column(name = "am_nome", nullable = false)
    private String nome;

    @Column(name = "am_foto", unique = true)
    private String foto;

    @Column(name = "am_preco", nullable = false)
    private BigDecimal preco;

    // FKs
    // - Customizacao
    @OneToMany(mappedBy = "amigurumi", cascade = CascadeType.ALL)
    private List<Customizacao> customizacoes = new ArrayList<>();

    // - Item
    @OneToMany(mappedBy = "amigurumi", cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<>();

    // - AmCor
    @ManyToMany
    @JoinTable(
            name = "AmCor",         // nome da tabela intermediária
            joinColumns = @JoinColumn(name = "am_id"),
            inverseJoinColumns = @JoinColumn(name = "cor_codigo")
    )
    private List<Cor> cores = new ArrayList<>();

    // - AmAc
    @ManyToMany
    @JoinTable(
            name = "AmAc",
            joinColumns = @JoinColumn(name = "am_id"),
            inverseJoinColumns = @JoinColumn(name = "ac_id")
    )
    private List<Acessorio> acessorios = new ArrayList<>();

    protected Amigurumi() {}
    public Amigurumi(
            String nome, String foto, BigDecimal preco,
            List<Cor> cores, List<Acessorio> acessorios
    ) {
        this.nome = nome;
        this.foto = foto;
        this.preco = preco;
        this.cores = cores;
        this.acessorios = acessorios;
    }

    public Long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getFoto() {return foto;}
    public void setFoto(String foto) {this.foto = foto;}

    public BigDecimal getPreco() {return preco;}
    public void setPreco(BigDecimal preco) {this.preco = preco;}

    public List<Customizacao> getCustomizacoes() {return customizacoes;}
    public void setCustomizacoes(List<Customizacao> customizacoes) {this.customizacoes = customizacoes;}

    public List<Item> getItens() {return itens;}
    public void setItens(List<Item> itens) {this.itens = itens;}

    public List<Cor> getCores() {return cores;}
    public void setCores(List<Cor> cores) {this.cores = cores;}

    public List<Acessorio> getAcessorios() {return acessorios;}
    public void setAcessorios(List<Acessorio> acessorios) {this.acessorios = acessorios;}
}