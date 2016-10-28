package shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import mvc.controller.PainterPanelController;

public abstract class Polygon implements Shape {
    private List<Point> points = new ArrayList<>();
    private Point location;
    private Color color = Color.black;
    private Color fillColor = null;

    public Polygon(List<Point> points) {
        this.color=PainterPanelController.selectedColor;
        this.points = points;
    }

    public abstract double area();

    public abstract double perimeter();

    public void draw(Graphics g) {
        int numberOfPoints = points.size();
        int[] xPoints = new int[numberOfPoints];
        int[] yPoints = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            xPoints[i] = points.get(i).x;
            yPoints[i] = points.get(i).y;
        }
        g.drawPolygon(xPoints, yPoints, points.size());
    }

    /**
     * @return the points of the polygon.
     */
    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public boolean contain(int x, int y) {

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
    public void fill(Color color) {
        fillColor = color;
    }

    @Override
    public Color getLFillColor() {

        return fillColor;
    }
}
