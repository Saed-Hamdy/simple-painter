package server;

/**
 * Created by ahmedyakout on 11/1/16.
 */
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    private String username, address;
    private int port;
    private InetAddress ip;

    private DatagramSocket socket;
    private Thread sendThread, closeThread;

    private int ID = -1;

    public Client(String clientName, String clientAddress, int clientPort) {
        username = clientName;
        address = clientAddress;
        port = clientPort;
    }

    public boolean connect() {
        try {
            System.out.println("connecting..");
            socket = new DatagramSocket();
            ip = InetAddress.getByName(address);
            send("some one has connected".getBytes());
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Object receive() {
        try {
            byte[] recvBuf = new byte[6400];
            DatagramPacket packet = new DatagramPacket(recvBuf,
                    recvBuf.length);
            socket.receive(packet);
            // int byteCount = packet.getLength();
            ByteArrayInputStream byteStream = new
                    ByteArrayInputStream(recvBuf);
            ObjectInputStream is = new
                    ObjectInputStream(new BufferedInputStream(byteStream));
            Object o = is.readObject();
            is.close();
            return(o);
        }
        catch (IOException e) {
            System.err.println("Exception:  " + e);
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String receive1() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = new String(packet.getData());
        System.out.println(message);
        if (message.startsWith("/c/")) {
            this.ID = Integer.parseInt(message.substring(3, message.length()).trim());
        }
        return message;
    }


    public void send(byte[] data) {
        System.out.println("sending data");
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

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public int getID() {
        return ID;
    }

    public void setID(int newID) {
        ID = newID;
    }

    public void setUsername(String name) {
        username = name;
    }

}
