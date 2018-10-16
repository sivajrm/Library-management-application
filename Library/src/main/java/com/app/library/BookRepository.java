package com.app.library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;


public interface BookRepository extends ElasticsearchCrudRepository<Book, String > {
    public Book findByIsbn(String isbn); 
    public Page<Book> findByAuthor(String authorName, PageRequest pageRequest);
    public Page<Book> findByBookNameStartingWith(String prefixTerm, PageRequest pageRequest);
    public Page<Book> findByAuthorStartingWith(String prefixTerm, PageRequest pageRequest); 
}
