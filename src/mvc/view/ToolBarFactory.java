package mvc.view;

import javax.swing.*;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class ToolBarFactory {
    private static JPanel toolBarPanel;
    private JButton drawLineButton;
    private JButton drawOvalButton;
    private JButton drawRectButton;

    private ToolBarFactory() {
        toolBarPanel = new JPanel();
        initComponents();
        buildToolBar();
    }

    public static JPanel getToolBarPanel() {
        if (toolBarPanel == null) {
            new ToolBarFactory();
        }
        return toolBarPanel;
    }

    private void initComponents() {
        drawLineButton = new JButton("Line");
        drawOvalButton = new JButton("Oval");
        drawRectButton = new JButton("Rectangle");
    }

    private void buildToolBar() {
        toolBarPanel.add(drawLineButton);
        toolBarPanel.add(drawOvalButton);
        toolBarPanel.add(drawRectButton);
    }

}
