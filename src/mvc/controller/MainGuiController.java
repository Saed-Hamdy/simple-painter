package mvc.controller;

import mvc.model.Model;
import mvc.view.MainGuiView;
import server.Client;
import shapes.Shape;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * the MainGuiController is controller part of the MainGuiView view it's get called by @see .LaunchGui
 * it's responsible to call MainGuiView and other controllers
 */
public class MainGuiController implements Runnable {
    /**
     * singelton object of the MainGuiView
     */
    private MainGuiView mainGuiView;
    private Thread listenThread, runThread;
    private Client client = new Client("a7a", "192.168.1.106", 8017);
    private static MainGuiController mainGuiController;

    public MainGuiController() {
        listen();
        mainGuiController = this;
        // load model
        Model.getModel();


        // load all views
        mainGuiView = MainGuiView.getMainGuiView();

        // show the up after it has finished setting up the gui components
        mainGuiView.setVisible(true);

        // load all controllers
        new ToolBarController();
        new MenuBarController();

        // controllers methods
        addListners();
    }

    /**
     * add all the listeners to the MainGuiView view
     */
    void addListners() {
        mainGuiView.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
    }

    /**
     * get called when user try to closes the window
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        mainGuiView.showConfirm("sure?");
        // TODO
        if (client != null) {
            System.out.println("closing socket");
            client.close();
        }
    }

    public void startListening(String clientUsername, String clientAddress, int clientPort) {
        listenThread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        System.out.println("something has received");
                        System.out.println(client.receive1());
                        System.out.println(client.receive());
                        // mainGuiView.showError(client.receive().toString());
//                        List<Shape> shapes = (List<Shape>) client.receive();
//                        if (shapes != null) {
//                            Model.getModel().setShapes(shapes);
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        listenThread.start();
    }

    public void sendShapes() {
        System.out.println("sending shapes");
        client.send("hi".getBytes());
        System.out.println("hi sended");
        ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(Model.getModel().getShapes());
            byte[] data = baos.toByteArray();
            client.send(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MainGuiController getMainGuiController() {
        return mainGuiController;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        listen();
    }

    void listen() {

        boolean connected = client.connect();
        if (!connected) {
            System.err.println("Connection failed!");
            System.out.println("Connection failed!");
        }

        System.out.println("Connection successful");
        // System.out.println("Connecting to " + clientAddress + ":" + clientPort + "...");

        listenThread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        System.out.println("something has received");
                        System.out.println(client.receive1());
                        System.out.println(client.receive());
                        // mainGuiView.showError(client.receive().toString());
//                        List<Shape> shapes = (List<Shape>) client.receive();
//                        if (shapes != null) {
//                            Model.getModel().setShapes(shapes);
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        listenThread.start();
    }
}
