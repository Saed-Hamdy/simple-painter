package mvc.view;

import mvc.controller.PainterPanelController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class MainGui extends JFrame {
    private static JFrame mainGui;
    private static Operation operation;

    private JPanel painterPanel;
    private JPanel toolBarPanel;
    private JMenuBar menuBar;

    public enum Operation {
        DrawOval, DrawLine, Resize, Move, Fill, DrawRect;
    }

    public static Operation getOperation() {
        return operation;
    }

    public static void setOperation(Operation operation) {
        MainGui.operation = operation;
    }

    private MainGui() {
        setVisible(true);
        this.setTitle("Painter");
        this.setSize(1000, 700);
        this.setResizable(false);
        initComponents();
        operation = Operation.DrawLine;
    }

    public static JFrame getMainGui() {
        if(mainGui == null) {
            mainGui = new MainGui();
        }
        return mainGui;
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        menuBar = MenuBarFactory.getMenuBar();
        toolBarPanel = ToolBarFactory.getToolBarPanel();
        painterPanel = PainterPanelController.getPainterPanel();

        setJMenuBar(menuBar);
        add(toolBarPanel);
        add(painterPanel);

        FlowLayout flowLayout = (FlowLayout) toolBarPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        toolBarPanel.setBackground(Color.darkGray);
        add(toolBarPanel, BorderLayout.NORTH);
    }


    private void showError(String message) {
        JOptionPane.showConfirmDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
