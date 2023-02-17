package com.distribuida.svc;

import com.distribuida.db.Book;
import java.util.List;

public interface BookService {
    Book listarUno(Integer id);
    List<Book> listarTodos();
    void crear(Book book);
    void actualizar(Integer id,Book book);
    void eliminar(Integer id);

}
