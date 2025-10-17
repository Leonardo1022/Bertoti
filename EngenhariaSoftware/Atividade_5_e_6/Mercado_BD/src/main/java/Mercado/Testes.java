package Mercado;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Testes {

    @BeforeAll
    static void setup() {
        Mercado mercado = new Mercado();
        //Cria a tabela e limpa ela antes dos testes para evitar erros
        mercado.criarTabelaProdutos();
        mercado.criarTabelaPrateleiras();
        Produto.limparTabela();
        Prateleira.limparTabela();
    }

    @Test
    void teste() {
        Mercado mercado = new Mercado();

        mercado.cadastrarProduto(new Produto("Batata", 10.00));
        mercado.cadastrarProduto(new Produto("Espinafre", 12.00));

        List<Produto> listagem = mercado.listarProdutos();
        Assertions.assertFalse(listagem.isEmpty());
        Assertions.assertEquals(2, listagem.size());
    }

}
