package UDP_1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        // Define the port for the connection
        final int serverPort = 12345;

        // Define the menu and its addresses
        ArrayList<String> services = new ArrayList<>();
        services.add("Gehiketa kalkulatzeko zerbitzua");
        services.add("Kenketa kalkulatzeko zerbitzua");
        services.add("Biderketa kalkulatzeko zerbitzua");
        services.add("Zatiketa kalkulatzeko zerbitzua");
        services.add("Berreketa kalkulatzeko zerbitzua");
        services.add("Erroketa kalkulatzeko zerbitzua");

        ArrayList<String> addresses = new ArrayList<>();
        addresses.add("IP: 192.168.65.1\nPORT: 8080");
        addresses.add("IP: 192.168.65.2\nPORT: 8070");
        addresses.add("IP: 192.168.65.3\nPORT: 8060");
        addresses.add("IP: 192.168.65.4\nPORT: 8050");
        addresses.add("IP: 192.168.65.5\nPORT: 8040");
        addresses.add("IP: 192.168.65.6\nPORT: 8030");


        try {
            // Create the socket for the connection
            DatagramSocket socket = new DatagramSocket(serverPort);

            // Wair for a connection
            System.out.println("Zerbitzaria konexioaren zain dago...");
            byte[] buffer = new byte[1024];
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivedPacket);
            String receivedMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            System.out.println(receivedMessage);

            // Send the services menu
            String servicesMenu = "";
            int counter = 1;
            for(String service : services){
                servicesMenu += counter + "." + service + "\n";
                counter++;
            }
            byte[] data = servicesMenu.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, receivedPacket.getAddress(), receivedPacket.getPort());
            socket.send(packet);

            // Receive the selected option
            buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            String selectedOption = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Clien selected option: " + selectedOption);

            // Get the index+1 of the selected option
            Integer aukera = 1;
            try {
                aukera = Integer.valueOf(selectedOption);
            } catch (Exception e) {
                counter = 1;
                for(String service : services){
                    if(service.equals(selectedOption)){
                        aukera = counter;
                    }
                    counter++;
                }
            }

            // Send the address of the service
            data = addresses.get(aukera-1).getBytes();
            packet = new DatagramPacket(data, data.length, receivedPacket.getAddress(), receivedPacket.getPort());
            socket.send(packet);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}