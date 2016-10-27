package mvc.controller;

import mvc.model.Model;
import mvc.view.MainGui;
import mvc.view.MenuBarFactory;
import mvc.view.ToolBarFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class MenuBarController {
    private JMenuBar menuBar;
    private JFileChooser fileChooser;
    private MainGui mainGui;
    private ToolBarFactory toolBarPanel;

    public MenuBarController() {
        menuBar = MenuBarFactory.getMenuBar();
        mainGui = MainGui.getMainGui();
        toolBarPanel = ToolBarFactory.getToolBarPanel();
        addListners();
    }

    private void addListners() {
        Component[] components = menuBar.getComponents();
        for (Component component : components) {
            Component[] menuComponents = ((JMenu) component).getMenuComponents();
            for (Component menuComponent : menuComponents) {
                JMenuItem menuItem = (JMenuItem) menuComponent;
                switch (menuItem.getText()) {
                    case "Load":
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                fileChooser = new JFileChooser();
                                FileFilter classFilesFilter = new FileFilter() {
                                    @Override
                                    public boolean accept(File f) {
                                        if (f.isDirectory()) {
                                            return true;
                                        }
                                        String s = f.getName();
                                        return s.endsWith(".class") || s.endsWith(".CLASS");
                                    }

                                    @Override
                                    public String getDescription() {
                                        return ".class, .CLass";
                                    }
                                };
                                fileChooser.addChoosableFileFilter(classFilesFilter);
                                int returnValue = fileChooser.showOpenDialog(MainGui.getMainGui());
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    String fileName = fileChooser.getSelectedFile().getName();
                                    fileName = fileName.replace(".class", "");
                                    System.out.println(fileName);

                                    String directory = fileChooser.getCurrentDirectory().toString();
                                    // directory = directory.replaceAll("/", ".").replaceFirst(".", "");
                                    directory = directory.replace("/shapes", "");
                                    System.out.println(directory);

                                    File file = new File(directory);
                                    URL url;
                                    try {
                                        url = file.toURI().toURL();
                                        URL[] urls = new URL[]{url};
                                        ClassLoader classLoader = new URLClassLoader(urls);
                                        Class  aClass = classLoader.loadClass("shapes." + fileName);
                                        addNewShape(fileName, aClass);
                                    } catch (MalformedURLException e1) {
                                        e1.printStackTrace();
                                        mainGui.showError(e1.toString());
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                        mainGui.showError(e1.toString());
                                    }

                                    // Model.loadNewShape(directory + "." + fileName);
                                } else {
                                    System.out.println("canceled");
                                }
                            }
                        });
                        break;
                    case "Save":
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                fileChooser = new JFileChooser();
                                int returnValue = fileChooser.showSaveDialog(MainGui.getMainGui());
                            }
                        });
                        break;
                    case "Exit":
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mainGui.showConfirm("Sure");
                            }
                        });
                        break;
                }
            }
        }
    }

    private void addNewShape(String shapeName, Class aClass) {
        ToolBarController.addNewListner(toolBarPanel.addNewShapeButton(shapeName));
        Model.getModel().setSuppotedShapesClassFiles(shapeName, aClass);
    }

}
