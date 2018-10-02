package com.app.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.app.library.Customer;

@Service
public class CustomerService {
	private static final Logger logger = LogManager.getLogger(LibraryApplication.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer getCustomerByUserName(String userName) {
		Customer customer = customerRepository.findByUserName(userName);
		if(customer == null) {
			logger.info("GET Customer {} not found.",userName);
			return customer;
		}
		logger.info("GET Customer {} returned.",userName);
		return customer;
	}
	
	public List<Customer> getAllCustomers(){
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		logger.info("GET Customers {} returned.", customers);
		return customers;
	}
	
	public Customer addCustomer(Customer customer) {
		if(customerRepository.findByUserName(customer.getUserName()) != null) {
			logger.error("POST Customer {} already exists.",customer.getUserName());
			return null;
		}
		customerRepository.save(customer);
		if(customerRepository.findByUserName(customer.getUserName()) == null) {
			logger.error("POST Customer {} not saved.", customer.getUserName());
			return null;
		}
		logger.info("POST Customer {} created.", customer.getUserName());
		return customer;
	}	
	
	public Customer deleteCustomerByUsername(String username) {
		Customer customer = customerRepository.findByUserName(username);
		if(customer == null) {
			logger.error("DELETE Customer {} not found.", username);
			return customer;
		}
		customerRepository.delete(customer);
		logger.info("DELETE Customer {} deleted.", username);
		return customer;
	}

}