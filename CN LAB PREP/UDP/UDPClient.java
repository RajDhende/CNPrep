package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter a message to send to the server (type 'exit' to quit):");

            while (true) {
                System.out.print("Client: ");
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                byte[] sendBuffer = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer, sendBuffer.length,
                        serverAddress, 9876);
                clientSocket.send(sendPacket);
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Server response: " + response);
            }

            clientSocket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
