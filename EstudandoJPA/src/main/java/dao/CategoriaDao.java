package dao;

import org.example.Categoria;
import org.example.Produto;

import javax.persistence.EntityManager;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        this.em.persist(categoria);
    }

    public void atualizar(Categoria categoria){
        this.em.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria = em.merge(categoria); // atualizando para continuar managed
        this.em.remove(categoria);
    } //tem que está no estado managed

    public Produto buscarPorId(Long id){
        return em.find(Produto.class, id);
    }
}
