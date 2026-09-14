package schneiderlab.tools.blanketj.uicomponents.events;

import ij.IJ;
import ij.io.OpenDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectFileDisplayInTextField implements ActionListener {
    private final String currentDir;
    private final JFrame parent;
    private final JTextField textField;

    public SelectFileDisplayInTextField(String currentDir, JFrame parent, JTextField textField) {
        this.currentDir = currentDir;
        this.parent = parent;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser(IJ.getDirectory("current"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "TIFF Images", "tif", "tiff", "TIF", "TIFF");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = jFileChooser.showOpenDialog(parent);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            if (file != null) {
                textField.setText(file.getAbsolutePath());
                OpenDialog.setDefaultDirectory(file.getParent());
            }
        }
    }
}

