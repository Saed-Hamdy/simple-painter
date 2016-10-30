package mvc.view;

import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import mvc.controller.PainterPanelController;
import mvc.fillFilters.DataOfShapes;
import mvc.fillFilters.JasonSaveFileFilter;
import mvc.fillFilters.pngSaveFilter;
import mvc.fillFilters.xmlSaveFilter;

@SuppressWarnings("serial")
public class OpenFile extends JFrame {
    public static BufferedImage image;
    // private List <Shape>shapes = new ArrayList<>();
    // private BufferedReader br;

    public OpenFile() throws ClassNotFoundException {
        JFileChooser openFile = new JFileChooser();
        openFile.addChoosableFileFilter(new pngSaveFilter());
        openFile.addChoosableFileFilter(new JasonSaveFileFilter());
        openFile.addChoosableFileFilter(new xmlSaveFilter());
        int result = openFile.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (openFile.getSelectedFile().getName().endsWith(".png")) {
                try {
                    image = ImageIO.read(openFile.getSelectedFile());
                    MainGui.getMainGui().repaint();
                } catch (IOException ex) {
                }
            } else if (openFile.getSelectedFile().getName().endsWith(".json")) {

                try {
                    DataOfShapes data;
                    XStream json = new XStream(new JettisonMappedXmlDriver());
                    json.alias("data", DataOfShapes.class);
                    data = (DataOfShapes) json.fromXML(openFile.getSelectedFile());
                    PainterPanelController.setShapes(data.getListOfStates());
                    image = data.getImage();
                    MainGui.getMainGui().repaint();

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            } else if (openFile.getSelectedFile().getName().endsWith(".xml")) {
                try {
                    DataOfShapes data;
                    XStream xml = new XStream(new StaxDriver());
                    xml.alias("data", DataOfShapes.class);
                    data = (DataOfShapes) xml.fromXML(openFile.getSelectedFile());
                    PainterPanelController.setShapes(data.getListOfStates());
                    image = data.getImage();
                    MainGui.getMainGui().repaint();

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
}
