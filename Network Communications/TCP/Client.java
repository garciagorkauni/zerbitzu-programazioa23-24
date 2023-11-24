package TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String serverAddress = "192.168.65.56";
        final int port = 12345;
        // InetAddress serverAddress = InetAddress.getLocalHost();
        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);
            String message = "";
            while (!message.equals("exit")) {

                // Auto-flushing enabled

                // Ask for the input to send
                Scanner in = new Scanner(System.in);
                System.out.print("Enter the message for the server: ");
                message = in.nextLine();

                // Send a message to the server
                writer.println(message);

               

                // Receive and print the response from the server
                String serverResponse = reader.readLine();
                System.out.println("Server says: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}