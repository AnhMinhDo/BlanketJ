package schneiderlab.tools.blanketj.uicomponents.events;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EventChannelsSelectionCheckboxEnabled implements PropertyChangeListener {
    private JCheckBox checkBox;
    private JComboBox comboBox;

    public EventChannelsSelectionCheckboxEnabled(JCheckBox checkBox, JComboBox comboBox) {
        this.checkBox = checkBox;
        this.comboBox = comboBox;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (checkBox.isEnabled() && checkBox.isSelected()){
            comboBox.setEnabled(true);
        } else if (checkBox.isEnabled() && !checkBox.isSelected()) {
            comboBox.setEnabled(false);
        } else if (!checkBox.isEnabled()) {
            comboBox.setEnabled(false);
        }
    }
}
