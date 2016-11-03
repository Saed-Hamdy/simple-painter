package mvc.view;

import mvc.controller.MainGuiController;
import mvc.controller.PainterPanelController;
import mvc.model.Model;
import server.Client;
import shapes.*;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.List;

/**
 * Created on 10/26/16.
 */
public class MainGuiView extends JFrame {
    private static MainGuiView mainGuiView;
    private static Operation operation = Operation.DrawLine;
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
        Select,
        Other, DrawTriangle;
    }

    private MainGuiView() {
        // setVisible(true);
        setTitle("Painter");
        setSize(1000, 700);
        setResizable(false);
        setLocation(100, 0);
        initComponents();
    }

    public static MainGuiView getMainGuiView() {
        if(mainGuiView == null) {
            mainGuiView = new MainGuiView();
        }
        return mainGuiView;
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
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showConfirm(String message) {
        int selectedValue = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION);
        if (selectedValue == JOptionPane.YES_OPTION) {
            if (MainGuiController.getMainGuiController().getClient() != null) {
                MainGuiController.getMainGuiController().getClient().close();
            }
            System.exit(0);
        }
    }

    public static Operation getOperation() {
        return operation;
    }

    public static void setOperation(Operation operation) {
        MainGuiView.operation = operation;
    }

    public static void setOtherOperation(String otherOperation) {
        MainGuiView.otherOperation = otherOperation;
    }

    public static String getOtherOperation() {
        return otherOperation;
    }

}
