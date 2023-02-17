package com.distribuida.srv;

import com.distribuida.db.Author;
import com.distribuida.dao.AuthorDao;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorDao authorDao;

    @Override
    public Author listarUno(Long id) {
        return authorDao.findById(id);
    }

    @Override
    public List<Author> listarTodos() {
        return authorDao.listAll();
    }

    @Transactional
    @Override
    public void crear(Author author) {
        authorDao.persist(author);

    }
    @Transactional
    @Override
    public void actualizar(Long id, Author author) {
        Author guardar=listarUno(id);
        guardar.setFirst_name(author.getFirst_name());
        guardar.setLast_name(author.getLast_name());
        authorDao.persist(guardar);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        authorDao.deleteById(id);
    }
}
