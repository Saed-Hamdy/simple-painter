package mvc.controller;

import mvc.view.MainGui;
import mvc.view.ToolBarFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class ToolBarController {
    private JPanel toolBarPanel;

    public ToolBarController() {
        toolBarPanel = ToolBarFactory.getToolBarPanel();
        addListners();
    }

    void addListners() {
        Component[] components = toolBarPanel.getComponents();
        for(Component component : components) {
            JButton buttonComponent = (JButton) component;
            switch (buttonComponent.getText()) {
                case "Line":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGui.setOperation(MainGui.Operation.DrawLine);
                            System.out.println(MainGui.getOperation());
                        }
                    });
                    break;
                case "Oval":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGui.setOperation(MainGui.Operation.DrawOval);
                            System.out.println(MainGui.getOperation());
                        }
                    });
                    break;
                case "Rectangle":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGui.setOperation(MainGui.Operation.DrawRect);
                            System.out.println(MainGui.getOperation());
                        }
                    });
                    break;
                case "Delete":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGui.setOperation(MainGui.Operation.Delete);
                            System.out.println(MainGui.getOperation());
                        }
                    });
                    break;
                case "Move":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGui.setOperation(MainGui.Operation.Move);
                            System.out.println(MainGui.getOperation());
                        }
                    });
                    break;
                case "Resize":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGui.setOperation(MainGui.Operation.Resize);
                            System.out.println(MainGui.getOperation());
                        }
                    });
                    break;
            }
        }
    }

    public static void addNewListner(JButton button) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainGui.setOperation(MainGui.Operation.Other);
                    MainGui.setOtherOperation(button.getText());
                    System.out.println(MainGui.getOtherOperation());
                }
            });
    }

}
