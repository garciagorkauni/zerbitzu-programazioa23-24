package Exercises.AzterketaSimulazioa2.UDP_2;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) {
        int port = 12345;
        
        try {
            DatagramSocket socketCliente = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            
            Ikaslea ikaslea = new Ikaslea("izena", "abizena", 20, "DAM", true);
            objectStream.writeObject(ikaslea); 
            byte[] data = byteStream.toByteArray();
            
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socketCliente.send(packet);
            System.out.println("Ikaslearen fitxa bidalita");
        } catch (Exception e) {
        }
    }
}