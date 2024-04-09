// com.ecommerce.Order.java
package com.ecommerce;

import com.ecommerce.Customer;
import com.ecommerce.Product;
import java.util.List;

public class Order {
    private int orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;

    public Order(int orderID, Customer customer, List<Product> products) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        calculateOrderTotal();
        // Set initial status
        this.status = "Pending";
    }

    // Method to calculate the total cost of the order
    private void calculateOrderTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        this.orderTotal = total;
    }

    // Method to generate an order summary
    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ").append(customer.getName()).append("\n");
        summary.append("Products:\n");
        for (Product product : products) {
            summary.append("- ").append(product.getName()).append(": $").append(product.getPrice()).append("\n");
        }
        summary.append("Total: $").append(orderTotal).append("\n");
        return summary.toString();
    }

    // Method to update the order status
    public void updateOrderStatus(String status) {
        this.status = status;
    }

    // Getters
    public double getOrderTotal() {
        return orderTotal;
    }
}