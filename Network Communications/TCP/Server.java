package TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        final int port = 12345; 
        
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on " 
                + localHost.getHostAddress() + ":" + port);
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from: " 
                    + clientSocket.getInetAddress());

            String message = "";
            while (!message.equals("exit")) {                
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
            
                Scanner scan = new Scanner(inputStream);
                PrintWriter writer = new PrintWriter(outputStream, true); 
                
                String clientMessage = scan.nextLine();
                if(clientMessage.equals("exit")){
                    System.out.println("Client has exited the chat...");
                    break;
                } else{
                    System.out.println("Received from client: " + clientMessage);
            
                    // Ask for the input to send
                    Scanner in = new Scanner(System.in);
                    System.out.print("Enter the message for the client: ");
                    message = in.nextLine();

                    // Send a message to the server
                    writer.println(message);
                }                    
            }
            clientSocket.close();
            System.out.println("The chat has been closed...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
