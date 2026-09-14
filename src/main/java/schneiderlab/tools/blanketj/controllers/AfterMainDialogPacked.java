package schneiderlab.tools.blanketj.controllers;

import ij.ImagePlus;
import ij.io.FileInfo;
import schneiderlab.tools.blanketj.uicomponents.MainDialog;

public class AfterMainDialogPacked {
    public static void useCurrentImageAsInput(MainDialog mainDialog){
        // if there is an open window image; use it as the input
        if(ij.WindowManager.getCurrentImage() != null){
            ImagePlus currentImage = ij.WindowManager.getCurrentImage();
            FileInfo fileInfo = currentImage.getOriginalFileInfo();
            String filepath = fileInfo.getFilePath();
            mainDialog.getTextField2SingleFilePath().setText(filepath);
        }
    }
}
