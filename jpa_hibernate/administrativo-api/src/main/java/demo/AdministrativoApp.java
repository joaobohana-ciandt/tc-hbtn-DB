
import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

import java.util.List;

public class AdministrativoApp {
    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        //3) Atualizando nome do produto
        p1.setNome("TV Samsung");
        produtoModel.update(p1);

        Produto produtoEncontrado = produtoModel.findById(p1);
        System.out.println(produtoEncontrado.getNome() + " - " + p1.getNome());

        //4) Deletando produto
        produtoModel.delete(p1);
        produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados : " + produtos.size());

        PessoaModel pessoaModel = new PessoaModel();

        Pessoa p2 = new Pessoa();
        p2.setNome("Joao Victor");
        p2.setIdade(21);
        p2.setDataDeNascimento("31-10-2000");
        p2.setEmail("joaovictor@gmail.com");
        p2.setCpf("11111111111");

        pessoaModel.create(p2);

        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas: " + pessoas.size());

        p2.setNome("Joana");
        pessoaModel.update(p2);

        Pessoa pessoaEncontrada = pessoaModel.findById(p2);
        System.out.println(pessoaEncontrada.getNome() + " - " + p2.getNome());

        pessoaModel.delete(p2);
        pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas: " + pessoas.size());
    }
}
