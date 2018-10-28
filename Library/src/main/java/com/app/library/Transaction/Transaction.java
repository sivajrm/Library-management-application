package com.app.library.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "transactions")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@NonNull
	private String isbn;
	@NonNull
	private String userName;
	private Integer renewalCount;
}
