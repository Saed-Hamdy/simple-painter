package shapes;

import java.awt.Graphics;
import java.awt.Color;
import java.io.Serializable;

/**
 * @author YSteam
 */
public interface Shape extends Serializable {
    void draw(Graphics g);

    void setLocation(Point location);

    Point getLocation();

    Dimensions getDimensions();

    void setDimensions(Dimensions dimensions);

    boolean contain(int x, int y);

    void setColor(Color color);

    Color getColor();

    void setFillColor(Color color);

    Color getFillColor();

    Object clone() throws CloneNotSupportedException;

    void move(int x1, int y1, int x2, int y2);

    void resize(int x1, int y1, int x2, int y2);
}
