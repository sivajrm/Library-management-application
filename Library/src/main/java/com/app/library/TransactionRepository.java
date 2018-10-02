package com.app.library;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String>{
	public Transaction findByIsbnAndUserName(String isbn, String userName);
}
