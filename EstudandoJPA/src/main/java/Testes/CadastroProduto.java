package Testes;

import dao.CategoriaDao;
import dao.ProdutoDao;
import org.example.Categoria;
import org.example.Produto;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());

        //List<Produto> todos = produtoDao.buscarTodos();
        //List<Produto> todos = produtoDao.buscarPorNome("Sansung");
        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("celulares");

        todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Sansung");
        System.out.println(precoDoProduto);


    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("celulares");
        Produto celular = new Produto("Sansung", "Muito legal", new BigDecimal("809"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin(); //Abrindo

       categoriaDao.cadastrar(celulares);
       produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }

}
