package shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Rectangle extends Polygon {
    private Dimensions dimensions;
    private Point location;
    private List<Point> points;

    public Rectangle() {
        super(new ArrayList<>());
    }

    public Rectangle(Point location,  Dimensions dimensions) {
        super(new ArrayList<>());
        // TODO

        this.location = location;
        this.dimensions = dimensions;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(location.x , location.y, dimensions.width, dimensions.height);
    }

    public double perimeter() {
        return dimensions.width + dimensions.height + dimensions.width + dimensions.height;
    }

    public double area() {
        return dimensions.height * dimensions.width;
    }

    @Override
    public void setLocation(Point location) {
        location = location;
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

    @Override
    public List<Point> getPoints() {
        return points;
    }

    @Override
    public void setPoints(List<Point> points) {
        this.points = points;
    }

}
