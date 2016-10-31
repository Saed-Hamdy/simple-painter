package mvc.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class MenuBarFactory {
    private static JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu toolsMenu;
    private JMenu helpMenu;
    private JMenuItem exitMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem redoMenuItem;
    private JMenuItem undoMenuItem;
    private JMenuItem addPluginMenuItem;
    private JMenuItem openMenuItem;


    private MenuBarFactory() {
        menuBar = new JMenuBar();
        initComponent();
        assignShortcuts();
        buildMenuBar();
    }

    public static JMenuBar getMenuBar() {
        if (menuBar == null) {
            new MenuBarFactory();
        }
        return menuBar;
    }

    private void initComponent() {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        toolsMenu = new JMenu("Tools");
        helpMenu = new JMenu("Help");


        menuBar = new JMenuBar();
        saveMenuItem = new JMenuItem("Save");
        openMenuItem = new JMenuItem("Open");
        loadMenuItem = new JMenuItem("Load");
        exitMenuItem = new JMenuItem("Exit");

        undoMenuItem = new JMenuItem("Undo");
        redoMenuItem = new JMenuItem("Redo");

        addPluginMenuItem = new JMenuItem("Add plug-in");

        try {
            Image saveIcon = ImageIO.read(getClass().getResource("/resources/menubar/fileMenu/saveIcon.png"));
            Image loadIcon = ImageIO.read(getClass().getResource("/resources/menubar/fileMenu/loadIcon.png"));
            Image exitIcon = ImageIO.read(getClass().getResource("/resources/menubar/fileMenu/exitIcon.png"));

            Image undoIcon = ImageIO.read(getClass().getResource("/resources/menubar/editMenu/undoIcon.png"));
            Image redoIcon = ImageIO.read(getClass().getResource("/resources/menubar/editMenu/redoIcon.png"));

            Image addPluginIcon = ImageIO.read(getClass().getResource("/resources/menubar/toolsMenu/addPluginIcon.png"));

            Image openIcon = ImageIO.read(getClass().getResource("/resources/menubar/fileMenu/openIcon.png"));

            saveMenuItem.setIcon(new ImageIcon(saveIcon));
            openMenuItem.setIcon(new ImageIcon(openIcon));
            loadMenuItem.setIcon(new ImageIcon(loadIcon));
            exitMenuItem.setIcon(new ImageIcon(exitIcon));

            undoMenuItem.setIcon(new ImageIcon(undoIcon));
            redoMenuItem.setIcon(new ImageIcon(redoIcon));

            addPluginMenuItem.setIcon(new ImageIcon(addPluginIcon));
        } catch (IOException ex) {
        }
    }

    private void buildMenuBar() {
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);

        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);

        toolsMenu.add(addPluginMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);
    }

    private void assignShortcuts() {
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));

        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
    }
}
