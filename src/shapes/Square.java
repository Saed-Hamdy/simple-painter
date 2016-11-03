package shapes;

/**
 * class 2Dsqure
 * 
 * @author said
 *
 */

public class Square extends Rectangle {
    /**
     * constructor
     * 
     * @param location
     * @param dimensions
     */
    public Square (){
        
    }
    public Square(Point location, Dimensions dimensions) {

        super(location, new Dimensions(Math.max(dimensions.height, dimensions.width),
                Math.max(dimensions.height, dimensions.width)));

    }
}
