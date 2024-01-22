package TCP_Multithreading_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerService extends Thread{
    // Define the atributes of the class
    Socket client;
    int billPrice = 0;
    ArrayList<String> foodOptions = new ArrayList<>();
    ArrayList<Integer> foodPrices = new ArrayList<>();

    // Define the constructor
    public ServerService(Socket client) {
        this.client = client;
    }

    // Define getters and setters
    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        // Add data to the arrayslists
        foodOptions.add("Makarroiak tomatearekin");
        foodOptions.add("Txuleta");
        foodOptions.add("Entsalada");
        foodOptions.add("Baba gorriak");

        foodPrices.add(7);
        foodPrices.add(16);
        foodPrices.add(12);
        foodPrices.add(14);

        try {
            while (true) {
                // Receive the name of the user
                InputStream inputStream = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String userName = reader.readLine();

                // Send the food menu
                String foodMenu = "";
                int counter = 1;
                for (String foodOption : foodOptions){
                    foodMenu += counter + ". " + foodOption + "__";
                    counter++;
                }

                OutputStream outputStream = client.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                writer.write(foodMenu);
                writer.newLine();
                writer.flush();

                // Receive the option of the user and show it in the log console
                DataInputStream dataInput = new DataInputStream(inputStream);
                int selectedOption = dataInput.readInt();

                System.out.println(userName + " - " + foodOptions.get(selectedOption - 1));

                // Update and send the bill price
                DataOutputStream dataOutput = new DataOutputStream(outputStream);
                billPrice += foodPrices.get(selectedOption - 1);
                dataOutput.writeInt(billPrice);
                dataOutput.flush();

                // Ask if the user wants to restart the program or not
                writer.write("Berriz jan nahi duzu?");
                writer.newLine();
                writer.flush();

                // Receive the answer
                String answer = reader.readLine();

                if (answer.equals("E")){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
