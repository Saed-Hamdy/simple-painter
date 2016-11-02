import mvc.controller.MainGuiController;
import server.Client;
import server.Server;
import java.awt.*;

/**
 * contains the main method that launch the whole application
 */
public class LaunchGui {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGuiController();
            }
        });
    }
}
