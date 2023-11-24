package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerReceiver {
    public static void main(String[] args) {
        // Define the port
        int port = 12345;           

        // Say the server is waiting
        InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on " 
                + localHost.getHostAddress() + ":" + port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        while(true){
            port++;
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from: " 
                    + clientSocket.getInetAddress());

                InputStream inputStream = clientSocket.getInputStream();
                Scanner scan = new Scanner(inputStream);
                String clientMessage = scan.nextLine();
                System.out.println("Received from client: " + clientMessage);

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
