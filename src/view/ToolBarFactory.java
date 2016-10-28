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
    private JButton colorButton;
    

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
        resizeButton  = new JButton("Resize");
        deleteButton  = new JButton("Delete");
        moveButton  = new JButton("Move");
        colorButton  = new JButton("Colors");
        drawLineButton = new JButton("Line");
        drawOvalButton = new JButton("Oval");
        drawRectButton = new JButton("Rectangle");
       
        

        buttons.add(moveButton);
        buttons.add(resizeButton);
        buttons.add(deleteButton);
        buttons.add(colorButton);        
        buttons.add(drawLineButton);
        buttons.add(drawOvalButton);
        buttons.add(drawRectButton);
        
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
