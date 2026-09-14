package schneiderlab.tools.blanketj.uicomponents.events;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EventChannelsSelectionCheckboxSelected implements ItemListener {
    private JCheckBox checkBox;
    private JComboBox comboBox;

    public EventChannelsSelectionCheckboxSelected(JCheckBox checkBox, JComboBox comboBox) {
        this.checkBox = checkBox;
        this.comboBox = comboBox;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (checkBox.isEnabled() && checkBox.isSelected()){
            comboBox.setEnabled(true);
        } else if (checkBox.isEnabled() && !checkBox.isSelected()) {
            comboBox.setEnabled(false);
        } else if (!checkBox.isEnabled()) {
            comboBox.setEnabled(false);
        }
    }
}
