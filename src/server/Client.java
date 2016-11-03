package server;

/**
 * Created by ahmedyakout on 11/1/16.
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    private String address;
    private int port;
    private InetAddress ip;

    private DatagramSocket socket;
    private Thread sendThread, closeThread;

    public Client(String clientAddress, int clientPort) {
        address = clientAddress;
        port = clientPort;
    }

    public boolean connect() {
        try {
            System.out.println("connecting..");
            socket = new DatagramSocket();
            ip = InetAddress.getByName(address);
            send("Request".getBytes());
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String receive() {
        byte[] data = new byte[6400];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = new String(packet.getData());
        return message;
    }


    public void send(byte[] data) {
        sendThread = new Thread("Send") {
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        sendThread.start();
    }

    public void close() {
        closeThread = new Thread() {
            public void run() {
                synchronized (socket) {
                    socket.close();
                }
            }
        };
        closeThread.start();
    }

}
