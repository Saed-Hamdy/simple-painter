package shapes;

import java.io.Serializable;

/**
 * Created by khlailmohammedyakout on 10/27/16.
 */
public class Dimensions implements Serializable{
    public int width;
    public int height;

    public Dimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
