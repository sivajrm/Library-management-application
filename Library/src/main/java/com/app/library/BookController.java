package com.app.library;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<List<Book>> getAllBooks(){
	    List<Book> books = bookService.getAllBooks();
	    return new ResponseEntity<List<Book>>(books,HttpStatus.CREATED);
	}
	
	@GetMapping("/{isbn}")
	public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn){
	    Book book = bookService.getBookByIsbn(isbn);
	    if(book == null)
	    	return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<Book>(book, HttpStatus.OK);
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