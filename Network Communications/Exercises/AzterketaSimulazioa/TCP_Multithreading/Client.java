package Exercises.AzterketaSimulazioa.TCP_Multithreading;

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
                String line = reader.readLine();
                String[] galderak = line.split(":");
                for (String galdera : galderak) {
                    System.out.println(galdera);
                }

                //Erabiltzailearen datuak teklatu bidez hartu eta zerbitzariari bidali
                Scanner sc = new Scanner(System.in);
                int aukera = sc.nextInt();
                writer.println(aukera);
            }
        } catch (Exception e) {
        }
    }
}