package server;


import java.net.InetAddress;

/**
 * Created by ahmedyakout on 11/1/16.
 */
public class ServerClient {
    private InetAddress address;
    private int port;

    public ServerClient(InetAddress clientAddress, int clientPort) {
        address = clientAddress;
        port = clientPort;
    }
    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
