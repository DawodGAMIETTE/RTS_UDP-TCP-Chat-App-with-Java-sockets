import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Missing arguments \"server\" or \"port\".");
            return;
        }

        String serverAddress = args[0];
        int serverPort = Integer.parseInt(args[1]);

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverInetAddress = InetAddress.getByName(serverAddress);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;

        System.out.println("Enter the text to send to server.");

        while ((inputLine = userInput.readLine()) != null) {
            if ("exit".equalsIgnoreCase(inputLine)) {
                break;
            }

            byte[] buffer = inputLine.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverInetAddress, serverPort);
            socket.send(packet);
        }

        socket.close();
    }
}