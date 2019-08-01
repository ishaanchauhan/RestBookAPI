package com.bcs.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
	
public class Book {

	private long id;
	@NotNull(message="@bookName:must not be null")
	@Pattern(regexp="[a-zA-Z]{1,100}$", message="first name should be alphabetical and less than or equal to 50 characters")
	private String bookName;
	@NotNull(message="@author:must not be null")
	@Pattern(regexp="[a-zA-Z]{1,100}$", message="first name should be alphabetical and less than or equal to 50 characters")
	private String author;	
	@NotNull
	@Pattern(regexp="^\\d+(\\.\\d{1,2})?$", message="first name should be alphabetical and less than or equal to 50 characters")
	private String price;
	
	public Book(){
	}
	
	public Book(long id, String bookName, String author, String price){
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.price = price;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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


}
