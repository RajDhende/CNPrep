package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveBuffer = new byte[1024];

            System.out.println("UDP Server is running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);
                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Received from client: " + receivedData);

                String responseData = "Echo: " + receivedData;
                byte[] sendBuffer = responseData.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendBuffer, sendBuffer.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
