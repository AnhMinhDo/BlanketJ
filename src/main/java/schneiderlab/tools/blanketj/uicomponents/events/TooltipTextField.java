package schneiderlab.tools.blanketj.uicomponents.events;

import javax.swing.*;
import java.awt.event.*;

public class TooltipTextField extends MouseAdapter {
    private JTextField textField;

    public TooltipTextField(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void mouseEntered(MouseEvent event){
        textField.setToolTipText(textField.getText());
    }
}
