package com.app.library.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//Customers entity
@Document(collection = "customers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@NonNull
	private String userName;
	private String fullName;
	private String phoneNumber;
	private String email;
	private String address;
}

