package shapes;

import java.awt.Color;
import java.awt.Graphics;
import mvc.controller.PainterPanelController;

public class Line implements Shape, Cloneable {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Point location;
    private Color color = Color.black;
    private Dimensions dimension;
    private Color fillColor = null;

    public Line(int x1, int y1, int x2, int y2) {
        color=PainterPanelController.selectedColor;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        location = new Point(x1, y1);
        dimension = new Dimensions(x2 - x1, y2 - y1);
    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawLine(x1, y1, x2, y2);
    }


    // TODO: DELTE
//    @Override
//    public double perimeter() {
//        return 0;
//    }

    @Override
    public void setLocation(Point location) {
        x1 = location.x;
        y1 = location.y;
        x2 = x1 + dimension.width;
        y2 = y1 + dimension.height;

    }

    @Override
    public Point getLocation() {
        return new Point(x1, y1);
    }

    @Override
    public Dimensions getDimensions() {
        return dimension;
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        x2 = dimensions.width;
        y2 = dimensions.height;
        this.dimension = new Dimensions(x2 - x1, y2 - y1);
    }

    @Override
    public boolean contain(int x, int y) {
        if (x <= Math.max(x1, x2) && x >= Math.min(x1,x2)) {
            try {
                return (((x - x1)/(y - y1)) == (x2 - x1)/(y2 - y1));
            } catch (Exception exp){
                return(Math.abs(Math.ceil((x - x1)*(dimension.height / 10)) - Math.ceil(y - y1) * (dimension.width / 10)) <= 400);
            }
        }
        return false;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setFillColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Line clonedShape = new Line(this.x1, this.y1, this.x2, this.y2);
        clonedShape.setColor(clonedShape.getColor());
        clonedShape.setFillColor(clonedShape.getColor());
        return clonedShape;
    }
}
