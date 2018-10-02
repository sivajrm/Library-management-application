package com.app.library;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, String > {
    public Book findByIsbn(String isbn); 
}
