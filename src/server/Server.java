package server;

/**
 * Created by ahmedyakout on 11/1/16.
 */
import mvc.view.MainGuiView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server implements Runnable {
    private List<ServerClient> clients = new CopyOnWriteArrayList<>();
    private DatagramSocket socket;
    private int port;
    private Thread runThread, sendThread, receiveThread;


    public Server(int serverPort) {
        port = serverPort;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            return;
        }
        runThread = new Thread(this, "Server");
        runThread.start();
    }

    public void run() {
        MainGuiView.getMainGuiView().showMessage("Server started on port " + port);
        receive();
    }

    private void receive() {
        receiveThread = new Thread("Receive") {
            public void run() {
                while (true) {
                    byte[] data = new byte[6400];
                    DatagramPacket packet = new DatagramPacket(data, data.length);
                    try {
                        socket.receive(packet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String packetString = new String(packet.getData());
                    if (packetString.startsWith("Request")) {
                        clients.add(new ServerClient(packet.getAddress(), packet.getPort()));
                        continue;
                    }
                    sendToClients(packetString);
                }

            }
        };
        receiveThread.start();
    }

    private void sendToClients(String packetString) {
        for (int i = 0; i < clients.size(); i++) {
            ServerClient client = clients.get(i);
            send(packetString.getBytes(), client.getAddress(), client.getPort());
        }
    }

    private void send(byte[] data, InetAddress addressSendTo, int portSendTo) {
        sendThread = new Thread() {
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, addressSendTo, portSendTo);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        sendThread.start();
    }
}
