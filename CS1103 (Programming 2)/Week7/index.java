import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The ChatServer class implements a simple chat server using socket
 * programming.
 * It listens for incoming connections from multiple clients and broadcasts
 * messages
 * received from one client to all connected clients.
 */
class ChatServer {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    /**
     * The main method creates a ServerSocket and listens for incoming connections.
     * For each incoming connection, it creates a new ClientHandler thread to handle
     * communication with the client.
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server started.");
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The ClientHandler class is responsible for handling communication with a
     * single client.
     * It listens for messages from the client and broadcasts them to all connected
     * clients.
     */
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter writer;
        private static int userCount = 0;

        /**
         * Constructor to initialize the ClientHandler with the client's socket.
         */
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        /**
         * The run method is called when the thread starts. It handles communication
         * with the client, reading messages from the client and broadcasting them.
         */
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);
                userCount++;
                writer.println("Welcome! You are user #" + userCount);

                clientWriters.add(writer);

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received message from client: " + message);
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    clientWriters.remove(writer);
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * The broadcastMessage method sends a message to all connected clients.
         */
        private void broadcastMessage(String message) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }
}

/**
 * The ChatClient class implements a simple chat client that connects to the
 * chat server
 * using socket programming. It allows users to send messages to the server and
 * receive
 * messages from other users.
 */
class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    /**
     * The main method creates a socket connection to the chat server and starts
     * communication with the server. It listens for messages from the server
     * and allows the user to input messages to send to the server.
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to Chat Server. Start typing messages...");

            // Create a separate thread to continuously receive messages from the server
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Received from server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            // Main loop to read user input and send messages to the server
            String userInputMessage;
            while ((userInputMessage = userInput.readLine()) != null) {
                writer.println(userInputMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
