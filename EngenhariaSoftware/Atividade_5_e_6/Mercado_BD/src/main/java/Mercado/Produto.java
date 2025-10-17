package Mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Produto {
    String nome;
    double preco;

    public Produto (String nome, double preco){
        this.preco = preco;
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }



    public static void limparTabela() {
        String sql = "DELETE FROM produtos";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:mercado.db");
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela limpa com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao limpar tabela: " + e.getMessage());
        }
    }
}
