package schneiderlab.tools.blanketj.controllers;

import schneiderlab.tools.blanketj.*;
import schneiderlab.tools.blanketj.models.MainModel;
import schneiderlab.tools.blanketj.uicomponents.MainDialog;
import schneiderlab.tools.blanketj.uicomponents.events.*;

import javax.swing.*;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;

public class MainController {
    private final MainModel model;
    private final MainDialog view;

    public MainController(MainModel model, MainDialog view) {
        this.model = model;
        this.view = view;

        //
        JComponent component = (JComponent) view.getContentPane();
        component.setTransferHandler(new DropFileOrDirTransferHandler(view));

        // set selection when first start the plugin
        view.getRadioButton3Preview().setSelected(true);

        // Action to show the checkbox list for output type
        view.getButton3SelectOutput()
                .addActionListener(new ShowPopUpMenu(view.getButton3SelectOutput(),view.getPopupMenu1Output()));

//        // for toggleButton switching light and dark mode
//        view.getToggleButton1LightDarkTheme()
//                .addActionListener(new ToggleLightDarkMode(view.getToggleButton1LightDarkTheme(), view.getParentFrame()));

        // for button browse button directory
        view.getButton1BrowseDirPath()
                .addActionListener(new SelectDirDisplayInTextField("",
                view.getParentFrame(),
                view.getTextField1DirPath()));

        // for button browse button file
        view.getButton2BrowseSingleFile()
                .addActionListener(new SelectFileDisplayInTextField("",
                view.getParentFrame(),
                view.getTextField2SingleFilePath()));

        // Cancel button
        view.getButton5Cancel()
                .addActionListener(new CloseMainWindow(view.getParentFrame()));

        // Add tooltip to textFields to view full file path
        view.getTextField1DirPath().getDocument()
                .addDocumentListener(new TooltipTextFieldUpdate(view.getTextField1DirPath()));
        view.getTextField2SingleFilePath().getDocument()
                .addDocumentListener(new TooltipTextFieldUpdate(view.getTextField2SingleFilePath()));
        view.getTextField3Status().getDocument()
                .addDocumentListener(new TooltipTextFieldUpdate(view.getTextField3Status()));
        // update channels combobox when textFields get new file
        view.getTextField2SingleFilePath().getDocument()
                .addDocumentListener(
                        new TrackChangeInTheTextFieldUpdateChannelsAvailability(
                                view.getTextField2SingleFilePath(),
                        view.getComboBox3SingleFileRef(),
                        view.getCheckBox2SingleFileRef(),
                        view.getComboBox5PreviewRef(),
                        view.getCheckBox4PreviewRef(),
                                view.getRadioButton1SingleFile(),
                                view.getRadioButton3Preview()));
        // update channels combobox multiple file when textFields get new directory
        view.getTextField1DirPath().getDocument()
                        .addDocumentListener(
                                new TrackChangeInTheDirTextFieldUpdateChannelsAvailability(
                                        view.getTextField1DirPath(),
                                        view.getComboBox4BatchFileRef(),
                                        view.getCheckBox3BatchFileRef()
                                )
                        );
        // SingleMode radio Button
        view.getRadioButton1SingleFile()
                .addItemListener(new ProcessModeGroupButtonStateListener(
                view.getButton2BrowseSingleFile(),
                view.getButton1BrowseDirPath(),
                view.getTextField2SingleFilePath(),
                view.getTextField1DirPath(),
                view.getRadioButton1SingleFile(),
                view.getRadioButton3Preview(),
                view.getRadioButton2MultipleFiles(),
                view.getCheckBox2SingleFileRef(),
                view.getCheckBox3BatchFileRef(),
                view.getCheckBox4PreviewRef(),
                view.getComboBox3SingleFileRef(),
                view.getComboBox4BatchFileRef(),
                view.getComboBox5PreviewRef()

        ));
        // Preview Mode radio Button
        view.getRadioButton3Preview()
                .addItemListener(new ProcessModeGroupButtonStateListener(
                view.getButton2BrowseSingleFile(),
                view.getButton1BrowseDirPath(),
                view.getTextField2SingleFilePath(),
                view.getTextField1DirPath(),
                view.getRadioButton1SingleFile(),
                view.getRadioButton3Preview(),
                view.getRadioButton2MultipleFiles(),
                view.getCheckBox2SingleFileRef(),
                view.getCheckBox3BatchFileRef(),
                view.getCheckBox4PreviewRef(),
                view.getComboBox3SingleFileRef(),
                view.getComboBox4BatchFileRef(),
                view.getComboBox5PreviewRef()));

        // Multiple File Mode radio button
        view.getRadioButton2MultipleFiles()
                .addItemListener(new ProcessModeGroupButtonStateListener(
                        view.getButton2BrowseSingleFile(),
                        view.getButton1BrowseDirPath(),
                        view.getTextField2SingleFilePath(),
                        view.getTextField1DirPath(),
                        view.getRadioButton1SingleFile(),
                        view.getRadioButton3Preview(),
                        view.getRadioButton2MultipleFiles(),
                        view.getCheckBox2SingleFileRef(),
                        view.getCheckBox3BatchFileRef(),
                        view.getCheckBox4PreviewRef(),
                        view.getComboBox3SingleFileRef(),
                        view.getComboBox4BatchFileRef(),
                        view.getComboBox5PreviewRef()));

        // MouseWheel action event for spinner
        view.getSpinner1Stiffeness()
                .addMouseWheelListener(new ActionMouseWheelSpinnerInteger(view.getSpinner1Stiffeness()));
        view.getSpinner2FilterSize()
                .addMouseWheelListener(new ActionMouseWheelSpinnerInteger(view.getSpinner2FilterSize()));
        view.getSpinner3Offset()
                .addMouseWheelListener(new ActionMouseWheelSpinnerInteger(view.getSpinner3Offset()));
        view.getSpinner4Depth()
                .addMouseWheelListener(new ActionMouseWheelSpinnerInteger(view.getSpinner4Depth()));

        //checkbox for use channels
        view.getCheckBox2SingleFileRef()
                .addItemListener(new EventChannelsSelectionCheckboxSelected(view.getCheckBox2SingleFileRef(),
                        view.getComboBox3SingleFileRef()));
        view.getCheckBox3BatchFileRef()
                .addItemListener(new EventChannelsSelectionCheckboxSelected(view.getCheckBox3BatchFileRef(),
                        view.getComboBox4BatchFileRef()));
        view.getCheckBox4PreviewRef()
                .addItemListener(new EventChannelsSelectionCheckboxSelected(view.getCheckBox4PreviewRef(),
                        view.getComboBox5PreviewRef()));
        view.getCheckBox2SingleFileRef()
                .addPropertyChangeListener(new EventChannelsSelectionCheckboxEnabled(view.getCheckBox2SingleFileRef(),
                        view.getComboBox3SingleFileRef()));
        view.getCheckBox3BatchFileRef()
                .addPropertyChangeListener(new EventChannelsSelectionCheckboxEnabled(view.getCheckBox3BatchFileRef(),
                        view.getComboBox4BatchFileRef()));
        view.getCheckBox4PreviewRef()
                .addPropertyChangeListener(new EventChannelsSelectionCheckboxEnabled(view.getCheckBox4PreviewRef(),
                        view.getComboBox5PreviewRef()));

        //Perform the main function, Update the progress bar and status bar to track the current progress
        view.getButton4StartProcess().addActionListener(
                new ActionButtonProcess(view.getTextField3Status(),
                view.getButtonGroup1ProcessMode(),
                view.getComboBox1ZStackDirection(),
                view.getSpinner1Stiffeness(),
                view.getSpinner2FilterSize(),
                view.getSpinner3Offset(),
                view.getSpinner4Depth(),
                view.getSpinnerSigma(),
                view.getTextField2SingleFilePath(),
                view.getTextField1DirPath(),
                view.getCheckBox2SingleFileRef(),
                view.getComboBox3SingleFileRef(),
                view.getCheckBox3BatchFileRef(),
                view.getComboBox4BatchFileRef(),
                view.getCheckBox4PreviewRef(),
                view.getComboBox5PreviewRef(),
                view.getProgressBar1(),
                view.getParentFrame(),
                        view));

        //Set actionCommand for each radio button in ProcessMode RadioButtonGroup
        view.getRadioButton1SingleFile().setActionCommand("SINGLE_FILE");
        view.getRadioButton2MultipleFiles().setActionCommand("MULTIPLE_FILES");
        view.getRadioButton3Preview().setActionCommand("PREVIEW");
    }
    private void updateModel(){
        model.setProcessingMode(userSelectedProcessingMode(view));
        model.setStiffness((int)view.getSpinner1Stiffeness().getValue());
        model.setFilterSize((int)view.getSpinner2FilterSize().getValue());
        model.setOffset((int)view.getSpinner3Offset().getValue());
        model.setDepth((int)view.getSpinner4Depth().getValue());
        model.setDirectoryPath(Paths.get(view.getTextField1DirPath().getText()));
        model.setFilePath(Paths.get(view.getTextField2SingleFilePath().getText()));
        model.setOutput(userOutputSelection(view));
    }

