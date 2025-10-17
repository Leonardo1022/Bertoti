package Mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Prateleira {
    int secao;
    String descricao;
    List<Produto> produtos;

    public Prateleira(int secao, String descricao, List<Produto> produtos) {
        this.produtos = produtos;
        this.secao = secao;
        this.descricao = descricao;
    }

    public int getSecao() {
        return secao;
    }
    public void setSecao(int secao) {
        this.secao = secao;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    Mercado mercado = new Mercado();

    public static void limparTabela() {
        String sql = "DELETE FROM prateleiras";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:mercado.db");
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela limpa com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao limpar tabela: " + e.getMessage());
        }
    }
}
