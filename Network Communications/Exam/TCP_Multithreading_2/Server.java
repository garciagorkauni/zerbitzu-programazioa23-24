package TCP_Multithreading_2;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        // Define the port for the connection and create the socket
        final int port = 12345;

        try {
            ServerSocket server = new ServerSocket(port);

            // Start a thread for each client
            while (true) {
                Socket client = server.accept();
                ServerService thread = new ServerService(client);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
