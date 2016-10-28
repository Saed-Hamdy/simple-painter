package shapes;

import java.awt.*;

/**
 * @author YSteam
 */
public interface Shape {
    public double perimeter();

    public void draw(Graphics g);

    public void setLocation(Point location);

    public Point getLocation();
    

    public Dimensions getDimensions();

    public void setDimensions(Dimensions dimensions);

    public boolean contain(int x, int y);

    public void setColor(Color color);

    public Color getColor();

    public void fill(Color color);

    public Color getLFillColor();

}
