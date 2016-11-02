package mvc.view;

import mvc.controller.PainterPanelController;

import javax.swing.*;
import java.awt.*;

/**
 * Created on 10/26/16.
 */
public class MainGuiView extends JFrame {
    private static MainGuiView mainGuiView;
    private static Operation operation = Operation.DrawLine;
    private static String otherOperation;

    private JPanel painterPanel;
    private JPanel toolBarPanel;
    private JPanel operationBarPanel;
    private JMenuBar menuBar;

    public enum Operation {
        DrawOval,
        DrawTriangle,
        DrawLine,
        DrawRect,
        Resize,
        Delete,
        Clear,
        Move,
        Fill,
        Select,
        Other;
    }

    private MainGuiView() {
        setVisible(true);
        setTitle("Painter");
        setSize(1000, 700);
        setResizable(true);
        setMinimumSize(this.size());
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
        operationBarPanel=OprationBarFactory.getOprationBarPanel();
        painterPanel = PainterPanelController.getPainterPanel();

        setJMenuBar(menuBar);
        add(toolBarPanel);
        add(operationBarPanel);
        add(painterPanel);

        FlowLayout flowLayout = (FlowLayout) toolBarPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);        
        toolBarPanel.setBackground(Color.darkGray);
        add(toolBarPanel, BorderLayout.NORTH);
        operationBarPanel.setLayout(new BoxLayout(operationBarPanel,BoxLayout.Y_AXIS));
        operationBarPanel.setBackground(Color.darkGray);
        add(operationBarPanel, BorderLayout.WEST);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
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
        MainGuiView.operation = operation;
    }

    public static void setOtherOperation(String otherOperation) {
        MainGuiView.otherOperation = otherOperation;
    }

    public static String getOtherOperation() {
        return otherOperation;
    }
}
