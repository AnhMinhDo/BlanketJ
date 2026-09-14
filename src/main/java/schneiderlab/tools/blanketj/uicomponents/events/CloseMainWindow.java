package schneiderlab.tools.blanketj.uicomponents.events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseMainWindow implements ActionListener {
    private JFrame parentFrame;

    public CloseMainWindow(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parentFrame.dispose();
    }
}
