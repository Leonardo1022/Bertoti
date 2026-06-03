package com.demo.shop.dto;

import java.math.BigDecimal;
import java.util.List;

public class CompraRequestDTO {
    private Long usuarioId;
    private List<ItemRequestDTO> itens;

    public Long getUsuarioId() {return usuarioId;}
    public void setUsuarioId(Long usuarioId) {this.usuarioId = usuarioId;}

    public List<ItemRequestDTO> getItens() {return itens;}
    public void setItens(List<ItemRequestDTO> itens) {this.itens = itens;}

    public static class ItemRequestDTO {
        private Long amigurumiId;
        private Integer quantidade;
        private BigDecimal preco;

        public Long getAmigurumiId() {return amigurumiId;}
        public void setAmigurumiId(Long amigurumiId) {this.amigurumiId = amigurumiId;}

        public Integer getQuantidade() {return quantidade;}
        public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}

        public BigDecimal getPreco() {return preco;}
        public void setPreco(BigDecimal preco) {this.preco = preco;}
    }
}