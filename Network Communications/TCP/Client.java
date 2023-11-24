package TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String serverAddress;
        final int port;
        Scanner connection = new Scanner(System.in);
        System.out.print("Write the ip of the server: ");
        serverAddress = connection.nextLine();
        System.out.print("Write the port of the server: ");
        port = connection.nextInt();
        
        // InetAddress serverAddress = InetAddress.getLocalHost();
        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            PrintWriter writer = new PrintWriter(outputStream, true);
            String serverResponse = "";
            while (!serverResponse.equals("exit")) {

                // Auto-flushing enabled

                // Ask for the input to send
                Scanner in = new Scanner(System.in);
                System.out.print("Enter the message for the server: ");
                String message = in.nextLine();

                // Send a message to the server
                writer.println(message);
                if (message.equals("exit")) {
                    break;
                } else {
                    // Ask for the input to send
                    Scanner ino = new Scanner(inputStream);
                    serverResponse = ino.next();
                    System.out.println("Received from Server: " + serverResponse);
                
                    // Send a message to the server
                    writer.println(message);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}