package server;


import java.net.InetAddress;

/**
 * Created by ahmedyakout on 11/1/16.
 */
public class ServerClient {
    private String username;
    private InetAddress address;
    private int port;
    private final int ID;
    private int attempt = 0;

    public ServerClient(String clientName, InetAddress clientAddress, int clientPort, final int clientID) {
        username = clientName;
        address = clientAddress;
        port = clientPort;
        ID = clientID;
    }

    public int getID() {
        return ID;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public int getAttempts() {
        return attempt;
    }

    public void incrementAttempts() {
        attempt++;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }
}
