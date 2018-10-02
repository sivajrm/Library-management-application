package com.app.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "transactions")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@NotNull
	private String isbn;
	@NotNull
	private String userName;
	private Integer renewalCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getRenewalCount() {
		return renewalCount;
	}
	public void setRenewalCount(Integer renewalCount) {
		this.renewalCount = renewalCount;
	}
}
