import mvc.controller.MainGuiController;

/**
 * contains the main method that launch the whole application
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
