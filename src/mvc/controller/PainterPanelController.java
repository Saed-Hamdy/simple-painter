package mvc.controller;

import mvc.model.Model;
import mvc.view.MainGui;
import shapes.Dimensions;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Ellipse;
import shapes.Line;

import javax.swing.JPanel;
import java.awt.Graphics;
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
    private Shape currentShape,oldshape;

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
        if (currentX!=-1&&currentY!=-1){
        switch (MainGui.getOperation()) {
            case DrawRect:
                Rectangle rectangle = new Rectangle(new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)), new Dimensions(calculateWidth(), calculateHeight()));
                currentShape = rectangle;
                System.out.println(Math.min(oldX, currentX));
                //System.out.println("rec");
                
                rectangle.draw(g);
                break;
            case DrawOval:
                Ellipse ellipse = new Ellipse(new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)), new Dimensions(calculateWidth(), calculateHeight()));
                currentShape = ellipse;
                ellipse.draw(g);
                break;
            case DrawLine:
                Line line = new Line(oldX, oldY, currentX, currentY);
                currentShape = line;
                line.draw(g);
               // System.out.println("li");
                break;
            case Other:
                String otherShape = MainGui.getOtherOperation();
                Class otherShapeClass = Model.getModel().getSuppotedShapesClassFiles().get(otherShape);
                try {
                    Constructor constructor = otherShapeClass.getDeclaredConstructor(Point.class, Dimensions.class);
                    Shape shape = (Shape) constructor.newInstance(new Point(Math.min(oldX, currentX), Math.min(oldY, currentY)), new Dimensions(calculateWidth(), calculateHeight()));
                    currentShape = shape;
                    currentShape.draw(g);
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
                if(currentShape!=null){
                currentShape.setDimensions(new Dimensions(Math.abs(currentX-currentShape.getLocation().x),Math.abs(currentY-currentShape.getLocation().y)));
                currentShape.draw(g);
                }
                break;
            case Move:
                if(currentShape!=null){
                currentShape.setLocation(new Point(currentX, currentY));
                currentShape.draw(g);
                }
                break; 
        }}
        
        for(Shape shape : shapes) {
            System.out.println("1.."+shapes.size()+"f"+shape);
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
        currentY = oldY;
        switch (MainGui.getOperation()) {
            case Resize:
                for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                    Shape sh = shapes.get(i);
                    if (sh.contain(currentX, currentY)) {
                        currentShape = sh;
                        shapes.remove(sh);
                        System.out.println("ccccsize"+shapes.size());
                        break;
                    }
                }
                break;
            case Move:
                for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                    Shape sh = shapes.get(i);
                    if (sh.contain(currentX, currentY)) {
                        currentShape = sh;
                        shapes.remove(sh);
                        break;
                    }
                }
                break;
                
                
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (MainGui.getOperation()){
        case Delete:
            for (int i = shapes.size() - 1; i >= 0; i = i - 1) {
                Shape sh = shapes.get(i);
                if (sh.contain(currentX, currentY)) {
                    shapes.remove(i);
                    
                    break;
                }
            }
                currentX=currentY=-1;
                repaint();
            
            break;            
            default:
                if(currentShape!=null){
                    shapes.add(currentShape);
                    currentShape=null;
                }
                
                
         }
        currentX=currentY=oldX=oldY=-1;
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

    private int calculateWidth() {
        return Math.abs(oldX - currentX);
    }

    private int calculateHeight() {
        return Math.abs(oldY - currentY);
    }
}
