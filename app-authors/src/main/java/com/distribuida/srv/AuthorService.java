package com.distribuida.srv;

import com.distribuida.db.Author;

import java.util.List;

public interface AuthorService {
    Author listarUno(Long id);
    List<Author> listarTodos();

    void crear(Author author);
    void actualizar(Long id,Author author);
    void eliminar(Long id);
}
