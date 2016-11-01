package shapes;

public class Triangle extends RegulerPolygon {

    private final static int nuOfSides =3;

    public Triangle(Point location, Dimensions dimensions) {
        super(location, dimensions, nuOfSides);
    }


}
