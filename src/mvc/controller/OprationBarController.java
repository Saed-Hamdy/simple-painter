package mvc.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.view.MainGuiView;
import mvc.view.OprationBarFactory;

public class OprationBarController {

    private JPanel oprationBarPanel;

     public OprationBarController(){
        oprationBarPanel = OprationBarFactory.getOprationBarPanel();
        addListners();
    }

    void addListners() {
        Component[] components = oprationBarPanel.getComponents();
        for (Component component : components) {
            JButton buttonComponent = (JButton) component;
            switch (buttonComponent.getText()) {

            case "Delete":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.Delete);
                    }
                });
                break;
            case "Move":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.Move);
                      }
                });
                break;
            case "Resize":
                buttonComponent.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MainGuiView.setOperation(MainGuiView.Operation.Resize);
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

}
