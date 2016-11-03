package mvc.controller;

import mvc.model.Model;
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

    private static void clearSelectedShapes() {
        if (!PainterPanelController.selectedShapes.isEmpty())
            Model.getModel().getShapes().addAll(PainterPanelController.selectedShapes);
        PainterPanelController.selectedShapes.clear();
        MainGuiView.getMainGuiView().repaint();

    }

    void addListners() {
        Component[] components = toolBarPanel.getComponents();
        for (Component component : components) {
            JButton buttonComponent = (JButton) component;
            switch (buttonComponent.getText()) {
            case "Triangle":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.DrawTriangle);
                        clearSelectedShapes();
                    }
                });
                break;
            case "Line":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.DrawLine);
                        clearSelectedShapes();
                    }
                });
                break;
            case "Oval":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.DrawOval);
                        clearSelectedShapes();
                    }
                });
                break;
            case "Rectangle":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.DrawRect);
                        clearSelectedShapes();
                    }
                });
                break;
           
            }
        }
    }

    public static void addNewListner(final JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGuiView.setOperation(MainGuiView.Operation.Other);
                MainGuiView.setOtherOperation(button.getText());
                clearSelectedShapes();
            }
        });
    }

}
