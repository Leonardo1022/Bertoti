package biblioteca;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Teste {

    @BeforeAll
    static void setup() {
        //Cria a tabela e limpa ela antes dos testes para evitar erros
        LivroDAO.criarTabela();
        LivroDAO.limparTabela();
        UsuarioDAO.criarTabela();
        UsuarioDAO.limparTabela();
    }

    @Test
    void test() {
        Biblioteca bib = new Biblioteca();

        //Adiciona os livros e usuarios para fazer o teste
        bib.addLivro(new Livro("Senhor dos Aneis", "J R R Tolkien", "123"));
        bib.addLivro(new Livro("Divina Comédia", "Dante Alighieri", "456"));
        bib.addUsuario(new Usuario("Pedro", 12345));

        //Testa o metodo de busca por titulo
        List<Livro> busca = bib.buscarLivroTitulo("Senhor dos Aneis");
        Assertions.assertFalse(busca.isEmpty(), "A lista está vazia");
        Assertions.assertEquals("J R R Tolkien", busca.get(0).getAutor(), "A busca pelo autor retornou um valor diferente");

        //Testa o metodo de busca por isbn, não retorna uma lista porque ele e uma PK
        Livro liv = bib.buscarLivroIsbn("123");
        Assertions.assertNotNull(liv);
        Assertions.assertEquals("Senhor dos Aneis", liv.getTitulo(), "A busca pelo titulo retornou um valor diferente");

        // Testa o metodo de busca por RA
        Usuario user = bib.buscarUsuarioRa(12345);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(12345, user.getRa(), "A busca pelo RA retornou um valor diferente");

    }
}
