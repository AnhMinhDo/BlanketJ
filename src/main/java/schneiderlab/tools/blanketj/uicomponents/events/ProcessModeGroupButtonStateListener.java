package schneiderlab.tools.blanketj.uicomponents.events;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ProcessModeGroupButtonStateListener implements ItemListener {
    private JButton browseButtonSingleFile;
    private JButton browseButtonDirectory;
    private JTextField textFieldSingleFile;
    private JTextField textFieldDirectory;
    private JRadioButton singleModeButton;
    private JRadioButton previewModeButton;
    private JRadioButton multipleFilesModeButton;
    private JCheckBox checkboxUsedRefSingle;
    private JCheckBox checkboxUsedRefMultiple;
    private JCheckBox checkboxUsedRefPreview;
    private JComboBox comboboxRefSingle;
    private JComboBox comboboxRefMultiple;
    private JComboBox comboboxRefPreview;

    public ProcessModeGroupButtonStateListener(JButton browseButtonSingleFile, JButton browseButtonDirectory, JTextField textFieldSingleFile, JTextField textFieldDirectory, JRadioButton singleModeButton, JRadioButton previewModeButton, JRadioButton multipleFilesModeButton, JCheckBox checkboxUsedRefSingle, JCheckBox checkboxUsedRefMultiple, JCheckBox checkboxUsedRefPreview, JComboBox comboboxRefSingle, JComboBox comboboxRefMultiple, JComboBox comboboxRefPreview) {
        this.browseButtonSingleFile = browseButtonSingleFile;
        this.browseButtonDirectory = browseButtonDirectory;
        this.textFieldSingleFile = textFieldSingleFile;
        this.textFieldDirectory = textFieldDirectory;
        this.singleModeButton = singleModeButton;
        this.previewModeButton = previewModeButton;
        this.multipleFilesModeButton = multipleFilesModeButton;
        this.checkboxUsedRefSingle = checkboxUsedRefSingle;
        this.checkboxUsedRefMultiple = checkboxUsedRefMultiple;
        this.checkboxUsedRefPreview = checkboxUsedRefPreview;
        this.comboboxRefSingle = comboboxRefSingle;
        this.comboboxRefMultiple = comboboxRefMultiple;
        this.comboboxRefPreview = comboboxRefPreview;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(singleModeButton.isSelected()){
            browseButtonDirectory.setEnabled(false);
            browseButtonSingleFile.setEnabled(true);
            textFieldSingleFile.setEnabled(true);
            textFieldDirectory.setEnabled(false);
//            checkboxUsedRefSingle.setEnabled(true);
//            checkboxUsedRefMultiple.setEnabled(false);
//            checkboxUsedRefPreview.setEnabled(false);
//            comboboxRefSingle.setEnabled(true);
//            comboboxRefMultiple.setEnabled(false);
//            comboboxRefPreview.setEnabled(false);

        } else if (previewModeButton.isSelected()) {
            browseButtonDirectory.setEnabled(false);
            browseButtonSingleFile.setEnabled(true);
            textFieldSingleFile.setEnabled(true);
            textFieldDirectory.setEnabled(false);
//            checkboxUsedRefSingle.setEnabled(false);
//            checkboxUsedRefMultiple.setEnabled(false);
//            checkboxUsedRefPreview.setEnabled(true);
//            comboboxRefSingle.setEnabled(false);
//            comboboxRefMultiple.setEnabled(false);
//            comboboxRefPreview.setEnabled(true);

        } else if(multipleFilesModeButton.isSelected()){
            browseButtonDirectory.setEnabled(true);
            browseButtonSingleFile.setEnabled(false);
            textFieldSingleFile.setEnabled(false);
            textFieldDirectory.setEnabled(true);
//            checkboxUsedRefSingle.setEnabled(false);
//            checkboxUsedRefMultiple.setEnabled(true);
//            checkboxUsedRefPreview.setEnabled(false);
//            comboboxRefSingle.setEnabled(false);
//            comboboxRefMultiple.setEnabled(true);
//            comboboxRefPreview.setEnabled(false);
        }
    }
}
