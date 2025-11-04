

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class chatServer {
    public static void main(String[] args) throws IOException {
        // 1. Create a server socket listening on port 5000
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Chat Server started on port 5000...");

        // 2. Wait for a client to connect (this is a blocking call)
        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        // 3. Set up streams to communicate with the client
        // Reads messages from the client
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Sends messages to the client
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // Reads messages from the server's own console
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String msg;
        while (true) {
            // Read message from client
            if ((msg = in.readLine()) != null) {
                if (msg.equalsIgnoreCase("bye")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + msg);

                // Read response from server's console and send to client
                System.out.print("You: ");
                out.println(console.readLine());
            }
        }

        // 4. Close resources
        socket.close();
        serverSocket.close();
    }
}