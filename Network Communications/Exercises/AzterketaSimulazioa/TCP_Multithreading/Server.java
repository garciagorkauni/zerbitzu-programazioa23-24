package Exercises.AzterketaSimulazioa.TCP_Multithreading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        
        //Portua definitu eta zerbitzariaren socketa sortu 
        int port = 12345;
        ServerSocket server = new ServerSocket(port);
        
        //Zerbitzaria bezeroen zain jarri eta bezeroen konexioak onartu. Konexio bakoitzeko, hari bat sortu
        while(true) {
            Socket client = server.accept();
            ServerService thread = new ServerService(client);
            thread.start();
        }
    }
}