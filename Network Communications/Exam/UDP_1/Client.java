package UDP_1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Define the port of the server
        final int serverPort = 12345;

        try {
            // Define the address of the server 
            InetAddress serverAddress = InetAddress.getByName("localhost");
            // Create the socket for the connection
            DatagramSocket socket = new DatagramSocket();

            // Send the connection
            String connMsg = "Bezeroa konektatuta";
            byte[] connectionMessage = connMsg.getBytes();
            DatagramPacket packet = new DatagramPacket(connectionMessage, connectionMessage.length, serverAddress, serverPort);
            socket.send(packet);

            // Receive the services menu and show it
            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            String servicesMenu = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(servicesMenu);

            // Ask for the wanted option and send it
            Scanner in = new Scanner(System.in);
            System.out.print("Zure aukera: ");
            String selectedOption = in.nextLine();

            byte[] data = selectedOption.getBytes();
            packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
            socket.send(packet);

            // Receive the ip and port of the selected service
            buffer = new byte[1024];
            responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            String serviceAddress = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Service address:\n" + serviceAddress);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
