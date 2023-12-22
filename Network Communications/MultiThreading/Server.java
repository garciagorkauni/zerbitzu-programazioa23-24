package MultiThreading;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket servidor = new ServerSocket(6000);
        ArrayList<PrintWriter> pwak = new ArrayList<>();
        Semaphore sem = new Semaphore(1);

        while (true) {
            Socket cliente = servidor.accept();
            ServerService hilo = new ServerService(cliente, pwak, sem);
            hilo.start();
        }
    }
}

