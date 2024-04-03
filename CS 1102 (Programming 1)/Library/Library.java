import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

class Book {
    private String title;
    private String author;
    private int quantity;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Library {
    private Map<String, Book> bookInventory;

    public Library() {
        this.bookInventory = new HashMap<>();
    }

    public void addBook(String title, String author, int quantity) {
        Book existingBook = bookInventory.get(title);

        if (existingBook != null) {
            // Book already exists, update the quantity
            existingBook.setQuantity(existingBook.getQuantity() + quantity);
            System.out.println("Book quantity updated.");
        } else {
            // Add a new book to the library
            Book newBook = new Book(title, author, quantity);
            bookInventory.put(title, newBook);
            System.out.println("Book added to the library.");
        }
    }

    public void borrowBook(String title, int quantity) {
        Book existingBook = bookInventory.get(title);

        if (existingBook != null && existingBook.getQuantity() >= quantity) {
            // Sufficient quantity available, update the quantity
            existingBook.setQuantity(existingBook.getQuantity() - quantity);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Insufficient quantity or book not found.");
        }
    }

    public void returnBook(String title, int quantity) {
        Book existingBook = bookInventory.get(title);

        if (existingBook != null) {
            // Update the quantity when the book is returned
            existingBook.setQuantity(existingBook.getQuantity() + quantity);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found in the library.");
        }
    }
}

class LibrarySystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Library library = new Library();

            while (true) {
                System.out.println("Library System Menu:");
                System.out.println("1. Add Books");
                System.out.println("2. Borrow Books");
                System.out.println("3. Return Books");
                System.out.println("4. Exit");
                try {
                    System.out.print("Enter your choice: ");
                    String choiceInput = scanner.next(); // Read as a string
                    if (!choiceInput.matches("\\d+")) {
                        // Check if the entered value is not an integer
                        System.out.println("Invalid choice. Please enter a valid option.");
                        continue; // Restart the loop
                    }
                    int choice = Integer.parseInt(choiceInput);
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            System.out.print("Enter book title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter book author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            library.addBook(title, author, quantity);
                            break;
                        case 2:
                            System.out.print("Enter book title to borrow: ");
                            title = scanner.nextLine();
                            System.out.print("Enter quantity to borrow: ");
                            quantity = scanner.nextInt();
                            library.borrowBook(title, quantity);
                            break;
                        case 3:
                            System.out.print("Enter book title to return: ");
                            title = scanner.nextLine();
                            System.out.print("Enter quantity to return: ");
                            quantity = scanner.nextInt();
                            library.returnBook(title, quantity);
                            break;
                        case 4:
                            System.out.println("Exiting the Library System. Goodbye!");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    scanner.next(); // Consume the invalid input to avoid an infinite loop
                }

            }
        }
    }
}