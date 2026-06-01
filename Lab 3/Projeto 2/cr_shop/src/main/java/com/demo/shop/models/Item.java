package com.demo.shop.models;

import jakarta.persistence.*;
// para dinheiro
import java.math.BigDecimal;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "it_id")
    private Long id;

    @Column(name = "it_quantidade")
    private Integer quantidade; // Default definido no banco

    @Column(name = "it_preco", nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "am_id")
    private Amigurumi amigurumi;

    @ManyToOne
    @JoinColumn(name = "com_id")
    private Compra compra;

    protected Item() {}

    public Item(Integer quantidade, BigDecimal preco, Amigurumi amigurumi, Compra compra) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.amigurumi = amigurumi;
        this.compra = compra;
    }

    public Long getId() { return id; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Amigurumi getAmigurumi() { return amigurumi; }
    public void setAmigurumi(Amigurumi amigurumi) { this.amigurumi = amigurumi; }

    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }
}
