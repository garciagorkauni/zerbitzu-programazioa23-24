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
            
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            
                // Receive the random number from client
                int number = dataInputStream.readInt();

                // Make a operation with the number
                int numberToSum = (int)Math.random()*100;
                System.out.println("This is the operation: " + number + " (RECEIVED) + " + numberToSum + " (RANDOM) = " + (number + numberToSum));
            
                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}