import mvc.controller.MainGuiController;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class LaunchGui {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGuiController();
            }
        });
    }
}
