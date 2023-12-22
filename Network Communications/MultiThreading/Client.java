package MultiThreading;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        
        //final String serverAddress = "192.168.65.31";
        final int port = 6000;
        final String ipa;
        Scanner in = new Scanner(System.in);
        System.out.println("Sartu ip-a: ");
        ipa = in.nextLine();
        // InetAddress serverAddress = InetAddress.getLocalHost();
        while (true) {
            try (Socket socket = new Socket(ipa, port)) {
                // Create input and output streams for the server
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                PrintWriter writer = new PrintWriter(outputStream, true);
                // Auto-flushing enabled

                System.out.println("Sartu zure mensajea: ");
                String s = in.nextLine();
                writer.println(s);

                // Receive and print the response from the server
                String serverResponse = reader.readLine();
                System.out.println("Server says: " + serverResponse);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}