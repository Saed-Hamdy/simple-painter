package fileFilters;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import shapes.Shape;


//
public class DataOfShapes {
    private  List<Shape> shapes = new ArrayList<>();
    private BufferedImage image;
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public List<Shape> getListOfStates() {
        return shapes;
    }

    public void setListOfshapes(List<Shape> sh) {
        shapes = sh;
    }
}

