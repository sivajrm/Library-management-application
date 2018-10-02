package com.app.library;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String > {
	public Customer findByUserName(String userName); 
}