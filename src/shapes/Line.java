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
    private Color fillColor = null;
    private Dimensions dimensions;
    

    public Line(int x1, int y1, int x2, int y2) {
        color = PainterPanelController.selectedColor;

        location = new Point(x1, y1);
        dimensions = new Dimensions(x2 - x1, y2 - y1);

    }

    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawLine(location.x, location.y, location.x + dimensions.width,location.y+ dimensions.height);
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

    @Override
    public boolean contain(int x, int y) {
        // TODO Auto-generated method stub
        if (x <= Math.max(location.x,location.x+dimensions.width) &&
                x >= Math.min(location.x,location.x+dimensions.width))
            try {
                System.out.println("gg");

                return (((x - location.x) / (y - location.y)) == 
                        (location.x+dimensions.width - location.x) / (location.y+dimensions.height - location.y));
            } catch (Exception exp) {
                System.out.println("kkll");
                return (Math.abs(Math.ceil((x - location.x) * (dimensions.height / 10))
                        - Math.ceil(y - location.y) * (dimensions.width / 10)) <= 400);

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
    public void move(int x1, int y1, int x2, int y2) {
        location.x += x2 - x1;
        location.y += y2 - y1;
    }

    @Override
    public void resize(int x1, int y1, int x2, int y2) {
        System.out.println(x1+""+x2+""+y1+""+y2);
        dimensions.width = (dimensions.width + x2 - x1);
        dimensions.height = (dimensions.height + y2 - y1);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Line clonedShape = new Line(this.x1, this.y1, this.x2, this.y2);
        clonedShape.setColor(clonedShape.getColor());
        clonedShape.setFillColor(clonedShape.getColor());
        return clonedShape;
    }

   

}
