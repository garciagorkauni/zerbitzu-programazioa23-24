package MultiCast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Definimos la dirección multicast y el puerto
        InetAddress direccionMulticast = InetAddress.getByName("225.0.0.1");
        int puerto = 54321;

        // Creamos el socket de escucha
        MulticastSocket escuchaSocket = new MulticastSocket(puerto);

        // Creamos el grupo con la dirección multicast y el puerto
        InetSocketAddress grupo = new InetSocketAddress(direccionMulticast, puerto);

        // Definimos la interfaz de red
        NetworkInterface netIf = NetworkInterface.getByName("bge0");

        // Nos unimos al grupo multicast
        escuchaSocket.joinGroup(new InetSocketAddress(direccionMulticast, 0), netIf);

        // Nos ponemos a la espera de recibir un paquete
        byte[] buf = new byte[1000];
        DatagramPacket paquete = new DatagramPacket(buf, buf.length);
        escuchaSocket.receive(paquete);

        Pertsona receivedObject;
        try (ObjectInputStream objectInput = new ObjectInputStream(new ByteArrayInputStream(paquete.getData()))) {
            receivedObject = (Pertsona) objectInput.readObject();
            System.out.println("Objetua: " + receivedObject.getId() + ": " + receivedObject.getIzena() + " ("
                    + receivedObject.getAdina() + ")");
        } catch (IOException e) {
        }

        // Dejamos el grupo
        escuchaSocket.leaveGroup(grupo, netIf);

        escuchaSocket.close();
    }
}