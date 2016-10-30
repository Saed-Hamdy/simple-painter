package mvc.fillFilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class xmlSaveFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        // TODO Auto-generated method stub
        if(f.isDirectory()){
            return false ;
        }
        String s = f.getName();
        return s.endsWith(".xml")||s.endsWith(".XML");
        
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return ".xml,.XML";
    }

}