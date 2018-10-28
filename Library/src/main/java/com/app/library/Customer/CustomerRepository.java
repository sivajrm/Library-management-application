package com.app.library.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String > {
	Customer findByUserName(String userName);
}