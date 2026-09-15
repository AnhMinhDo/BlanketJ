package schneiderlab.tools.blanketj;

import ij.plugin.PlugIn;
import schneiderlab.tools.blanketj.controllers.AfterMainDialogPacked;
import schneiderlab.tools.blanketj.controllers.MainController;
import schneiderlab.tools.blanketj.models.MainModel;
import schneiderlab.tools.blanketj.uicomponents.MainDialog;

import javax.swing.*;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;

public class Blanket_J implements PlugIn {

    @Override
    public void run(String arg) {
        SwingUtilities.invokeLater(this::launchUI);
    }

    public void launchUI (){
        String osName = System.getProperty("os.name").toLowerCase();
//        try {
//            if (osName.contains("mac")){
//                com.formdev.flatlaf.themes.FlatMacLightLaf.setup();
//            } else {
//                com.formdev.flatlaf.FlatLightLaf.setup();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        JFrame frame = new JFrame("BlanketJ");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MainModel mainModel = new MainModel(ProcessingMode.SINGLE_FILE,
                ThemeMode.LIGHT,
                ZStackDirection.IN,
                10,
                30,
                0,
                0,
                Paths.get(""),
                Paths.get(""),
                new HashSet<>(Collections.singletonList(OutputTypeName.SMP)));
        MainDialog form = new MainDialog(frame);
        MainController mainController = new MainController(mainModel,form);
        frame.setContentPane(form.getContentPane());
        frame.pack();
        frame.setVisible(true);
        // the function below is for using the current selected opened image as the input so the user does not need to search for the same image file with the browse button
        AfterMainDialogPacked.useCurrentImageAsInput(form);
    }


}
