package mvc.controller;

import mvc.view.MainGuiView;
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
                            MainGuiView.setOperation(MainGuiView.Operation.DrawLine);
                            System.out.println(MainGuiView.getOperation());
                        }
                    });
                    break;
                case "Oval":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGuiView.setOperation(MainGuiView.Operation.DrawOval);
                            System.out.println(MainGuiView.getOperation());
                        }
                    });
                    break;
                case "Rectangle":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGuiView.setOperation(MainGuiView.Operation.DrawRect);
                            System.out.println(MainGuiView.getOperation());
                        }
                    });
                    break;
                case "Delete":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGuiView.setOperation(MainGuiView.Operation.Delete);
                            System.out.println(MainGuiView.getOperation());
                        }
                    });
                    break;
                case "Move":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGuiView.setOperation(MainGuiView.Operation.Move);
                            System.out.println(MainGuiView.getOperation());
                        }
                    });
                    break;
                case "Resize":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGuiView.setOperation(MainGuiView.Operation.Resize);
                            System.out.println(MainGuiView.getOperation());
                        }
                    });
                    break;
                case "Colors":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new ColorChooser();
                        }
                    });
                    break;

                case "Select":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGuiView.setOperation(MainGuiView.Operation.Select);
                        }
                    });
                    break;
                case "Fill":
                    buttonComponent.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MainGuiView.setOperation(MainGuiView.Operation.Fill);
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
                    MainGuiView.setOperation(MainGuiView.Operation.Other);
                    MainGuiView.setOtherOperation(button.getText());
                    System.out.println(MainGuiView.getOtherOperation());
                }
            });
    }

}
