package MultiCast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try{
        // Definimos la dirección multicast y el puerto
        InetAddress direccionMulticast = InetAddress.getByName("225.0.0.1");
        int puerto = 54321;

        Pertsona pertsona = new Pertsona(1, "Fernando Alonso", 33);

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput = new ObjectOutputStream(byteStream);

        objectOutput.writeObject(pertsona);

        byte[] data = byteStream.toByteArray();
        // Creamos el socket de envío
        MulticastSocket envioSocket = new MulticastSocket();
        
        // Creamos el grupo con la dirección multicast y el puerto
        InetSocketAddress grupo = new InetSocketAddress(direccionMulticast, puerto);

        DatagramPacket paquete = new DatagramPacket(data, data.length, grupo);
        
        envioSocket.send(paquete);
        envioSocket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

