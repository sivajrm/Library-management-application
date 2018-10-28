package com.app.library.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	
	@GetMapping()
	public ResponseEntity<List<Customer>> getAllCustomers(){
	    List<Customer> customers = customerService.getAllCustomers();
	    return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/{userName}")
	public ResponseEntity<Customer> getCustomerByUserName(@PathVariable String userName){
	    Customer customer=  customerService.getCustomerByUserName(userName);
	    if(customer == null)
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		customer = customerService.addCustomer(customer);
		if(customer == null)
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{userName}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable String userName){
		 Customer customer = customerService.deleteCustomerByUsername(userName);
		 if(customer == null)
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}