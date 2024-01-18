package Exercises.AzterketaSimulazioa.UDP;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    public static void main(String[] args) {
        //Portua definitu
        int port = 12345;
        try {
            //Zerbitzariaren socketa sortu
            DatagramSocket socketServer = new DatagramSocket(port);
            
            //Buffer bat sortu datuak jasotzeko
            byte[] buffer = new byte[500];
            
            //DatagramPacket objetu bat sortu, bezeroaren datuak jasotzeko
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            socketServer.receive(receivedPacket);
            
            String receivedMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            
            String[] katetoak = receivedMessage.split("-");
            
            double kateto1 =  Double.parseDouble(katetoak[0]);
            double kateto2 = Double.parseDouble(katetoak[1]);
            
            double hipotenusa = Math.sqrt(kateto1 * kateto1 + kateto2 * kateto2);
            
            //Bezeroari datuak bidaltzeko idazketa-kanala sortu
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            DataOutputStream dataStream = new DataOutputStream(byteStream);
            
            //Bidali nahi dugun datua byte array batera pasa
            dataStream.writeDouble(hipotenusa);
            byte[] erantzuna = byteStream.toByteArray();

            //Packeta sortu
            DatagramPacket erantzunaPacket = new DatagramPacket(erantzuna, erantzuna.length, receivedPacket.getAddress(), receivedPacket.getPort());
            
            //Packeta bidali
            socketServer.send(erantzunaPacket);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}