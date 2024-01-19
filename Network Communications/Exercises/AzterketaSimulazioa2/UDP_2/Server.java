package Exercises.AzterketaSimulazioa2.UDP_2;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    public static void main(String[] args) {
        int port = 12345;
        
        try {
            DatagramSocket socketServidor = new DatagramSocket(port);
            
            byte[] buffer = new byte[2048];
            
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            socketServidor.receive(receivedPacket);
            
            // Deserialize the object
            Ikaslea ikaslea;
            try (ObjectInputStream objectInput = new ObjectInputStream(new ByteArrayInputStream(receivedPacket.getData()))) {
                ikaslea = (Ikaslea) objectInput.readObject();
            }
            
            System.out.println("Ikaslearen datuak:\n" + ikaslea);
        } catch (Exception e) {
        }
    }
}