package com.klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Order {

    private int orderId;
    private String customerName;
    private int quantity;
    private Product product;

    @Autowired
    public Order(Product product) {
        this.orderId = 2005;
        this.customerName = "Manan Shah";
        this.quantity = 3;
        this.product = product;
    }

    public void display() {
        System.out.println("The following are the orders:");
        System.out.println("OrderId: " + orderId);
        System.out.println("CustomerName: " + customerName);
        System.out.println("Quantity: " + quantity);
        System.out.println("ProductId: " + product.getProductId());
        System.out.println("ProductName: " + product.getProductName());
        System.out.println("ProductPrice: " + product.getPrice());
        System.out.println("ProductCategory: " + product.getCategory());
    }
}
