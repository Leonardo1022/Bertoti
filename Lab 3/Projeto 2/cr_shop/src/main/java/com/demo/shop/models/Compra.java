package com.demo.shop.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "com_id")
    private Long id;

    @Column(name = "com_data")
    private LocalDateTime data; // Default definido no banco

    // FKs
    @ManyToOne
    @JoinColumn(name = "us_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<>();

    protected Compra() {}
    public Compra(LocalDateTime data, Usuario usuario, List<Item> itens) {
        this.data = data;
        this.usuario = usuario;
        this.itens = itens;
    }

    public Long getId() {return id;}

    public LocalDateTime getData() {return data;}
    public void setData(LocalDateTime data) {this.data = data;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public List<Item> getItens() {return itens;}
    public void setItens(List<Item> itens) {this.itens = itens;}
}
