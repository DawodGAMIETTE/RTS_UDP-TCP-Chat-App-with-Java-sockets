package ensea.rts.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    /**
     * Main method to run the UDPClient.
     *
     * @param args Command line arguments: server address and port.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Missing arguments \"server address\" or \"port\".");
            return;
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);

            System.out.println("Connected to server " + serverAddress + " on port " + serverPort);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;

            System.out.println("Enter text to send to the server (type 'exit' to quit):");

            while ((inputLine = userInput.readLine()) != null) {
                if ("exit".equalsIgnoreCase(inputLine)) {
                    break;
                }

                byte[] buffer = inputLine.getBytes(StandardCharsets.UTF_8);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverInetAddress, serverPort);
                socket.send(packet);
            }
            socket.close();
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}