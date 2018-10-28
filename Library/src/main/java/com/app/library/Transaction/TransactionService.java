package com.app.library.Transaction;

import com.app.library.Book.Book;
import com.app.library.Book.BookService;
import com.app.library.Customer.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	private static final Logger logger = LogManager.getLogger(TransactionService.class);

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookService bookService;

	public Page<Transaction>  getAllTransactions(int page) {
		Page<Transaction> transaction = transactionRepository.findAll(PageRequest.of(--page, 10));
		if(transaction == null) {
			logger.info("GET Transaction Page:{}  not found.",page);
			return transaction;
		}
		logger.info("GET Transaction Page:{} returned {} transactions.", page ,transaction.getTotalElements());
		return transaction;
	}
	
	public Transaction getTransactionByIsbnAndUserName(String isbn, String userName) {
		Transaction transaction = transactionRepository.findByIsbnAndUserName(isbn, userName);
		if(transaction == null) {
			logger.info("GET Transaction {},{} not found.",isbn, userName);
			return transaction;
		}
		logger.info("GET Transaction {},{} returned.",isbn, userName);
		return transaction;
	}
	
	public Transaction addTransaction(Transaction transaction) {
		//validation: a) userName, book exists b) no duplicate transaction for userName, book c) Borrow permitted iff  book copies > 0 
		if(customerRepository.findByUserName(transaction.getUserName()) == null) {
			logger.error("POST Transaction {} userName does not exist.", transaction.getUserName());
			return null;
		}
		if(transactionRepository.findByIsbnAndUserName(transaction.getIsbn(), transaction.getUserName()) != null) {
			logger.error("POST Transaction {},{} already exists.", transaction.getIsbn(), transaction.getUserName());
			return null;
		}
		Book book = bookService.updateBookCount(transaction.getIsbn(),-1);
		if(book == null) {
			logger.error("POST Transaction ISBN:{} {}", transaction.getIsbn(), "Book not found or temporarily  unavailable for borrow");
			return transaction;
		}
		transaction.setRenewalCount(3);
		transactionRepository.save(transaction);
		if(transactionRepository.findByIsbnAndUserName(transaction.getIsbn(), transaction.getUserName()) == null) {
			logger.error("POST Transaction {},{} not saved.", transaction.getIsbn(), transaction.getUserName());
			return null;
		}
		logger.info("POST Transaction {},{}  created.", transaction.getIsbn(), transaction.getUserName());
		return transaction;
	}
	
	public Transaction deleteTransactionByIsbnAndUserName(String isbn, String userName) {
		Transaction transaction = transactionRepository.findByIsbnAndUserName(isbn, userName);
		if(transaction == null) {
			logger.error("DELETE Transaction {},{} not found.", isbn, userName);
			return transaction;
		}
		transactionRepository.delete(transaction);
		logger.info("DELETE Transaction {},{} deleted.", isbn, userName);
		return transaction;
	}
}
