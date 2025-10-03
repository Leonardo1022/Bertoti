package biblioteca;

import java.sql.*; //Importa todas as classes do pacote java.sql

public class LivroDAO {

    public static void main(String[] args) {
        conectar();
        criarTabela();
        limparTabela();
    }

    public static void criarTabela() {
        String url = "jdbc:sqlite:livros.db";
        String sql = "CREATE TABLE IF NOT EXISTS livros(\n"
                + " isbn TEXT PRIMARY KEY, \n"
                + " titulo TEXT NOT NULL,\n"
                + "autor TEXT NOT NULL \n"
                + ");";

        try (var conn = DriverManager.getConnection(url)) {
            java.sql.Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso!");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void limparTabela() {
        String sql = "DELETE FROM livros";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:livros.db");
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabela limpa com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao limpar tabela: " + e.getMessage());
        }
    }

    public static void conectar() {
        // connection string
        String url = "jdbc:sqlite:livros.db"; // Caminho para o BD

        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Conexão com o banco de dados foi estabelecida."); //Mensagem se a conexão com o BD foi estabelecida

        }
        catch (SQLException e) {
            System.out.println(e.getMessage()); //Mensagem se não foi
        }
    }

    public static void listarLivros() {
        String sql = "SELECT * FROM livros";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:livros.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Lista de livros no banco ===");

            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");

                System.out.println("ISBN: " + isbn + " | Título: " + titulo + " | Autor: " + autor);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }
}
