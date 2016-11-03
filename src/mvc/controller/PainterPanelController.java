package mvc.controller;

import mvc.model.Model;
import mvc.model.UpdateCommand;
import mvc.view.MainGuiView;
import mvc.view.RightClickPopUpMenu;
import shapes.*;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import mvc.view.OpenFile;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class PainterPanelController extends JPanel implements MouseListener, MouseMotionListener {
    private int currentX, currentY;
    private int oldX, oldY;
    private Shape currentShape;
    public static Color selectedColor = Color.black;

    public List<Shape> selectedShapes = new ArrayList<>(); // for select operation
    public boolean shouldSelect = false; // used in draw methods in shapes to fill it in grey color
    private boolean didChange = false; // this flag used to check if anythings changed in the array of shapes
    // so the history get updated

    private static PainterPanelController singeltonPainterPanel;


    private PainterPanelController() {
        // register listeners
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setComponentPopupMenu(new RightClickPopUpMenu());
    }

    public static PainterPanelController getPainterPanel() {
        if (singeltonPainterPanel == null) {
            singeltonPainterPanel = new PainterPanelController();
        }
        return singeltonPainterPanel;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(Shape currentShape) {
        this.currentShape = currentShape;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(OpenFile.image != null){
            g.drawImage(OpenFile.image, 0, 0, null);
            repaint();
        }

        if (currentX != -1 && currentY != -1) {
            switch (MainGuiView.getOperation()) {
            case DrawTriangle:
                Triangle triangle = new Triangle(new Point(oldX, oldY),
                        new Dimensions(currentX - oldX, currentY - oldY));
                currentShape = triangle;

                triangle.draw(g);
                break;
            case DrawRect:
                Rectangle rectangle = new Rectangle(new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)),
                        new Dimensions(calculateWidth(), calculateHeight()));
                currentShape = rectangle;
                rectangle.draw(g);
                break;
            case DrawOval:
                Ellipse ellipse = new Ellipse(new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)),
                        new Dimensions(calculateWidth(), calculateHeight()));
                currentShape = ellipse;
                ellipse.draw(g);
                break;
            case DrawLine:
                Line line = new Line(oldX, oldY, currentX, currentY);
                currentShape = line;
                line.draw(g);
                break;
            case Other:
                String otherShape = MainGuiView.getOtherOperation();
                Class otherShapeClass = Model.getModel().getSuppotedShapesClassFiles().get(otherShape);
                try {
                    Shape tempShape = (Shape) otherShapeClass.newInstance();
                    if (tempShape instanceof RegularPolygon) {
                        Constructor constructor = otherShapeClass.getDeclaredConstructor(Point.class, Dimensions.class);

                        Shape shape = (Shape) constructor.newInstance(new Point(oldX, oldY),
                                new Dimensions(currentX - oldX, currentY - oldY));
                        currentShape = shape;
                        currentShape.draw(g);
                    } else {
                        Constructor constructor = otherShapeClass.getDeclaredConstructor(Point.class, Dimensions.class);

                        Shape shape = (Shape) constructor.newInstance(
                                new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)),
                                new Dimensions(calculateWidth(), calculateHeight()));
                        currentShape = shape;
                        currentShape.draw(g);
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            case Resize:
                if (!selectedShapes.isEmpty()) {
                    for (int i = selectedShapes.size() - 1; i >= 0; i = i - 1) {
                        selectedShapes.get(i).resize(oldX, oldY, currentX, currentY);
                    }
                    oldX = currentX;
                    oldY = currentY;
                } else if (currentShape != null) {
                    currentShape.resize(oldX, oldY, currentX, currentY);
                    oldX = currentX;
                    oldY = currentY;
                    currentShape.draw(g);
                }
                break;
            case Move:
                if (!selectedShapes.isEmpty()) {
                    for (int i = selectedShapes.size() - 1; i >= 0; i = i - 1) {
                        selectedShapes.get(i).move(oldX, oldY, currentX, currentY);
                    }
                    oldX = currentX;
                    oldY = currentY;
                } else if (currentShape != null) {
                    currentShape.move(oldX, oldY, currentX, currentY);
                    oldX = currentX;
                    oldY = currentY;
                    currentShape.draw(g);
                }
                break;
                case Select:
                    break;
            }
        }

        for (Shape shape : Model.getModel().getShapes()) {
            shape.draw(g);
        }

        shouldSelect = true;
        for (Shape shape : selectedShapes) {
            shape.draw(g);
        }
        shouldSelect = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) return;
        List<Shape> shapes = Model.getModel().getShapes();
        oldX = e.getX();
        oldY = e.getY();
        currentX = oldX;
        currentY = oldY;
        switch (MainGuiView.getOperation()) {
            case Resize:
                if (selectedShapes.isEmpty()) {
                    for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                        Shape sh = shapes.get(i);
                        try {
                            if (sh.contain(currentX, currentY)) {
                                currentShape = sh;
                                shapes.remove(sh);
                                break;
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                break;
            case Move:
                if (selectedShapes.isEmpty()) {
                    for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                        Shape sh = shapes.get(i);
                        try {
                            if (sh.contain(currentX, currentY)) {
                                currentShape = sh;
                                shapes.remove(sh);
                                break;
                            }
                        } catch (Exception p) {
                            p.printStackTrace();
                        }
                    }
                }
    
                break;
            case Fill:
                for (Shape shape : shapes) {
                    if (shape.contain(currentX, currentY)) {
                        shape.setFillColor(selectedColor);
                        break;
                    }
                }
                break;

            case Select:
                boolean shapeFound = false;
                for (Shape shape : shapes) {
                    if (shape.contain(currentX, currentY)) {
                        shapeFound = true;

                        boolean selectedShapeFound = false;
                        for (int i = 0; i < selectedShapes.size(); i++) {
                            if (selectedShapes.get(i) == shape) {
                                selectedShapes.remove(i);
                                selectedShapeFound = true;
                                break;
                            }
                        }

                        if (!selectedShapeFound) {
                            selectedShapes.add(shape);
                        }
                        break;
                    }
                }

                if (!shapeFound) {
                    selectedShapes.clear();
                }
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) return;
        List<Shape> shapes = Model.getModel().getShapes();

        switch (MainGuiView.getOperation()) {
            case Delete:
                for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                    try {
                        Shape sh = shapes.get(i);
                        if (sh.contain(currentX, currentY)) {
                            shapes.remove(i);
                            didChange = true;
                            break;
                        }
                    } catch (Exception p) {
                        p.printStackTrace();
                    }
                }
                currentX = currentY = -1;
                repaint();
    
                break;
            default:
                if (currentShape != null) {
                    shapes.add(currentShape);
                    didChange = true;
                    currentShape = null;
                }

        }
        currentX = currentY = oldX = oldY = -1;

        if (MainGuiView.getOperation() != MainGuiView.Operation.Select && didChange) {
            // update the shapes
            Model.getModel().setShapes(shapes);

            // update the history
            new UpdateCommand().execute();

            // reset click
            didChange = false;
        }

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.isPopupTrigger()) return;
        currentX = e.getX();
        currentY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void doneSelecting() {
        shouldSelect = false;
        selectedShapes.clear();
    }

    private int calculateWidth() {
        return Math.abs(oldX - currentX);
    }

    private int calculateHeight() {
        return Math.abs(oldY - currentY);
    }
}
