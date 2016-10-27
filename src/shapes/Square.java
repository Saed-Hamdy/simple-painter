package shapes;

public class Square extends Rectangle {
    private Point location;
    private int length;


    public Square(Point location, int length) {
        super(location, new Dimensions(length, length));
        this.location = location;
        this.length = length;
    }
}
