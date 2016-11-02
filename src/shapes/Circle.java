package shapes;
/**
 * class circle
 * 
 * @author said
 *
 */

public class Circle extends Ellipse {
    /**
     * location of the shape
     */
    private Point location;
    /**
     * radius of the circle
     */
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
