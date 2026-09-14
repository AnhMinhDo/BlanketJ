package schneiderlab.tools.blanketj.uicomponents.events;

import ij.ImagePlus;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TrackChangeInTheTextFieldUpdateChannelsAvailability implements DocumentListener {
    private JTextField textField;
    private JComboBox comboBoxSingle;
    private JCheckBox checkBoxSingle;
    private JComboBox comboBoxPreview;
    private JCheckBox checkBoxPreview;
    private JRadioButton radioButtonSingle;
    private JRadioButton radioButtonPreview;

    public TrackChangeInTheTextFieldUpdateChannelsAvailability(JTextField textField,
                                                               JComboBox comboBoxSingle,
                                                               JCheckBox checkBoxSingle,
                                                               JComboBox comboBoxPreview,
                                                               JCheckBox checkBoxPreview,
                                                               JRadioButton radioButtonSingle,
                                                               JRadioButton radioButtonPreview) {
        this.textField = textField;
        this.comboBoxSingle = comboBoxSingle;
        this.checkBoxSingle = checkBoxSingle;
        this.comboBoxPreview = comboBoxPreview;
        this.checkBoxPreview = checkBoxPreview;
        this.radioButtonSingle = radioButtonSingle;
        this.radioButtonPreview = radioButtonPreview;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        ImagePlus inputFile = new ImagePlus(textField.getText());
        int numOfChannels = inputFile.getNChannels();
        comboBoxSingle.removeAllItems();
        comboBoxPreview.removeAllItems();

        for (int i = 0; i < numOfChannels; i++) {
            comboBoxSingle.addItem("channels " + (i+1));
            comboBoxPreview.addItem("channels " + (i+1));
        }
        comboBoxSingle.setSelectedIndex(0);
        comboBoxPreview.setSelectedIndex(0);
        if(numOfChannels > 1){
            checkBoxSingle.setSelected(true);
            checkBoxPreview.setSelected(true);
            checkBoxSingle.setEnabled(true);
            checkBoxPreview.setEnabled(true);

        } else {
            checkBoxSingle.setSelected(false);
            checkBoxSingle.setEnabled(false);
            checkBoxPreview.setSelected(false);
            checkBoxPreview.setEnabled(false);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
