package mvc.controller;

import mvc.model.Model;
import mvc.view.MainGui;
import shapes.*;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class PainterPanelController extends JPanel implements MouseListener, MouseMotionListener {
    private int currentX, currentY;
    private int oldX, oldY;
    private List<Shape> shapes = new ArrayList<>();
    private Shape currentShape;

    private static PainterPanelController singeltonPainterPanel;

    private PainterPanelController() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public static PainterPanelController getPainterPanel() {
        if(singeltonPainterPanel == null) {
            singeltonPainterPanel = new PainterPanelController();
        }
        return singeltonPainterPanel;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (MainGui.getOperation()) {
            case DrawRect:
                Rectangle rectangle = new Rectangle(new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)), new Dimensions(calculateWidth(), calculateHeight()));
                currentShape = rectangle;
                rectangle.draw(g);
                break;
//            case DrawOval:
//                Ellipse ellipse = new Ellipse(new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)), new Dimensions(calculateWidth(), calculateHeight()));
//                currentShape = ellipse;
//                ellipse.draw(g);
//                break;
            case DrawLine:
                Line line = new Line(oldX, oldY, currentX, currentY);
                currentShape = line;
                line.draw(g);
                break;
            case Other:
                String otherShape = MainGui.getOtherOperation();
                String otherShapeClass = Model.getModel().getSuppotedShapesClassFiles().get(otherShape);
                try {
                    Constructor constructor = ClassLoader.getSystemClassLoader().loadClass(otherShapeClass).getDeclaredConstructor(Point.class, int.class);
                    Shape shape = (Shape) constructor.newInstance(new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)), calculateWidth());
                    currentShape = shape;
                    currentShape.draw(g);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
        }

        for(Shape shape : shapes) {
            shape.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        oldX = e.getX();
        oldY = e.getY();
        currentX = oldX;
        currentX = oldY;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shapes.add(currentShape);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    private int calculateWidth() {
        return Math.abs(oldX - currentX);
    }

    private int calculateHeight() {
        return Math.abs(oldY - currentY);
    }
}
