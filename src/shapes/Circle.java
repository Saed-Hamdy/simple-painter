package shapes;

import java.awt.Dimension;

/**
 * class circle
 * 
 * @author said
 *
 */

public class Circle extends Ellipse {
    /**
     * radius of the circle
     */
    private int radius;
    

    public Circle(Point location, Dimension dimention) {

        super(location, new Dimensions(Math.max(dimention.width, dimention.height),
                Math.max(dimention.width, dimention.height)));
        radius=Math.max(dimention.width, dimention.height);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
