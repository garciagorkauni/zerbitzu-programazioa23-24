package Exercises.AzterketaSimulazioa.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        //Zerbitzariaren portua definitu
        int port = 12345;
        try {
            //Bezeroaren socketa sortu datuak bidali ahal izateko
            DatagramSocket socket = new DatagramSocket();
            //Zerbitzariaren IPa definitu
            InetAddress inetServerAddress = InetAddress.getByName("localhost");

            Scanner sc = new Scanner(System.in);

            //STRING BAT EZ DEN DATU BAT BIDALTZEKO
            /*
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            
            (*)DATU PRIMITIBOAK BIDALTZEKO
            DataOutputStream dataStream = new DataOutputStream(byteStream);
            dataStream.writeInt(zenbakia);

            (*)OBJETUAK BIDALTZEKO
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectOutput.writeObject(myObject);
            
            byte[] data = byteStream.toByteArray();
             */
            //Erabiltzailearen mezua jaso eta byte array batera pasa
            System.out.println("Sartu bi katetoak gioi batekin bananduta");
            String mezua = sc.nextLine();
            byte[] data = mezua.getBytes();

            //DatagramPacketa sortu byte array-arekin, zerbitzariaren ip-a eta portuarekin
            DatagramPacket mezuaPacket = new DatagramPacket(data, data.length, inetServerAddress, port);
            //Packeta bidali
            socket.send(mezuaPacket);

            //Buffer bat sortu datuak jasotzeko 
            byte[] buffer = new byte[500];

            //DatagramPacket objetu bat sortu, zerbitzariaren datuak jasotzeko
            DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
            //Packeta jaso
            socket.receive(receivedPacket);
            //byte array-tik doublera pasatu 
            double hipotenusa = ByteBuffer.wrap(receivedPacket.getData()).getDouble();

            /* 
            (*)STRING BAT JASOTZEKO
            String receivedString = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            
            (*)OBJETU BAT JASOTZEKO
            MyObject receivedObject;
            try (ObjectInputStream objectInput = new ObjectInputStream(new ByteArrayInputStream(receivedPacket.getData()))) {
                receivedObject = (MyObject) objectInput.readObject();
            
            */
            System.out.println("triangeluaren hipotenusa: " + hipotenusa);
        } catch (Exception e) {
        }
    }
}