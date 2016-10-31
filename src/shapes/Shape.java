package shapes;

import java.awt.Graphics;
import java.awt.Color;

/**
 * @author YSteam
 */
public interface Shape {
    public void draw(Graphics g);

    public void setLocation(Point location);

    public Point getLocation();

    public Dimensions getDimensions();

    public void setDimensions(Dimensions dimensions);

    public boolean contain(int x, int y);

    public void setColor(Color color);

    public Color getColor();

    public void setFillColor(Color color);

    public Color getFillColor();

    public Object clone() throws CloneNotSupportedException;

    public void move(int x1, int y1, int x2, int y2);

    public void resize(int x1, int y1, int x2, int y2);
}
