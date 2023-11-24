import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws ClassNotFoundException {
        final int port = 12345;
        int id = 1;

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
            
                ObjectInputStream objectInput = new ObjectInputStream(inputStream);
                ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
                
                // Receive the student object of the client
                Student student = (Student)objectInput.readObject();

                // Modify the object and increment the id variable
                student.setId(id);
                id++;

                // Return the modified object to the client
                objectOutput.writeObject(student);
                objectOutput.flush();

                // Close the client connection
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}