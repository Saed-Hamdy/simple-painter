package mvc.view;

import javax.swing.*;

/**
 * Created by ahmedyakout on 10/31/16.
 */
public class RightClickPopUpMenu extends JPopupMenu {
    JMenuItem copyMenuItem;
    public RightClickPopUpMenu(){
        copyMenuItem = new JMenuItem("Copy");
        add(copyMenuItem);
    }
}
