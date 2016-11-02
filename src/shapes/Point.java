package shapes;

import java.io.Serializable;

/**
 * Created by khlailmohammedyakout on 10/27/16.
 */
public class Point implements Serializable {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
