import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        // Define the server address and por
        final String serverAddress = "192.168.65.X";
        final int port = 12345;

        // Try to connect to the server
        try (Socket socket = new Socket(serverAddress, port)) {
            // Create input and output streams for the server
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // Send a random number to the server
            int number = (int)Math.random()*100;
            dataOutputStream.writeInt(number);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}