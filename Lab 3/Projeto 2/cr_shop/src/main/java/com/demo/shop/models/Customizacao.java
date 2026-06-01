package com.demo.shop.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customizacao")
public class Customizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long id;

    @Column(name = "cust_nome", nullable = false)
    private String nome;

    // FKs
    @ManyToOne
    @JoinColumn(name = "am_id", nullable = false)
    private Amigurumi amigurumi;

    @OneToMany(mappedBy = "customizacao", cascade = CascadeType.ALL)
    private List<Opcao> opcoes = new ArrayList<>();

    protected Customizacao() {}
    public Customizacao(String nome, Amigurumi amigurumi, List<Opcao> opcoes) {
        this.nome = nome;
        this.amigurumi = amigurumi;
        this.opcoes = opcoes;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Amigurumi getAmigurumi() {
        return amigurumi;
    }
    public void setAmigurumi(Amigurumi amigurumi) {
        this.amigurumi = amigurumi;
    }

    public List<Opcao> getOpcoes() {
        return opcoes;
    }
    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes = opcoes;
    }
}
