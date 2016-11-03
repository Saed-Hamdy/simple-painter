package mvc.controller;

import mvc.model.Model;
import mvc.model.UpdateCommand;
import mvc.view.MainGuiView;
import shapes.Dimensions;
import shapes.Point;
import shapes.Rectangle;
import shapes.RegulerPolygon;
import shapes.Shape;
import shapes.Triangle;
import shapes.Ellipse;
import shapes.Line;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import mvc.view.OpenFile;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class PainterPanelController extends JPanel implements MouseListener, MouseMotionListener {

    /**
     * current mouse x location
     */
    private int currentX;
    /**
     * 
     * current mouse y location
     */
    private int currentY;
    /**
     * 
     * old mouse x location
     */
    private int oldX;
    /**
     * 
     * old mouse y location
     */
    private int oldY;
    /**
     * the last selected shape to have opration
     */
    private Shape currentShape;
    /**
     * the selected color to draw with
     */
    public static Color selectedColor = Color.black;
    /**
     * a list of the selected shapes to do operation on
     */
    public static List<Shape> selectedShapes = new ArrayList<>();
    /**
     * a flag use to know if the shape drawing is selected or not to fill it in
     * gray
     */
    public boolean shouldSelect = false;

    /**
     * the only object of type {@link PainterPanelController} in the program
     * 
     */

    private static PainterPanelController singeltonPainterPanel;

    /**
     * initialize the object
     */
    private PainterPanelController() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
/**
 * 
 * @return the {@link PainterPanelController}  object
 */
    public static PainterPanelController getPainterPanel() {
        if (singeltonPainterPanel == null) {
            singeltonPainterPanel = new PainterPanelController();
        }
        return singeltonPainterPanel;
    }
/**
 * paint the component of the program and handle every state  
 */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (OpenFile.image != null) {
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
                // changed
                String otherShape = MainGuiView.getOtherOperation();
                System.out.println("other");
                Class otherShapeClass = Model.getModel().getSuppotedShapesClassFiles().get(otherShape);
                try {
                    Shape tempShape = (Shape) otherShapeClass.newInstance();
                    if (tempShape instanceof RegulerPolygon||tempShape instanceof Line) {
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
                        Shape sh = selectedShapes.get(i);
                        // sh.setDimensions(new Dimensions(currentX, currentY));
                        sh.resize(oldX, oldY, currentX, currentY);
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
        List<Shape> shapes = Model.getModel().getShapes();
        oldX = e.getX();
        oldY = e.getY();
        currentX = oldX;
        currentY = oldY;
        switch (MainGuiView.getOperation()) {
        case Resize:
            if (selectedShapes.isEmpty())
                for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                    Shape sh = shapes.get(i);
                    try {
                        if (sh.contain(currentX, currentY)) {
                            currentShape = sh;
                            shapes.remove(sh);
                            break;
                        }
                    } catch (Exception p) {
                    }

                }
            break;
        case Move:
            if (selectedShapes.isEmpty())
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

            break;
        case Fill:
            if (selectedShapes.isEmpty()) {
                for (Shape shape : shapes) {
                    if (shape.contain(currentX, currentY)) {
                        shape.setFillColor(selectedColor);
                        break;
                    }
                }
            }
            break;

        case Select:
            boolean shapeFound = false;
            for (Shape shape : shapes) {
                System.out.println("slect found");
                if (shape.contain(currentX, currentY)) {
                    selectedShapes.add(shape);
                    shapes.remove(shape);
                    shapeFound = true;
                    System.out.println("slect found2  " + shapes.size());
                    break;
                }
            }
            Boolean selectedFound = false;
            if (!shapeFound) {

                for (Shape shape : selectedShapes) {
                    if (shape.contain(currentX, currentY)) {
                        shapes.add(shape);
                        selectedShapes.remove(shape);
                        selectedFound = true;
                        System.out.println("slect found3  " + selectedShapes.size());
                        break;
                    }

                }
                if (!selectedFound) {
                    shapes.addAll(selectedShapes);
                    selectedShapes.clear();
                }
            }

            break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        List<Shape> shapes = Model.getModel().getShapes();

        switch (MainGuiView.getOperation()) {
        case Delete:
            for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                try {
                    Shape sh = shapes.get(i);
                    if (sh.contain(currentX, currentY)) {
                        shapes.remove(i);
                        // TODO: changed
                        break;
                    }
                } catch (Exception p) {
                }
            }
            currentX = currentY = -1;
            repaint();

            break;
        default:
            if (currentShape != null) {
                shapes.add(currentShape);
                currentShape = null;
                // TODO: changed
            }

        }
        currentX = currentY = oldX = oldY = -1;

        if (MainGuiView.getOperation() != MainGuiView.Operation.Select) {
            // update the shapes
            Model.getModel().setShapes(shapes);

            // update the history
            new UpdateCommand().execute();
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
        currentX = e.getX();
        currentY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
/**
 * 
 */
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
