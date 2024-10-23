import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private int listeningPort;
    private static final int maxBytes = 1024;
    private byte buffer[] = new byte[maxBytes];

    public UDPServer(int listeningPort) {
        this.listeningPort = listeningPort;
    }

    public UDPServer() {
        this.listeningPort = 70;
    }

    public void launch() throws IOException {
        DatagramSocket socket = new DatagramSocket(listeningPort);
        System.out.println("Server started on port: " + listeningPort);

        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            // TODO: add UTF_8 encoding

            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();

            System.out.println("Received from " + clientAddress + ":" + clientPort + " - " + received);
        }
    }

    // TODO: add toString method
    public static void main(String[] args) throws IOException {
        int port = 70;
        if (args.length > 0) {
            UDPServer server = new UDPServer(port);
            server.launch();
        } else {
            UDPServer server = new UDPServer();
            server.launch();
        }
    }
}

