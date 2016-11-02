package shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import mvc.controller.PainterPanelController;

public class RegularPolygon extends Polygon {
    private List<Point> points = new ArrayList<>();
    private Point location;
    Dimensions dimensions;
    private Color color = Color.black;
    private Color fillColor = null;
    private int NunuOfSides;

    public RegularPolygon(Point location, Dimensions dimensions, int sides) {

        this.dimensions = dimensions;
        NunuOfSides = sides;
        this.location = location;
        this.color = PainterPanelController.selectedColor;
        intialize();

    }

    private void intialize() {
        points.clear();
        double theta = Math.toRadians(360 / NunuOfSides);
        points.add(0, new Point(location.x + dimensions.width, location.y + dimensions.height));
        System.out.println("intii"+location.x+"/"+location.y);

        for (int i = 0; i < NunuOfSides - 1; i++) {
            int x = (int) (location.x + (points.get(i).x - location.x) * Math.cos(theta)
                    - (points.get(i).y - location.y) * (Math.sin(theta)));
            int y = (int) (location.y + (points.get(i).x - location.x) * Math.sin(theta)
                    + (points.get(i).y - location.y) * (Math.cos(theta)));

            points.add(new Point(x, y));
        }

    }

    public RegularPolygon(List<Point> points) {
        this.color = PainterPanelController.selectedColor;
        this.points = points;
        NunuOfSides = points.size();
    }

    public void draw(Graphics g) {
        int numberOfPoints = points.size();
        int[] xPoints = new int[numberOfPoints];
        int[] yPoints = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            xPoints[i] = points.get(i).x;
            yPoints[i] = points.get(i).y;
        }

        // g.drawPolygon(xPoints, yPoints, points.size());
        if (fillColor == null) {
            g.setColor(color);
            g.drawPolygon(xPoints, yPoints, numberOfPoints);
            if (PainterPanelController.getPainterPanel().shouldSelect) {
                g.setColor(new Color(0, 0, 0, 20));
                g.fillPolygon(xPoints, yPoints, numberOfPoints);

            }
        } else {
            g.setColor(fillColor);
            g.fillPolygon(xPoints, yPoints, numberOfPoints);
            if (PainterPanelController.getPainterPanel().shouldSelect) {
                g.setColor(new Color(0, 0, 0, 20));
                g.fillPolygon(xPoints, yPoints, numberOfPoints);

            }

        }
    }

    /**
     * @return the points of the polygon.
     */
    public List<Point> getPoints() {
        return points;

    }

    public void setPoints(List<Point> points) {
        this.points = points;
        NunuOfSides = points.size();
    }

    public boolean contain(int x, int y) {
        int numberOfPoints = points.size();
        int[] xPoints = new int[numberOfPoints];
        int[] yPoints = new int[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            xPoints[i] = points.get(i).x;
            yPoints[i] = points.get(i).y;
        }
        System.out.println("yes contain");
        java.awt.Polygon p = new java.awt.Polygon(xPoints, yPoints, numberOfPoints);
        return p.contains(x, y);
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
        fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    public Object clone() throws CloneNotSupportedException {
        RegularPolygon polyg = new RegularPolygon(points);
        polyg.setColor(getColor());
        polyg.setFillColor(getFillColor());
        return polyg;
    }

    @Override
    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public Point getLocation() {
        // TODO Auto-generated method stub
        return location;
    }

    @Override
    public Dimensions getDimensions() {
        // TODO Auto-generated method stub
        return dimensions;
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void move(int x1, int y1, int x2, int y2) {
        // TODO Auto-generated method stub
        System.out.println("yes move");
        location.x += x2 - x1;
        location.y += y2 - y1;
        intialize();

    }

    @Override
    public void resize(int x1, int y1, int x2, int y2) {

        System.out.println("yes resize");
        dimensions.width+=x2-x1;
        dimensions.height+=y2-y1;
        intialize();
    }
}
