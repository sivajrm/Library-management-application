package com.app.library.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepositoryWithMongoDB extends PagingAndSortingRepository<Book, String > {
	Book findByIsbn(String isbn);
    Page<Book> findByAuthor(String authorName, PageRequest pageRequest);
    Page<Book> findByBookNameStartingWith(String prefixTerm, PageRequest pageRequest);
    Page<Book> findByAuthorStartingWith(String prefixTerm, PageRequest pageRequest);
}
