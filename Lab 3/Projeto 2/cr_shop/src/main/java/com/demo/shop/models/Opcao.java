package com.demo.shop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Opcao")
public class Opcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_id")
    private Long id;

    @Column(name = "op_nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customizacao customizacao;

    protected Opcao() {}
    public Opcao(String nome, Customizacao customizacao) {
        this.nome = nome;
        this.customizacao = customizacao;
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

    public Customizacao getCustomizacao() {
        return customizacao;
    }
    public void setCustomizacao(Customizacao customizacao) {
        this.customizacao = customizacao;
    }
}
