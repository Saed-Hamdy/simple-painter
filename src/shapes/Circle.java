package shapes;

public class Circle extends Ellipse {
    private Point location;
    private int radius;

    public Circle (Point location, int radius) {
        super(location, new Dimensions(radius, radius));
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
