import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final int port = 12345;
            
        try {
            // Show the host listening
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on " + localHost.getHostAddress() + ":" + port);

            // Create the server socket
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                // Accept client's connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from: " + clientSocket.getInetAddress());
            
                // Create input and output streams for the client
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
            
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter writer = new PrintWriter(outputStream, true);
            
                // Send the greeting to the client
                writer.println("Welcome client!");

                // Read the client's response
                String clientMessage = reader.readLine();
                System.out.println("Received from client: " + clientMessage);
            
                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}