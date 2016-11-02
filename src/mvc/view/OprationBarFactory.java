package mvc.view;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OprationBarFactory extends JPanel {
    private static OprationBarFactory operationBarPanel;
    private List<JButton> operationButtons;
    private JButton resizeButton;
    private JButton deleteButton;
    private JButton moveButton;
    private JButton colorButton;
    private JButton selectButton;
    private JButton fillShapeButton;

    private OprationBarFactory() {
        initComponents();
        buildToolBar();
    }

    public static OprationBarFactory getOprationBarPanel() {
        if (operationBarPanel == null) {
            operationBarPanel = new OprationBarFactory();
        }
        return operationBarPanel;
    }

    private void initComponents() {
        operationButtons = new ArrayList<>();
        resizeButton = new JButton("Resize");
        deleteButton = new JButton("Delete");
        moveButton = new JButton("Move");
        colorButton = new JButton("Colors");
        selectButton = new JButton("Select");
        fillShapeButton = new JButton("Fill");
        try {
            Image resizeIcon = ImageIO.read(getClass().getResource("/resources/toolbar/resizeIcon.png"));
            Image moveIcon = ImageIO.read(getClass().getResource("/resources/toolbar/moveIcon.png"));
            Image deleteIcon = ImageIO.read(getClass().getResource("/resources/toolbar/deleteIcon.png"));
            Image colorChooserIcon = ImageIO.read(getClass().getResource("/resources/toolbar/colorChooserIcon.png"));
            Image selectIcon = ImageIO.read(getClass().getResource("/resources/toolbar/selectIcon.png"));
            Image fillIcon = ImageIO.read(getClass().getResource("/resources/toolbar/fillIcon.png"));

            resizeButton.setIcon(new ImageIcon(resizeIcon));
            deleteButton.setIcon(new ImageIcon(deleteIcon));
            moveButton.setIcon(new ImageIcon(moveIcon));
            colorButton.setIcon(new ImageIcon(colorChooserIcon));
            selectButton.setIcon(new ImageIcon(selectIcon));
            fillShapeButton.setIcon(new ImageIcon(fillIcon));

        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

        operationButtons.add(moveButton);
        operationButtons.add(resizeButton);
        operationButtons.add(deleteButton);
        operationButtons.add(colorButton);
        operationButtons.add(selectButton);
        operationButtons.add(fillShapeButton);
    }

    private void buildToolBar() {
        for (JButton button : operationButtons) {
            add(button);
            revalidate();
            repaint();
        }
    }

}