package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Book {
	
	// Properties of the book
	// private means: These cannot be accessed directly from other classes. Access is controlled using getters.
	
	private String isbn;
	private String title;
	private String author;
	private double price;
	
	// Default constructor
	// Constructor runs when object is created
	// this means current objects
	public Book() {
		this.isbn="ISBN-101";
		this.title="Spring Framework Basics";
		this.author="Rod Johnson";
		this.price=550.0;
	}
	
	//getter method
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	 public String toString() {
        return "Book [isbn=" + isbn + ", title=" + title +
               ", author=" + author + ", price=" + price + "]";
    }
}
