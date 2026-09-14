package schneiderlab.tools.blanketj.uicomponents.events;

import schneiderlab.tools.blanketj.uicomponents.MainDialog;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DropFileOrDirTransferHandler extends TransferHandler {
    MainDialog mainView;
    public DropFileOrDirTransferHandler(MainDialog mainView){
        this.mainView=mainView;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.COPY;  // Essential for drops from external apps
    }

    @Override
    public boolean canImport(TransferSupport support){
        return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) return false;
        Transferable t = support.getTransferable();
        try {
            List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
            File importedFile = files.get(0);
            if (importedFile.isFile()){
                mainView.getTextField2SingleFilePath().setText(
                        importedFile.getAbsolutePath()
                );
            } else {
                mainView.getTextField1DirPath().setText(
                        importedFile.getAbsolutePath()
                );
            }
            return true;
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
