package com.app.library.Transaction;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, String> {
	Transaction findByIsbnAndUserName(String isbn, String userName);
}
