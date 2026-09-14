package schneiderlab.tools.blanketj.uicomponents.events;

import ij.IJ;
import ij.io.OpenDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectDirDisplayInTextField implements ActionListener {
    private final String currentDir;
    private final JFrame parent;
    private final JTextField textField;

    public SelectDirDisplayInTextField(String currentDir, JFrame parent, JTextField textField) {
        this.currentDir = currentDir;
        this.parent = parent;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser(IJ.getDirectory("current"));
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jFileChooser.showOpenDialog(parent);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File dir = jFileChooser.getSelectedFile();
            if (dir != null) {
                textField.setText(dir.getAbsolutePath());
                OpenDialog.setDefaultDirectory(dir.getAbsolutePath());
            }
        }
    }
}
