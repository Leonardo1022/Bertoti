package Mercado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mercado {
    private static final String URL = "jdbc:sqlite:mercado.db";

    public Connection conectar() {
        String url = "jdbc:sqlite:C:/Users/Noturno/Downloads/mercado.db"; // Caminho para o BD

        try (
                Connection conn = DriverManager.getConnection(url)
        ) {
            System.out.println("Conexão com o banco de dados foi estabelecida."); //Mensagem se a conexão com o BD foi estabelecida
            return conn;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage()); //Mensagem se não foi
            return null;
        }
    }

    public void criarTabelaProdutos() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos(\n" +
                " nome TEXT PRIMARY KEY, \n" +
                " preco DOUBLE(5, 2) NOT NULL, \n" +
                " prateleiras INTEGER, \n" +
                " FOREIGN KEY (prateleiras) REFERENCES produtos(id) \n" +
                ");";

        try(Connection conn = DriverManager.getConnection(URL)) {
            java.sql.Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabela Criada com sucesso!");
        }
        catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public void criarTabelaPrateleiras() {
        String sql = "CREATE TABLE IF NOT EXISTS prateleiras(\n" +
                " id INTEGER PRIMARY KEY, \n" +
                " descricao TEXT NOT NULL \n" +
                ");";

        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:mercado.db")) {
            java.sql.Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabela Criada com sucesso!");
        }
        catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public List<Produto> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            List<Produto> produtos = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            System.out.println("=== Lista de produtos no banco ===");
            while (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");

                System.out.println("Nome: " + nome + " | Preço: " + preco);
                produtos.add(new Produto(nome, preco));
            }
            return produtos;
//            } else if (Objects.equals(tabela, "prateleiras")) {
//                System.out.println("=== Lista de prateleiras no banco ===");
//                while (rs.next()) {
//                    int secao = rs.getInt("secao");
//                    String descricao = rs.getString("descricao");
//
//                    System.out.println("Seção: " + secao + " | Descrição: " + descricao);
//                }
//        }

        } catch (SQLException e) {
            System.out.println("Erro ao listar os produtos: " + e.getMessage());
            return null;
        }
    }

    public void cadastrarProduto(Produto produto){
        String sql  = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try(
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());

            pstmt.executeUpdate();
            System.out.println("Produto adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public Produto acharProdutoPorNome(String nome){
        String sql  = "SELECT * FROM produtos WHERE nome = ?";

        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getString("nome"),
                        rs.getDouble("preco")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto por Nome: " + e.getMessage());
        }

        return null;
    }

    public void removerProdutoPorNome(String nome) {
        String sql  = "DELETE FROM produtos WHERE nome = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            int itensRemovidos = pstmt.executeUpdate();

            if (itensRemovidos > 0) {
                System.out.println("Produto(s) removido com sucesso!" + " (Foram removido(s) " + itensRemovidos + " produtos)");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover produto por Nome: " + e.getMessage());
        }
    }
    public void abrigarProduto(Produto produto) {
        String sql  = "SELECT * FROM produtos WHERE nome = ?";
    }

    public void cadastrarPrateleira(Prateleira prateleira) {
        String sqlPrateleira = "INSERT INTO prateleiras(id, descricao) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmtPrateleira = conn.prepareStatement(sqlPrateleira)) {

            // Definindo os parâmetros da query
            pstmtPrateleira.setInt(1, prateleira.getSecao());
            pstmtPrateleira.setString(2, prateleira.getDescricao());

            // Executa a inserção
            pstmtPrateleira.executeUpdate();
            System.out.println("Prateleira inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir prateleira: " + e.getMessage());
        }
    }

    public void removerPrateleiraPorID(int id) {
        String sql  = "DELETE FROM prateleiras WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int itensRemovidos = pstmt.executeUpdate();

            if (itensRemovidos > 0) {
                System.out.println("Prateleira(s) removida com sucesso!" + " (Foram removida(s) " + itensRemovidos + " prateleiras)");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover prateleira por ID: " + e.getMessage());
        }
    }
}
