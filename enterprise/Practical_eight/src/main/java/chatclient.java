

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class chatclient {
    public static void main(String[] args) throws IOException {
        // 1. Create a socket to connect to the server at localhost:5000
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to the chat server.");

        // 2. Set up streams to communicate with the server
        // Reads messages from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Sends messages to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Reads messages from the client's own console
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String msg;
        while (true) {
            // Read message from client's console
            System.out.print("You: ");
            msg = console.readLine();
            
            // Send message to the server
            out.println(msg);

            // Check for exit condition
            if (msg.equalsIgnoreCase("bye")) {
                System.out.println("Disconnecting from server.");
                break;
            }

            // Read and display the server's response
            System.out.println("Server: " + in.readLine());
        }

        // 3. Close resources
        socket.close();
    }
}