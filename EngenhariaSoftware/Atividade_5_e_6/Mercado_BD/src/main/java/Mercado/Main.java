package Mercado;

public class Main {
    public static void main(String[] args) {
        Mercado mercado = new Mercado();

        mercado.criarTabelaProdutos();

        Produto banana = new Produto("Banana", 5.65);
        Produto leite = new Produto("Leite", 5.99);

        mercado.cadastrarProduto(banana);
        mercado.cadastrarProduto(leite);
        mercado.removerProdutoPorNome(banana.getNome());
        mercado.listarProdutos();

        Produto produto_PorNome = mercado.acharProdutoPorNome(banana.getNome());
        if (produto_PorNome != null) {
            System.out.println(
                    "Produto encontrado por nome: " +
                    produto_PorNome.getNome() +
                    produto_PorNome.getPreco());
        } else {
            System.out.println("Nenhum produto encontrado com esse nome.");
        }
        Produto.limparTabela();
    }
}
