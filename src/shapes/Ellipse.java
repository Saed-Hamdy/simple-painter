package shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import mvc.controller.PainterPanelController;

public class Ellipse implements Shape {
    private Point location;
    private Dimensions dimensions;
    private Color color = Color.black;
    private Color fillColor = null;

    public Ellipse(Point location, Dimensions dimensions) {
        color=PainterPanelController.selectedColor;
        this.location = location;
        this.dimensions = dimensions;
    }

    public double area() {
        return Math.PI * dimensions.height * dimensions.width;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI
                * Math.sqrt((dimensions.width * dimensions.width + dimensions.height * dimensions.height) / 2);
    }

    @Override
    public void draw(Graphics g) {
        if (getLFillColor() == null) {
            g.setColor(getColor());
            g.drawOval(location.x, location.y, dimensions.width, dimensions.height);
        } else {
            g.setColor(getLFillColor());
            g.fillOval(location.x, location.y, dimensions.width, dimensions.height);
        }

    }

    @Override
    public Dimensions getDimensions() {
        return dimensions;
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    public boolean contain(int x, int y) {
        final Ellipse2D el = new Ellipse2D.Float(location.x, location.y, dimensions.width, dimensions.height);
        System.out.println("kod22");
        return el.contains(x, y);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {

        return color;
    }

    @Override
    public void fill(Color color) {
        fillColor = color;
    }

    @Override
    public Color getLFillColor() {

        return fillColor;
    }
}
