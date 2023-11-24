import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Define the server address and por
        final String serverAddress = "192.168.65.X";
        final int port = 12345;

        // Try to connect to the server
        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Receive and print the greeting from the server
            String serverResponse = reader.readLine();
            System.out.println("Server says: " + serverResponse);

            // Send a message to the server
            writer.println("Thank you for the greeting server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}