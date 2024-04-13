import java.text.SimpleDateFormat;
import java.util.Date;

class Clock {
    // Method to continuously update and print the current time
    public static void displayTime() {
        // Create a thread for continuously updating the time
        Thread updateTimeThread = new Thread(() -> {
            while (true) {
                try {
                    // Update the time
                    updateCurrentTime();
                    // Sleep for 1 second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Error in updateTimeThread: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Create a thread for printing the time to the console
        Thread printTimeThread = new Thread(() -> {
            while (true) {
                try {
                    // Print the current time
                    printCurrentTime();
                    // Sleep for 1 second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println("Error in printTimeThread: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Set higher priority for the printTimeThread
        printTimeThread.setPriority(Thread.MAX_PRIORITY);

        // Start the threads
        updateTimeThread.start();
        printTimeThread.start();
    }

    // Method to update the current time
    private static synchronized void updateCurrentTime() {
        // Get the current time
        Date now = new Date();
        // Display the time in HH:mm:ss dd-MM-yyyy format
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        System.out.println("Current Time: " + sdf.format(now));
    }

    // Method to print the current time
    private static synchronized void printCurrentTime() {
        // Get the current time
        Date now = new Date();
        // Display the time in HH:mm:ss format
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Time: " + sdf.format(now));
    }

    public static void main(String[] args) {
        // Start displaying the time
        displayTime();
    }
}
