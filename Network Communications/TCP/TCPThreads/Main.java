package TCP.TCPThreads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    final static int INITIAL_PORT = 12345;
    final static int NUMBER_OF_CHATS = 5;
    final static int[] ports = new int[NUMBER_OF_CHATS];

    public static void main(String[] args) {
        for (int i = 0, port = INITIAL_PORT; i < NUMBER_OF_CHATS; i++, port++) {
            ports[i] = port;
        }
        int counter = 0;
        while(true){
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Server is waiting for a connection on " 
                + localHost.getHostAddress() + ":" + ports[counter]);
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(ports[counter]);
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from: " 
                    + clientSocket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
