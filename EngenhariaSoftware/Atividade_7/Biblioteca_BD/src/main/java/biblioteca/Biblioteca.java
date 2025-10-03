package biblioteca;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Biblioteca {

    private static final String DB_URL_LIVROS = "jdbc:sqlite:livros.db";
    private static final String DB_URL_USUARIOS = "jdbc:sqlite:usuarios.db";

    public void addLivro(Livro livro) {
        //É um comando em SQL armazenado na variável sql, onde ? é um placeholder
        String sql = "INSERT INTO livros(isbn, titulo, autor) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL_LIVROS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getIsbn());
            pstmt.setString(2, livro.getTitulo());
            pstmt.setString(3, livro.getAutor());

            pstmt.executeUpdate();
            System.out.println("Livro adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    public void addUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nome, ra) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL_USUARIOS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setInt(2, usuario.getRa());

            pstmt.executeUpdate();
            System.out.println("Usuario adicionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar Usuario: " + e.getMessage());
        }
    }

    public List<Livro> buscarLivroTitulo(String titulo) {
        List<Livro> encontrados = new LinkedList<>();
        String sql = "SELECT * FROM livros WHERE titulo = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL_LIVROS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro(
                        //É necessário citar os atributos na ordem ao qual foram definidos
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn")
                );
                encontrados.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por título: " + e.getMessage());
        }

        return encontrados;
    }

    public Livro buscarLivroIsbn(String isbn) {
        String sql = "SELECT * FROM livros WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL_LIVROS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, isbn);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por ISBN: " + e.getMessage());
        }

        return null;
    }

    public Usuario buscarUsuarioRa(int ra) {
        String sql = "SELECT * FROM usuarios WHERE ra = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL_USUARIOS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ra);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getString("nome"),
                        rs.getInt("ra")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuario por RA: " + e.getMessage());
        }

        return null;
    }
}
