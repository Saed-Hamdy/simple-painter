package mvc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khlailmohammedyakout on 10/26/16.
 */
public class Model {
    private static Model model;
    private Map<String, Class> suppotedShapesClassFiles;

    private Model() {
        suppotedShapesClassFiles = new HashMap<>();
        loadSupportedShapes();
    }

    public static Model getModel() {
        if (model == null) {
            model = new Model();
        }
        return model;
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
