package TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(hostname, port)) {
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Hello Server!");

            if (scanner.hasNextLine()) {
                String serverResponse = scanner.nextLine();
                System.out.println("Server replied: " + serverResponse);
            }

            writer.println("exit");

            scanner.close();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
