package shapes;

import java.awt.*;

public class Ellipse implements Shape {
    private Point location;
    private Dimensions dimensions;

    public Ellipse(Point location, Dimensions dimensions) {
        this.location = location;
        this.dimensions = dimensions;
    }

    public double area() {
        return Math.PI * dimensions.height * dimensions.width;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * Math.sqrt((dimensions.width * dimensions.width + dimensions.height * dimensions.height) / 2) ;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(location.x, location.y, dimensions.width, dimensions.height);
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
}
