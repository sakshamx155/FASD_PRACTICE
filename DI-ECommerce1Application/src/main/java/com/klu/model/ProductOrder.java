package com.klu.model;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ProductOrder {
  
  private int orderId;
  private String customerName;
  private String productName;
  private int quantity;
  
  public ProductOrder(@Value("102")int oid,@Value("Virat")String cn) {
    this.orderId=oid;
    this.customerName=cn;
  }
  
  @Value("BRDM")
  public void setProductName(String p) {
    this.productName=p;
  }
  
  @Value("9")
  public void setQuantity(int q) {
    this.quantity=q;
  }
  
  public void display() {
    System.out.println("Ordered Details :");
    System.out.println("Order ID       : " + orderId);
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Product Name   : " + productName);
        System.out.println("Quantity       : " + quantity);
  }
  
}