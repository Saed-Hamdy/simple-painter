package mvc.view;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

/**
 * Created on 10/26/16.
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
    private JButton selectButton;
    private JButton fillShapeButton;
    

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
        selectButton = new JButton("Select");
        fillShapeButton = new JButton("Fill");

        try {
            Image resizeIcon = ImageIO.read(getClass().getResource("/resources/toolbar/resizeIcon.png"));
            Image moveIcon = ImageIO.read(getClass().getResource("/resources/toolbar/moveIcon.png"));
            Image ellipseIcon = ImageIO.read(getClass().getResource("/resources/toolbar/ellipseIcon.png"));
            Image rectIcon = ImageIO.read(getClass().getResource("/resources/toolbar/rectIcon.png"));
            Image lineIcon = ImageIO.read(getClass().getResource("/resources/toolbar/lineIcon.png"));
            Image deleteIcon = ImageIO.read(getClass().getResource("/resources/toolbar/deleteIcon.png"));
            Image colorChooserIcon = ImageIO.read(getClass().getResource("/resources/toolbar/colorChooserIcon.png"));
            Image selectIcon = ImageIO.read(getClass().getResource("/resources/toolbar/selectIcon.png"));
            Image fillIcon = ImageIO.read(getClass().getResource("/resources/toolbar/fillIcon.png"));

            resizeButton.setIcon(new ImageIcon(resizeIcon));
            deleteButton.setIcon(new ImageIcon(deleteIcon));
            moveButton.setIcon(new ImageIcon(moveIcon));
            colorButton.setIcon(new ImageIcon(colorChooserIcon));
            drawLineButton.setIcon(new ImageIcon(lineIcon));
            drawOvalButton.setIcon(new ImageIcon(ellipseIcon));
            drawRectButton.setIcon(new ImageIcon(rectIcon));
            selectButton.setIcon(new ImageIcon(selectIcon));
            fillShapeButton.setIcon(new ImageIcon(fillIcon));

        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

        buttons.add(moveButton);
        buttons.add(resizeButton);
        buttons.add(deleteButton);
        buttons.add(colorButton);        
        buttons.add(drawLineButton);
        buttons.add(drawOvalButton);
        buttons.add(drawRectButton);
        buttons.add(selectButton);
        buttons.add(fillShapeButton);
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
