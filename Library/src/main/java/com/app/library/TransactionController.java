package com.app.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("isbn/{isbn}/userName/{userName}")
	public ResponseEntity<Transaction> getTransactionByIsbnAndUserName(@PathVariable String isbn,  @PathVariable String userName){
	    Transaction transaction = transactionService.getTransactionByIsbnAndUserName(isbn, userName);
	    if(transaction == null)
	    	return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
	 
	@PostMapping()
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
		 transaction = transactionService.addTransaction(transaction);
		 if(transaction == null)
			 return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		 return new ResponseEntity<Transaction>(transaction, HttpStatus.CREATED);
	}
	
	@DeleteMapping("isbn/{isbn}/userName/{userName}")
	public ResponseEntity<Transaction> deleteTransactionByIsbnAndUserName(@PathVariable String isbn, @PathVariable String userName){
		Transaction transaction =  transactionService.deleteTransactionByIsbnAndUserName(isbn, userName);
		if(transaction == null)
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Transaction>(HttpStatus.NO_CONTENT);
	}
}
