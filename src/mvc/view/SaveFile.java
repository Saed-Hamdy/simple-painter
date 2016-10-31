package mvc.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import fileFilters.DataOfShapes;
import fileFilters.JasonSaveFileFilter;
import fileFilters.pngSaveFilter;
import fileFilters.xmlSaveFilter;
import mvc.controller.PainterPanelController;
import mvc.model.Model;
import shapes.Shape;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;



@SuppressWarnings("serial")
public class SaveFile extends JFrame {
    public SaveFile() {

        JFileChooser saveFile = new JFileChooser();
        saveFile.addChoosableFileFilter(new pngSaveFilter());
        saveFile.addChoosableFileFilter(new JasonSaveFileFilter());
        saveFile.addChoosableFileFilter(new xmlSaveFilter());
        int sf = saveFile.showSaveDialog(saveFile);
        if (sf == JFileChooser.APPROVE_OPTION) {
            String ext = "";
            String extension = saveFile.getFileFilter().getDescription();           
            if (extension.equals(".png,.PNG")) {
                ext = ".png";
                saveFile.setSelectedFile(new File(saveFile.getSelectedFile().toString() + ext));
                try {
                    BufferedImage bi = new BufferedImage(MainGuiView.getMainGuiView().getSize().width,
                            MainGuiView.getMainGuiView().getSize().height, BufferedImage.TYPE_INT_ARGB);
                    Graphics ig2 = PainterPanelController.getPainterPanel().getGraphics();
                    ig2 = bi.createGraphics();
                    ig2.setColor(Color.white);
                    ig2.fillRect(0, 0, MainGuiView.getMainGuiView().getWidth(), MainGuiView.getMainGuiView().getHeight());
                    if (OpenFile.image != null) {
                        ig2.drawImage(OpenFile.image, 0, 0, null);
                        repaint();
                    }
                    for (Shape shape : Model.getModel().getShapes()) {

                        shape.draw(ig2);
                    }
                    File outputfile = saveFile.getSelectedFile();
                    ImageIO.write(bi, "png", outputfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (extension.equals(".json,.JSON")) {
                ext = ".json";
                XStream json = new XStream(new JettisonMappedXmlDriver());
                DataOfShapes data = new DataOfShapes();
                data.setListOfshapes(Model.getModel().getShapes());
                json.setMode(XStream.NO_REFERENCES);
                json.alias("data", DataOfShapes.class);
                try (FileWriter writer = new FileWriter(saveFile.getSelectedFile().toString() + ".json")) {
                    writer.write(json.toXML(data));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (extension.equals(".xml,.XML")) {
                ext = ".xml";

                XStream xml = new XStream(new StaxDriver());
                DataOfShapes data = new DataOfShapes();
                data.setListOfshapes(Model.getModel().getShapes());
                xml.alias("data", DataOfShapes.class);
                try (FileWriter writer = new FileWriter(saveFile.getSelectedFile().toString() + ext)) {
                    writer.write(xml.toXML(data));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
