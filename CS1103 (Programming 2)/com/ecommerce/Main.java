
// Main program
import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

public class Main {
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