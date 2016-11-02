package shapes;

import mvc.controller.PainterPanelController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle implements Shape {

    private Dimensions dimensions;
    private Point location;
    // private List<Point> points;
    private Color color = Color.black;
    private Color fillColor = null;

    public Rectangle() {
        // super(new ArrayList<>());
    }

    public Rectangle(Point location, Dimensions dimensions) {
        // super(new ArrayList<>()); // TODO

        this.location = location;
        this.dimensions = dimensions;
    }

    @Override
    public void draw(Graphics g) {
        if (getFillColor() == null) {
            g.setColor(getColor());
            g.drawRect(location.x, location.y, dimensions.width, dimensions.height);

            if (PainterPanelController.getPainterPanel().shouldSelect) {
                g.setColor(new Color(0, 0, 0, 20));
                g.fillRect(location.x, location.y, dimensions.width, dimensions.height);
            }
        } else {
            g.setColor(getFillColor());
            g.fillRect(location.x, location.y, dimensions.width, dimensions.height);         
        }

    }

    public double perimeter() {
        return dimensions.width + dimensions.height + dimensions.width + dimensions.height;
    }

    public double area() {
        return dimensions.height * dimensions.width;
    }

    @Override
    public boolean contain(int x, int y) {
        if (x >= location.x && x <= (location.x + dimensions.width) && y >= location.y
                && y <= (location.y + dimensions.height)) {
            return true;
        }
        return false;
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

//    @Override
//    public List<Point> getPoints() {
//        return points;
//    }
//
//    @Override
//    public void setPoints(List<Point> points) {
//        this.points = points;
//    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Rectangle rectangle = new Rectangle(this.location, this.dimensions);
        rectangle.setColor(getColor());
        rectangle.setFillColor(getFillColor());
        return rectangle;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {

        return color;
    }

    public void setFillColor(Color color) {
        fillColor = color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void move(int x1, int y1, int x2, int y2) {
        location.x += x2 - x1;
        location.y += y2 - y1;
    }

    @Override
    public void resize(int x1, int y1, int x2, int y2) {
        dimensions.width =(dimensions.width + x2 - x1);
        dimensions.height = (dimensions.height + y2 - y1);
    }
}
