package shapes;

import java.awt.*;

public class Line implements Shape {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public double perimeter() {
        return 0;
    }

    @Override
    public void setLocation(Point location) {

    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimensions getDimensions() {
        return null;
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
    }
}
