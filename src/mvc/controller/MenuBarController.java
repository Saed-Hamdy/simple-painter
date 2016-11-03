package mvc.controller;

import mvc.model.Model;
import mvc.model.RedoCommand;
import mvc.model.UndoCommand;
import mvc.view.MainGuiView;
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

import mvc.view.OpenFile;
import mvc.view.SaveFile;

/**
 * This class is use to control every thing in the menu bar
 * Created by khlailmohammedyakout on 10/26/16.
 *  
 */
public class MenuBarController {
    /**
     * the menu bar
     */
    private JMenuBar menuBar;
    /**
     * the file chooser object
     */
    private JFileChooser fileChooser;
    /**
     * the Main application GUI object 
     * will be use to apply changes
     */
    private MainGuiView mainGuiView;
    /**
     * the tool bar object
     */
    private ToolBarFactory toolBarPanel;

    public MenuBarController() {
        menuBar = MenuBarFactory.getMenuBar();
        mainGuiView = MainGuiView.getMainGuiView();
        toolBarPanel = ToolBarFactory.getToolBarPanel();
        addListners();
    }
    /**
     * this method is use to add listeners to all object in the menu bar
     * 
     */

    private void addListners() {
        Component[] components = menuBar.getComponents();
        for (Component component : components) {
            Component[] menuComponents = ((JMenu) component).getMenuComponents();
            for (Component menuComponent : menuComponents) {
                JMenuItem menuItem = (JMenuItem) menuComponent;
                switch (menuItem.getText()) {
                case "Open":
                    menuItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try {
                                new OpenFile();
                            } catch (ClassNotFoundException e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    break;

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
                            int returnValue = fileChooser.showOpenDialog(MainGuiView.getMainGuiView());
                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                                String fileName = fileChooser.getSelectedFile().getName();
                                fileName = fileName.replace(".class", "");

                                String directory = fileChooser.getCurrentDirectory().toString();
                                // directory = directory.replaceAll("/",
                                // ".").replaceFirst(".", "");
                                directory = directory.replace("/shapes", "");

                                File file = new File(directory);
                                URL url;
                                try {
                                    url = file.toURI().toURL();
                                    URL[] urls = new URL[] { url };
                                    ClassLoader classLoader = new URLClassLoader(urls);
                                    Class aClass = classLoader.loadClass("shapes." + fileName);
                                    addNewShape(fileName, aClass);
                                } catch (MalformedURLException e1) {
                                    e1.printStackTrace();
                                    mainGuiView.showError(e1.toString());
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                    mainGuiView.showError(e1.toString());
                                }

                            }
                        }
                    });
                    break;
                case "Save":
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new SaveFile();
                        }
                    });
                    break;
                case "Exit":
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mainGuiView.showConfirm("Sure");
                        }
                    });
                    break;
                case "Redo":
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PainterPanelController.getPainterPanel().doneSelecting();
                            new RedoCommand().execute();
                        }
                    });
                    break;

                case "Undo":
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PainterPanelController.getPainterPanel().doneSelecting();
                            new UndoCommand().execute();
                        }
                    });
                    break;
                }
            }
        }
    }

    /**
     * this method is use to add a new class to the tool bar   
     * @param shapeName
     * @param aClass
     */
    private void addNewShape(String shapeName, Class aClass) {
        ToolBarController.addNewListner(toolBarPanel.addNewShapeButton(shapeName));
        Model.getModel().setSuppotedShapesClassFiles(shapeName, aClass);
    }

}
