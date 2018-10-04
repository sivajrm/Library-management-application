package com.app.library;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

//import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

//@Document(collection = "books")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "book", type = "default")
public class Book {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@NotNull
	private String isbn;
	private String bookName;
	private String author;
	private String url;
	private Integer copies;
	
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
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCopies() {
		return copies;
	}
	public void setCopies(Integer copies) {
		this.copies = copies;
	}
}

