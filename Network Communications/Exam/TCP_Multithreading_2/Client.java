package TCP_Multithreading_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // Define the variables for the connection
        final String serverAddress = "127.0.0.1";
        final int port = 12345;

        // Try to create the socker for the connection
        try (Socket socket = new Socket(serverAddress, port)) {
            Scanner in = new Scanner(System.in);
            System.out.print("Zure izena: ");
            String userName = in.nextLine();

            while (true) {
                // Send the user name
                OutputStream outputStream = socket.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

                writer.write(userName);
                writer.newLine();
                writer.flush();

                // Receive the food menu and show it
                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String foodMenu = reader.readLine();
                for(String option : foodMenu.split("__")){
                    System.out.println(option);
                }

                // Ask for the selected option and send it
                System.out.print("Zure aukera (zenbakia): ");
                int aukera = in.nextInt();

                DataOutputStream dataOutput = new DataOutputStream(outputStream);
                dataOutput.writeInt(aukera);
                dataOutput.flush();

                // Receive the bill and show it
                DataInputStream dataInput = new DataInputStream(inputStream);
                int billPrice = dataInput.readInt();
                System.out.println("Zure janariaren kontu totala: " + billPrice + " EUR");

                // Receive the question to follow the program or finish it
                String question = reader.readLine();
                System.out.print(question + " ");

                // Ask for the answer and send it
                String erantzuna = in.next();
                writer.write(erantzuna);
                writer.newLine();
                writer.flush();

                if (erantzuna.equals("E")){
                    break;
                }

                writer.write(erantzuna);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
