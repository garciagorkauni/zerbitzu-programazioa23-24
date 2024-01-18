package UDP.Calculator;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        final int port = 12345;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        
        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Server is listening on port " + port);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivedPacket);

                Integer receivedNumber = ByteBuffer.wrap(receivedPacket.getData()).getInt();
                // String arrayth = new String(receivedPacket.getData());
                // Integer receivedNumber = Integer.parseInt(arrayth.trim());
                numbers.add(receivedNumber);
                System.out.println("Received number:" + receivedNumber);


                // Respond to the client
                String responseMessage = "";
                if(receivedNumber != 0){
                    responseMessage = "Number " + receivedNumber + " received! Send 0 to sum all.";
                } else {
                    Integer totalSum = 0;
                    for (int i = 0; i < numbers.size(); i++) {
                        totalSum += numbers.get(i);
                    }
                    responseMessage = "The total sum of the received numbers is " + totalSum;
                    System.out.println("Total sum requested, answer is: " + totalSum);
                    numbers = new ArrayList<Integer>();
                }
                byte[] responseData = responseMessage.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                        receivedPacket.getAddress(), receivedPacket.getPort());
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}