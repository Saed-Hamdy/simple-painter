package mvc.view;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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
        exitMenuItem = new JMenuItem("Exit");
        loadMenuItem = new JMenuItem("Load");
        saveMenuItem = new JMenuItem("Save");
    }

    private void buildMenuBar() {
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);
    }

    private void assignShortcuts() {
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
    }
}
