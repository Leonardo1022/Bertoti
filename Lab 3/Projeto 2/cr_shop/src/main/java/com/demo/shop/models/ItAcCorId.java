package com.demo.shop.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItAcCorId implements Serializable {
    @Column(name = "it_id")
    private Long itemId;

    @Column(name = "ac_id")
    private Long acessorioId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItAcCorId that = (ItAcCorId) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(acessorioId, that.acessorioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, acessorioId);
    }
}