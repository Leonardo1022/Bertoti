package biblioteca;

import java.sql.*; //Importa todas as classes do pacote java.sql

public class UsuarioDAO {

    public static void main(String[] args) {
        conectar();
        criarTabela();
        limparTabela();
    }

    public static void criarTabela() {
        //Conexão para o banco de dados
        String url = "jdbc:sqlite:usuarios.db";
        //Definição das colunas
        String sql = "CREATE TABLE IF NOT EXISTS usuarios(\n"
                + " nome TEXT NOT NULL, \n"
                + " ra INT(5) PRIMARY KEY \n"
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
        String sql = "DELETE FROM usuarios";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Tabela limpa com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao limpar tabela: " + e.getMessage());
        }
    }

    public static void conectar() {
        String url = "jdbc:sqlite:usuarios.db";

        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Conexão com o banco de dados foi estabelecida."); //Mensagem se a conexão com o BD foi estabelecida

        }
        catch (SQLException e) {
            System.out.println(e.getMessage()); //Mensagem se não foi
        }
    }

    public static void listarUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Lista de usuarios no banco ===");

            while (rs.next()) {
                String nome = rs.getString("nome");
                String ra = rs.getString("ra");

                System.out.println("Nome: " + nome + " | RA: " + ra);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuarios: " + e.getMessage());
        }
    }
}
