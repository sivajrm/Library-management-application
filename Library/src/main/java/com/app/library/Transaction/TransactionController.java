package com.app.library.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<Page<Transaction>> getAllTransactions(){
        int page = 1;
        Page<Transaction> transaction = transactionService.getAllTransactions(page);
        if(transaction == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/{page}")
    public ResponseEntity<Page<Transaction>> getAllTransactions(@PathVariable int page){
        page = page == 0 ? 1: page;
        Page<Transaction> transaction = transactionService.getAllTransactions(page);
        if(transaction == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
	
	@GetMapping("/isbn/{isbn}/userName/{userName}")
	public ResponseEntity<Transaction> getTransactionByIsbnAndUserName(@PathVariable String isbn,  @PathVariable String userName){
	    Transaction transaction = transactionService.getTransactionByIsbnAndUserName(isbn, userName);
	    if(transaction == null)
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<>(transaction, HttpStatus.OK);
	}
	 
	@PostMapping()
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
		 transaction = transactionService.addTransaction(transaction);
		 if(transaction == null)
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 return new ResponseEntity<>(transaction, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/isbn/{isbn}/userName/{userName}")
	public ResponseEntity<Transaction> deleteTransactionByIsbnAndUserName(@PathVariable String isbn, @PathVariable String userName){
		Transaction transaction =  transactionService.deleteTransactionByIsbnAndUserName(isbn, userName);
		if(transaction == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
