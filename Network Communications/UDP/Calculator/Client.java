package UDP.Calculator;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final int serverPort = 12345;
        while (true) {
            try {
                DatagramSocket socket = new DatagramSocket();

                InetAddress serverAddress = InetAddress.getByName("192.168.65.6");// Server on Localhost
                Scanner in = new Scanner(System.in);
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                DataOutputStream dataOutput = new DataOutputStream(byteStream);
                System.out.println("Sartu mezua:");
                int message = in.nextInt();
                dataOutput.writeInt(message);

                byte[] data = byteStream.toByteArray();

                DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
                socket.send(packet);

                // Receive the response from the server
                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);

                String serverResponse = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println("Server says: " + serverResponse);

                // Close the socket when done
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}