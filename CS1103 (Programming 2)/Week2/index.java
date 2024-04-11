import java.util.List;
import java.util.ArrayList;

class Product {
    private int productID;
    private String name;
    private double price;

    public Product(int productID, String name, double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    // Getters
    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Other methods
    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class Customer {
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

    public String getName() {
        return name;
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

class Order {
    private int orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;
    private String status;

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

public class index {
    public static void main(String[] args) {
        // Create instances of products
        Product product1 = new Product(1, "Product 1", 10.99);
        Product product2 = new Product(2, "Product 2", 20.49);

        // Create instances of customers
        Customer customer1 = new Customer(1, "Customer 1");
        Customer customer2 = new Customer(2, "Customer 2");

        // Add products to customers' shopping carts
        customer1.addToCart(product1);
        customer2.addToCart(product2);

        // Place orders
        Order order1 = customer1.placeOrder();
        Order order2 = customer2.placeOrder();

        // Display information about products, customers, and orders
        System.out.println("Order 1 Total: $" + order1.getOrderTotal());
        System.out.println("Order 2 Total: $" + order2.getOrderTotal());
    }
}