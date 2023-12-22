package MultiThreading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ServerService extends Thread{
    Socket clientSocket = null;
    ArrayList<PrintWriter> pwak = new ArrayList<>();
    Semaphore sem;

    public ServerService(Socket s, ArrayList<PrintWriter> pwak, Semaphore sem){
        this.clientSocket = s;
        this.pwak = pwak;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            //Create input and output streams for the client
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);
            sem.acquire();
            pwak.add(writer);
            sem.release();

            while (true) {
                String clientMessage = reader.readLine();
                if(clientMessage != null){
                    System.out.println( "Message received:"+ clientMessage);

                    // Send the message to the other clients
                    for (PrintWriter pw : pwak) {
                        sem.acquire();
                        pw.println(clientMessage);
                        sem.release();
                    }
                }
            }            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
