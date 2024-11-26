package TCP;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPServer {
    public static void main(String[] args) {
        int port = 5000; 

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept(); 
                System.out.println("New client connected");

                
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                String clientMessage;

                
                while (scanner.hasNextLine()) {
                    clientMessage = scanner.nextLine();
                    System.out.println("Received from client: " + clientMessage);

                    
                    writer.println("Server received: " + clientMessage);

                    
                    if (clientMessage.equalsIgnoreCase("exit")) {
                        System.out.println("Client disconnected");
                        break;
                    }
                }
                socket.close(); 
                scanner.close(); 
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
