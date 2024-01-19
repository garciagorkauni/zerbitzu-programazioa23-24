package Exercises.AzterketaSimulazioa2.TCP_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        
        //Portua definitu eta zerbitzariaren socketa sortu 
        int port = 12345;
        ServerSocket server = new ServerSocket(port);

        //Bezero kopuruaren limitea eskatu
        Scanner in = new Scanner(System.in);
        System.out.print("Bezeroen konexio limitea: ");
        int konexioKopurua = in.nextInt();
        int counter = 0;

        //Zerbitzaria bezeroen zain jarri eta bezeroen konexioak onartu. Konexio bakoitzeko, hari bat sortu
        while(counter < konexioKopurua) {
            Socket client = server.accept();
            ServerService thread = new ServerService(client);
            thread.start();
            counter++;
        }
    }
}