    public ProcessingMode userSelectedProcessingMode(MainDialog view){
        ProcessingMode processingMode = null;
        String processingModeString = view.getButtonGroup1ProcessMode()
                .getSelection().getActionCommand();
        switch (processingModeString) {
            case "SINGLE_FILE":
                processingMode = ProcessingMode.SINGLE_FILE;
                break;
            case "MULTIPLE_FILES":
                processingMode = ProcessingMode.MULTIPLE_FILES;
                break;
            case "PREVIEW":
                processingMode = ProcessingMode.PREVIEW;
                break;
        }
        return processingMode;
    }

    public HashSet<OutputTypeName> userOutputSelection(MainDialog view){
        HashSet<OutputTypeName> hashSetOutputType = new HashSet<>();
        if (view.getCheckBoxMenuItem8MIP().isSelected()){
            hashSetOutputType.add(OutputTypeName.MIP);
        }
         if (view.getCheckBoxMenuItem9MIPzMap().isSelected()) {
            hashSetOutputType.add(OutputTypeName.MIP_ZMAP);
        }
         if (view.getCheckBoxMenuItem10SMP().isSelected()) {
            hashSetOutputType.add(OutputTypeName.SMP);
        }
         if (view.getCheckBoxMenuItem11SMPzMap().isSelected()){
             hashSetOutputType.add(OutputTypeName.SMP_ZMAP);
         }
         return hashSetOutputType;
    }

    public ZStackDirection userSelectedZStackDirection(MainDialog view){
        ZStackDirection zStackDirection = null;
        String zStackDirectionString = (String) view.getComboBox1ZStackDirection().getSelectedItem();
        switch (Objects.requireNonNull(zStackDirectionString)) {
            case "IN":
                zStackDirection = ZStackDirection.IN;
                break;
            case "OUT":
                zStackDirection = ZStackDirection.OUT;
                break;
        }
        return zStackDirection;
    }
}
