package schneiderlab.tools.blanketj.uicomponents.events;

import ij.ImagePlus;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class TrackChangeInTheDirTextFieldUpdateChannelsAvailability implements DocumentListener{
    private JTextField dirTextField;
    private JComboBox comboBoxBatch;
    private JCheckBox checkBoxBatch;

    public TrackChangeInTheDirTextFieldUpdateChannelsAvailability(JTextField dirTextField, JComboBox comboBoxBatch, JCheckBox checkBoxBatch) {
        this.dirTextField = dirTextField;
        this.comboBoxBatch = comboBoxBatch;
        this.checkBoxBatch = checkBoxBatch;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        File dir = new File(dirTextField.getText());
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".tif"));
        ArrayList<Integer> arrayNumberOfChannelsInEachFile = new ArrayList<>();
        for (File file : files){
            ImagePlus img = new ImagePlus(file.getAbsolutePath());
            Integer numberOfChannel = (Integer) img.getNChannels();
            arrayNumberOfChannelsInEachFile.add(numberOfChannel);
        }
        Integer minNumberOfChannels = Collections.min(
                arrayNumberOfChannelsInEachFile);
        comboBoxBatch.removeAllItems();
        for (int i = 0; i < minNumberOfChannels; i++) {
            comboBoxBatch.addItem("channels " + (i+1));
        }
        comboBoxBatch.setSelectedIndex(0);
        if(minNumberOfChannels > 1){
            checkBoxBatch.setSelected(true);
            checkBoxBatch.setEnabled(true);
            comboBoxBatch.setEnabled(true);
        } else {
            checkBoxBatch.setSelected(false);
            checkBoxBatch.setEnabled(false);
            comboBoxBatch.setEnabled(false);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
