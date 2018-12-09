package com.app.library.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//Book entity
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "book", type = "default")
public class Book {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@NonNull
	private String isbn;
	private String bookName;
	private String author;
	private String url;
	private Integer copies;
}

