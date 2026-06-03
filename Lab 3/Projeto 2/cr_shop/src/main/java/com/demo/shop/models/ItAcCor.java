package com.demo.shop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "itaccor")
public class ItAcCor {
    @EmbeddedId
    private ItAcCorId id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "it_id")
    private Item item;

    @ManyToOne
    @MapsId("acessorioId")
    @JoinColumn(name = "ac_id")
    private Acessorio acessorio;

    @ManyToOne
    @JoinColumn(name = "cor_codigo")
    private Cor cor;
}