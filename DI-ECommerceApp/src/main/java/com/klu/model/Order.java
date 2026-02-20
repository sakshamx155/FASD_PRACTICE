package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Order {
  private int orderId;
  private String customerName;
  private String productName;
  @Value("4")
  private int quantity;
  
  public Order(@Value("101")int orderId,@Value("Akshaya") String customerName) {
	  this.orderId=orderId;
	  this.customerName=customerName;
  }
  @Value("Laptop")
  public void setProductName(String productName) {
	  this.productName=productName;
  }
  public void display() {
	  System.out.println("Following is Order Details");
	  System.out.println("OrderId:"+orderId);
	  System.out.println("CustomerName:"+customerName);
	  System.out.println("ProductName:"+productName);
	  System.out.println("Quantity:"+quantity);
	  
  }
}
