package biblioteca;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Cria uma tabela antes de prosseguir
        LivroDAO.criarTabela();

        Biblioteca biblioteca = new Biblioteca();

        // Adicionando um livro
        Livro livro1 = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", "001");
        Livro livro2 = new Livro("Dom Casmurro", "Machado de Assis", "002");

        biblioteca.addLivro(livro1);
        biblioteca.addLivro(livro2);

        // Buscar por título
        List<Livro> encontrados = biblioteca.buscarLivroTitulo("Dom Casmurro");
        for (Livro livro : encontrados) {
            System.out.println("Encontrado: " + livro);
        }

        // Buscar por ISBN
        Livro livroPorIsbn = biblioteca.buscarLivroIsbn("001");
        if (livroPorIsbn != null) {
            System.out.println("Livro encontrado por ISBN: " + livroPorIsbn);
        } else {
            System.out.println("Nenhum livro encontrado com esse ISBN.");
        }
        LivroDAO.listarLivros();
    }
}
