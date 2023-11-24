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
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            
                // Receive the data from client
                String name = reader.readLine();
                int age = dataInputStream.readInt();

                // Send the respond to the client
                if(age >= 18){
                    writer.write(name + " that is " + age + " years old, is of legal age.");
                } else {
                    writer.write(name + " that is " + age + " years old, is not of legal age.");
                }

                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}