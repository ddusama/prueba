package com.distribuida.svc;

import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @PersistenceContext(unitName = "jpa")
    private EntityManager entityManager;

    @Override
    public Book listarUno(Integer id){
        return entityManager.find(Book.class,id);
    }

    @Override
    public List<Book> listarTodos()  {
        return entityManager
                .createNamedQuery("Book.findAll",Book.class)
                .getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void crear(Book book) {
        entityManager.persist(book);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void actualizar(Integer id, Book book){
        var guardar=this.listarUno(id);
        guardar.setIsbn(book.getIsbn());
        guardar.setAuthor(book.getAuthor());
        guardar.setTitle(book.getTitle());
        guardar.setPrice(book.getPrice());
        entityManager.persist(book);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void eliminar(Integer id) {
        entityManager.remove(listarUno(id));
    }

}
