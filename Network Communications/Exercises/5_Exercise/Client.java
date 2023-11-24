import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException {
        // Define the server address and por
        final String serverAddress = "192.168.65.X";
        final int port = 12345;

        // Try to connect to the server
        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            ObjectInputStream objectInput = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);

            // Create the student object
            Student student = new Student("Gorka", 19, (float)350.6);

            // Send the object to the server
            objectOutput.writeObject(student);
            objectOutput.flush();

            // Receive the modified object of the server
            Student changedStudent = (Student)objectInput.readObject();

            // Show the object
            System.out.println(changedStudent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}