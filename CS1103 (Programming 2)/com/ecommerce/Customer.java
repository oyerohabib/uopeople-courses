// com.ecommerce.Customer.java
package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerID;
    private String name;
    private List<Product> shoppingCart;

    public Customer(int customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }

    // Method to add a product to the shopping cart
    public void addToCart(Product product) {
        shoppingCart.add(product);
    }

    // Method to remove a product from the shopping cart
    public void removeFromCart(Product product) {
        shoppingCart.remove(product);
    }

    // Method to calculate the total cost of items in the shopping cart
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product product : shoppingCart) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    // Method to place an order
    public Order placeOrder() {
        // Assuming the order ID is generated elsewhere
        // For simplicity, just create an order with current shopping cart
        Order order = new Order(customerID, this, new ArrayList<>(shoppingCart));
        // Clear the shopping cart after placing the order
        shoppingCart.clear();
        return order;
    }
}