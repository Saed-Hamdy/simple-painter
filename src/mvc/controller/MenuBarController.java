package mvc.controller;

import mvc.model.History;
import mvc.model.Model;
import mvc.model.RedoCommand;
import mvc.model.UndoCommand;
import mvc.view.*;

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
 * Created by ahmedyakout on 10/26/16.
 */
public class MenuBarController {
    private JMenuBar menuBar;
    private JFileChooser fileChooser;
    private MainGuiView mainGuiView;
    private ToolBarFactory toolBarPanel;

    public MenuBarController() {
        menuBar = MenuBarFactory.getMenuBar();
        mainGuiView = MainGuiView.getMainGuiView();
        toolBarPanel = ToolBarFactory.getToolBarPanel();
        addListners();
    }

    private void addListners() {
        Component[] components = menuBar.getComponents();
        for (Component component : components) {
            Component[] menuComponents = ((JMenu) component).getMenuComponents();
            for (Component menuComponent : menuComponents) {
                if(menuComponent instanceof JSeparator) continue;
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

                    case "Add plug-in":
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
                                        mainGuiView.showError(e1.toString());
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                        mainGuiView.showError(e1.toString());
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

                    case "Set history limit":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                History history = Model.getModel().getHistory();
                                String input = JOptionPane.showInputDialog("please select limit" +
                                        " (current limit is " + history.getLimit() + ")");
                                if(input != null && !input.equals("")) {
                                    history.setLimit(Integer.parseInt(input));
                                }
                            }
                        });
                        break;

                    case "Share your painter":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                new SharePainterWindow().setVisible(true);
                            }
                        });
                        break;

                    case "Start a server":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                new StartServerWindow().setVisible(true);
                            }
                        });
                        break;

                    case "About":
                        menuItem.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null, "version 1.0\n\nDeveloped with love " +
                                        "by:\nAhmed Yakout\nSaed Hamdy");
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
