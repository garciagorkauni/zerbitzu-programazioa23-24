package UDP.BasicExample;

import java.net.*;

public class Client { 
    public static void main(String[] args) { 
        final int serverPort = 12345;    
        try {
            DatagramSocket socket = new DatagramSocket();
    
            InetAddress serverAddress = InetAddress.getByName("localhost");//Server on Localhost
            String message = "Hello, server!";
            byte[] data = message.getBytes();
    
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