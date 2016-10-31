package mvc.model;

import shapes.Shape;

import java.util.*;

/**
 * Created on 10/26/16.
 */
public class Model {
    private static Model model;
    private Map<String, Class> suppotedShapesClassFiles;
    private List<Shape> shapes;
    /**
     * history object it's get updated by eidt command, and get initialized first time when model is initialized
     * with empty history list.
     */
    private History history;

    private Model() {
        suppotedShapesClassFiles = new HashMap<>();
        history = new History();
        shapes = new ArrayList<>();
        loadSupportedShapes();
    }

    public History getHistory() {
        return history;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public static Model getModel() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<Shape> cloneShapes(List<Shape> shapes) {
        List<Shape> clonedShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            try {
                clonedShapes.add((Shape) shape.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return clonedShapes;
    }

    private void loadSupportedShapes() {
    }

    public static void loadNewShape(String fileName) {
        try {
            ClassLoader.getSystemClassLoader().loadClass(fileName);
            // Shape rectangle = (Shape) ClassLoader.getSystemClassLoader().loadClass("shapes.Rectangle").newInstance();

        }
        // catch (InstantiationException e) {
        //  e.printStackTrace();
        // } catch (IllegalAccessException e) {
        //    e.printStackTrace();
        // }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Class> getSuppotedShapesClassFiles() {
        return suppotedShapesClassFiles;
    }

    public void setSuppotedShapesClassFiles(String key, Class value) {
        suppotedShapesClassFiles.put(key, value);
    }

    void persistIntoJson() {
    }

    void persistIntoXml() {
    }
}

