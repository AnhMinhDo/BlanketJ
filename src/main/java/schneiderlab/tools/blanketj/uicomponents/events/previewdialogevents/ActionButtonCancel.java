package schneiderlab.tools.blanketj.uicomponents.events.previewdialogevents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButtonCancel implements ActionListener {
    private JDialog currentDialog;

    public ActionButtonCancel(JDialog currentDialog) {
        this.currentDialog = currentDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentDialog.dispose();
    }
}
