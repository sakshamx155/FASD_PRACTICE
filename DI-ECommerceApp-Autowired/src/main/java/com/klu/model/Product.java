package com.klu.model;

import org.springframework.stereotype.Component;

@Component

public class Product {

	private int productId;
	private String productName;
	private double price;
	private String category;
	
public Product() {
	this.productId=101;
	this.productName="Macbook";
	this.price=99000.00;
	this.category="Electronics";
	
}
public int getProductId() {
	return productId;
}
public String getProductName() {
	return productName;
}
public String getCategory() {
	return category;
}
public double getPrice() {
	return price;
}
}
