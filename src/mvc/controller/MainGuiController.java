package mvc.controller;

import mvc.model.Model;
import mvc.view.MainGuiView;

/**
 * the MainGuiController is controller part of the MainGuiView view it's get called by @see .LaunchGui
 * it's responsible to call MainGuiView and other controllers
 */
public class MainGuiController {
    /**
     * singelton object of the MainGuiView
     */
    private MainGuiView mainGuiView;

    public MainGuiController() {
        // load model
        Model.getModel();


        // load all views
        mainGuiView = MainGuiView.getMainGuiView();

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
    }
}
