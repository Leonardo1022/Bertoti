package com.demo.shop.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cor")
public class Cor {
    @Id
    @Column(name = "cor_codigo")
    private String codigo; //Código HEX

    @Column(name = "cor_nome", nullable = false)
    private String nome;

    @Column(name = "cor_foto", unique = true)
    private String foto;

    // FKs
    // - AmCor
    @ManyToMany(mappedBy = "cores")
    private List<Amigurumi> amigurumis = new ArrayList<>();

    // - CorAc
    @ManyToMany
    @JoinTable(
            name = "corac",
            joinColumns = @JoinColumn(name = "cor_codigo"),
            inverseJoinColumns = @JoinColumn(name = "ac_id")
    )
    private List<Acessorio> acessorios = new ArrayList<>();

    // - ItAcCor
    @OneToMany(mappedBy = "cor")
    private List<ItAcCor> itAcCors = new ArrayList<>();

    protected Cor() {}
    public Cor(String codigo, String nome, String foto) {
        this.codigo = codigo;
        this.nome = nome;
        this.foto = foto;
    }

    public String getCodigo() {return codigo;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getFoto() {return foto;}
    public void setFoto(String foto) {this.foto = foto;}
}
