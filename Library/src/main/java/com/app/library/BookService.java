package com.app.library;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.app.library.Book;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
	private static final Logger logger = LogManager.getLogger(LibraryApplication.class);
	
	/*
	@Autowired
	private MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	MongoDatabase database = mongoClient.getDatabase("TestDatabase");
	MongoCollection<Document> booksCollection = database.getCollection("books");
	MongoOperations mongoOperations = new MongoTemplate(new SimpleMongoDbFactory( new MongoClient(), "TestDatabase"));
	*/
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);
		logger.info("GET Books {} returned.", books);
		return books;
	}
	
	public Book addBook(Book book) {
		if(bookRepository.findByIsbn(book.getIsbn()) != null) {
			logger.error("POST Book {} already exists.", book.getIsbn());
			return null;
		}
		bookRepository.save(book);
		if(bookRepository.findByIsbn(book.getIsbn()) == null) {
			logger.error("POST Book {} not saved.", book.getIsbn());
			return null;
		}
		logger.info("POST Book {} created.", book.getIsbn());
		return book;
	}
	
	public Book getBookByIsbn(String isbn) {
		Book book = bookRepository.findByIsbn(isbn);
		if(book == null) {
			logger.info("GET Book {} not found.",isbn);
		}
		else {
			logger.info("GET Book {} returned.",isbn);
		}
		return book;
	}
	
	public Page<Book> getBookByAuthor(String authorName) {
		Page<Book> book = bookRepository.findByAuthor(authorName, new PageRequest(0, 10));
		if(book.getContent().size() == 0) {
			logger.info("GET Book {} not found.",authorName);
		}
		logger.info("GET Book {} returned.",authorName);
		return book;
	}
	
	public Book updateBookCount(String isbn, Integer value) {
		Book book = bookRepository.findByIsbn(isbn);
		if(book == null) {
			logger.error("PUT Book {} not found.", isbn);
			return book;
		}
		if(book.getCopies()+value < 0) {
			logger.warn("PUT Book {} copy:0 Hence temporarily unavailable for borrow", isbn);
			book.setCopies(-1);
			return book;
		}
		book.setCopies(book.getCopies()+value);
		bookRepository.save(book);
        book = bookRepository.findByIsbn(isbn);
        logger.info("PUT Book {} count {} updated.", isbn, book.getCopies());
		return book;
	}
	
	public Book deleteBookByIsbn(String isbn) {
		Book book = bookRepository.findByIsbn(isbn);
		if(book == null) {
			logger.error("DELETE Book {} not found.", isbn);
			return book;
		}
		bookRepository.delete(book);
		logger.info("DELETE Book {} deleted.", isbn);
		return book;
	}
	
	public void addBooks(int count) {
		for(int i = 0; i < count; i++) {
			Book book = new Book();
			String name = RandomStringUtils.randomAlphabetic(10);
			book.setIsbn(name);
			book.setBookName(name);
			book.setAuthor(name);
			bookRepository.save(book);
		}
	}
}
