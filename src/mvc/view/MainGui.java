package mvc.view;

import mvc.controller.PainterPanelController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class MainGui extends JFrame {
    private static MainGui mainGui;
    private static Operation operation;
    private static String otherOperation;

    private JPanel painterPanel;
    private JPanel toolBarPanel;
    private JMenuBar menuBar;

    public enum Operation {
        DrawOval,
        DrawLine,
        DrawRect,
        Resize,
        Delete,
        Clear,
        Move,
        Fill,
        Other;
    }

    private MainGui() {
        setVisible(true);
        this.setTitle("Painter");
        this.setSize(1000, 700);
        this.setResizable(false);
        initComponents();
        operation = Operation.DrawLine;
    }

    public static MainGui getMainGui() {
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


    public void showError(String message) {
        JOptionPane.showConfirmDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }



    public void showConfirm(String message) {
        int selectedValue = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION);
        if (selectedValue == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


    public static Operation getOperation() {
        return operation;
    }

    public static void setOperation(Operation operation) {
        MainGui.operation = operation;
    }

    public static void setOtherOperation(String otherOperation) {
        MainGui.otherOperation = otherOperation;
    }

    public static String getOtherOperation() {
        return otherOperation;
    }
}
