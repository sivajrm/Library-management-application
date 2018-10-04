package com.app.library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends ElasticsearchCrudRepository<Book, String > {
    public Book findByIsbn(String isbn); 
    public Page<Book> findByAuthor(String authorName, PageRequest pageRequest);
}
