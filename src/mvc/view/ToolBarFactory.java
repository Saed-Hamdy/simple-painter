package mvc.view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class ToolBarFactory extends JPanel{
    private static ToolBarFactory toolBarPanel;
    private List<JButton> buttons;
    private JButton drawLineButton;
    private JButton drawOvalButton;
    private JButton drawRectButton;
    private JButton resizeButton;
    private JButton deleteButton;
    private JButton moveButton;

    private ToolBarFactory() {
        initComponents();
        buildToolBar();
    }

    public static ToolBarFactory getToolBarPanel() {
        if (toolBarPanel == null) {
            toolBarPanel = new ToolBarFactory();
        }
        return toolBarPanel;
    }

    private void initComponents() {
        buttons = new ArrayList<>();
        drawLineButton = new JButton("Line");
        drawOvalButton = new JButton("Oval");
        drawRectButton = new JButton("Rectangle");
        resizeButton  = new JButton("Resize");
        deleteButton  = new JButton("Delete");
        moveButton  = new JButton("Move");
        

        buttons.add(drawLineButton);
        buttons.add(drawOvalButton);
        buttons.add(drawRectButton);
        buttons.add(deleteButton);
        buttons.add(resizeButton);
        buttons.add(moveButton);
        
    }

    private void buildToolBar() {
        for(JButton button : buttons) {
            add(button);
            revalidate();
            repaint();
        }
    }

    public JButton addNewShapeButton(String shapeName) {
        JButton newButton = new JButton(shapeName);
        buttons.add(newButton);
        buildToolBar();
        return newButton;
    }

}
