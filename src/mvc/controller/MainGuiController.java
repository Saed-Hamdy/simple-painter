package mvc.controller;

import mvc.model.Model;
import mvc.view.MainGui;

import javax.swing.*;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class MainGuiController {
    private MainGui mainGui;

    public MainGuiController() {
        // load all views
        mainGui = MainGui.getMainGui();

        // load all controllers
        new ToolBarController();
        new MenuBarController();

        // load model
        Model.getModel();

        // controllers methods
        addListners();
    }

    void addListners() {
        mainGui.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        showConfirm("sure?");
    }

    private void showConfirm(String message) {
        int selectedValue = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION);
        if (selectedValue == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
