package schneiderlab.tools.blanketj.uicomponents.events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPopUpMenu implements ActionListener {
    private JButton button;
    private JPopupMenu popupMenu;
    public ShowPopUpMenu(JButton button, JPopupMenu popupMenu) {
        this.button = button;
        this.popupMenu = popupMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popupMenu.show(button, 0, button.getHeight());
    }
}
