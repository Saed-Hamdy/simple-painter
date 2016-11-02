package shapes;

public class Square extends Rectangle {

    public Square(Point location,Dimensions dimensions) {
        super(location, new Dimensions(Math.max(dimensions.height, dimensions.width), Math.max(dimensions.height, dimensions.width)));
    }
}
