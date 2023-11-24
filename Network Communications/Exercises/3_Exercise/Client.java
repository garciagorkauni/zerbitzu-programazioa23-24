import java.io.*;
import java.net.*;
import java.util.Scanner;

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
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            // Ask for the data to send
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scan.nextLine();
            System.out.print("Enter your age: ");
            int age = scan.nextInt();

            // Send the data to the server
            writer.write(name);
            dataOutputStream.writeInt(age);
            dataOutputStream.flush();

            // Receive the respond of the server
            System.out.println(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}