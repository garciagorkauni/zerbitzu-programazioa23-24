package Exercises.AzterketaSimulazioa2.TCP_3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        
        //Zerbitzariaren portua eta IPa konexioa egiteko
        int port = 12345;
        String ip = "127.0.0.1";

        try (Socket socket = new Socket(ip, port)) { //Konexioa sortu
            
            //TCPrako idazketa- eta irakurketa-kanalak sortu
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);

            //Ariketaren kodigoa
            while (true) {
                //Zerbitzariak bidaltzen duen mezua prozesatu
                String mezua = reader.readLine();
                String[] lerroak = mezua.split("**");
                for (String lerroa : lerroak) {
                    System.out.println(lerroa);
                }

                //Erabiltzailearen datuak teklatu bidez hartu eta zerbitzariari bidali
                Scanner sc = new Scanner(System.in);
                System.out.print("Zure erantzuna (a/b/c): ");
                String aukera = sc.next().toLowerCase();
                writer.println(aukera);
            }
        } catch (Exception e) {
        }
    }
}