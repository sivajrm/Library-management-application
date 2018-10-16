package com.app.library;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping()
	public ResponseEntity<Page<Book>> getAllBooksDefault(){
	    return this.getAllBooks(0);
	}
	
	@GetMapping("/page/{pageNumber}")
	public ResponseEntity<Page<Book>> getAllBooks(@PathVariable int pageNumber){
		Page<Book> books = bookService.getAllBooks(pageNumber);
	    return new ResponseEntity<Page<Book>>(books,HttpStatus.CREATED);
	}
	
	@GetMapping("/{isbn}")
	public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn){
	    Book book = bookService.getBookByIsbn(isbn);
	    if(book == null)
	    	return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@GetMapping("/author/{authorName}")
	public ResponseEntity<Page<Book>> getBookByAuthor(@PathVariable String authorName){
	    Page<Book> book = bookService.getBookByAuthor(authorName);
	    if(book == null)
	    	return new ResponseEntity<Page<Book>>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<Page<Book>>(book, HttpStatus.OK);
	}
	
	
	@GetMapping("/bookName/{bookName}")
	public ResponseEntity<Page<Book>> getBookByBookNameStartingWith(@PathVariable String bookName){
	    Page<Book> book = bookService.getBookStartingWith(bookName,"book");
	    if(book == null)
	    	return new ResponseEntity<Page<Book>>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<Page<Book>>(book, HttpStatus.OK);
	}
	
	@GetMapping("/authorName/{authorName}")
	public ResponseEntity<Page<Book>> getBookByAuthorNameStartingWith(@PathVariable String authorName){
	    Page<Book> book = bookService.getBookStartingWith(authorName,"author");
	    if(book == null)
	    	return new ResponseEntity<Page<Book>>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<Page<Book>>(book, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Book> addBook(@RequestBody Book book){
	    book = bookService.addBook(book);
		if(book == null)
			return new ResponseEntity<Book>(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}
	
	@PutMapping("/{isbn}")
	public ResponseEntity<String> updateBookCount(@RequestBody Map<String,String> body ,@PathVariable String isbn) {
		Integer count = body.get("mode").equalsIgnoreCase("borrow") ? -1 : +1;
		Book book = bookService.updateBookCount(isbn,count);
		if(book == null)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		if(book.getCopies() == -1)
			return new ResponseEntity<String>("Book temporarily unavailable for borrow",HttpStatus.OK);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{isbn}")
	public ResponseEntity<Book> deleteBookByIsbn(@PathVariable String isbn){
		Book book = bookService.deleteBookByIsbn(isbn);
		if(book == null)
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/{random}")
	public ResponseEntity<Book> generateBooks(@RequestBody int count){
		  bookService.addBooks(count);
		  return new ResponseEntity<Book>(HttpStatus.CREATED);
	}
}