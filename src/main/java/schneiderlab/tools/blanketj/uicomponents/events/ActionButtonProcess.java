package schneiderlab.tools.blanketj.uicomponents.events;

import schneiderlab.tools.blanketj.OutputTypeName;
import schneiderlab.tools.blanketj.ProcessingMode;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.uicomponents.MainDialog;
import schneiderlab.tools.blanketj.uicomponents.workers.ProcessMultipleFileWorker;
import schneiderlab.tools.blanketj.uicomponents.workers.ProcessPreviewFileWorker;
import schneiderlab.tools.blanketj.uicomponents.workers.ProcessSingleFileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class ActionButtonProcess implements ActionListener {
    private JTextField statusBar;
    private ButtonGroup processingModeRadioButtonGroup;
    private JComboBox zStackDirectionCombobox;
    private JSpinner stiffness;
    private JSpinner filterSize;
    private JSpinner offset;
    private JSpinner depth;
    private JSpinner sigma;
    private JTextField filePathTextField;
    private JTextField directoryPathTextField;
    private JCheckBox otherChannelProjectionSingleMode;
    private JComboBox comboBoxSingleMode;
    private JCheckBox otherChannelProjectionBatchMode;
    private JComboBox comboBoxBatchMode;
    private JCheckBox otherChannelProjectionPreviewMode;
    private JComboBox comboBoxPreviewMode;
    private boolean hasManyChannels;
    private int referenceChannelIdx;
    private JProgressBar progressBar;
    private JFrame parent;
    private MainDialog mainDialogView;
    private ProcessingMode processingMode;
    private ZStackDirection zStackDirection;
    private HashSet<OutputTypeName> outputType;
    private Path dirPath;

    public ActionButtonProcess(JTextField statusBar,
                               ButtonGroup processingModeRadioButtonGroup,
                               JComboBox zStackDirectionCombobox,
                               JSpinner stiffness,
                               JSpinner filterSize,
                               JSpinner offset,
                               JSpinner depth,
                               JSpinner sigma,
                               JTextField filePathTextField,
                               JTextField directoryPathTextField,
                               JCheckBox otherChannelProjectionSingleMode,
                               JComboBox comboBoxSingleMode,
                               JCheckBox otherChannelProjectionBatchMode,
                               JComboBox comboBoxBatchMode,
                               JCheckBox otherChannelProjectionPreviewMode,
                               JComboBox comboBoxPreviewMode,
                               JProgressBar progressBar,
                               JFrame parent,
                               MainDialog mainDialogView) {
        this.statusBar = statusBar;
        this.processingModeRadioButtonGroup = processingModeRadioButtonGroup;
        this.zStackDirectionCombobox = zStackDirectionCombobox;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.offset = offset;
        this.depth = depth;
        this.sigma = sigma;
        this.filePathTextField = filePathTextField;
        this.directoryPathTextField = directoryPathTextField;
        this.otherChannelProjectionSingleMode = otherChannelProjectionSingleMode;
        this.comboBoxSingleMode = comboBoxSingleMode;
        this.otherChannelProjectionBatchMode = otherChannelProjectionBatchMode;
        this.comboBoxBatchMode = comboBoxBatchMode;
        this.otherChannelProjectionPreviewMode = otherChannelProjectionPreviewMode;
        this.comboBoxPreviewMode = comboBoxPreviewMode;
        this.progressBar = progressBar;
        this.parent = parent;
        this.mainDialogView = mainDialogView;
        this.dirPath= Paths.get(directoryPathTextField.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setProcessingMode();
//        setzStackDirection();
        zStackDirection = (ZStackDirection) zStackDirectionCombobox.getSelectedItem();
        progressBar.setValue(0);
        statusBar.setText("processing...");
        // GET THE SELECTED OUTPUT
        this.outputType=getSelectedOutputType();
        // SINGLE FILE MODE
        if (processingMode==ProcessingMode.SINGLE_FILE){
            this.hasManyChannels = otherChannelProjectionSingleMode.isSelected();
            this.referenceChannelIdx = comboBoxSingleMode.getSelectedIndex();
            ProcessSingleFileWorker processSingleFileWorker =
                    new ProcessSingleFileWorker((int)stiffness.getValue(),
                            (int)filterSize.getValue(),
                            zStackDirection,
                            (int) offset.getValue(),
                            (int) depth.getValue(),
                            (double) sigma.getValue(),
                            filePathTextField.getText(),
                            statusBar,
                            hasManyChannels,
                            referenceChannelIdx,
                            progressBar,
                            (int)mainDialogView.getComboBox3SingleFileRef().getItemCount(),
                            outputType);
            processSingleFileWorker.addPropertyChangeListener(evt ->
            {
                if ("progress".equals(evt.getPropertyName())){
                    int value = (int) evt.getNewValue();
                    progressBar.setValue(value);
                    progressBar.setToolTipText(value + "%");
                }
            });
            processSingleFileWorker.execute();
        } else if (processingMode==ProcessingMode.PREVIEW) {
            this.hasManyChannels = otherChannelProjectionPreviewMode.isSelected();
            this.referenceChannelIdx = comboBoxPreviewMode.getSelectedIndex();
            ProcessPreviewFileWorker processPreviewFileWorker =
                    new ProcessPreviewFileWorker((int)stiffness.getValue(),
                            (int)filterSize.getValue(),
                            zStackDirection,
                            (int) offset.getValue(),
                            (int) depth.getValue(),
                            (double) sigma.getValue(),
                            filePathTextField.getText(),
                            statusBar,
                            hasManyChannels,
                            referenceChannelIdx,
                            progressBar,
                            parent,
                            mainDialogView);
            processPreviewFileWorker.addPropertyChangeListener(evt ->
            {
                if ("progress".equals(evt.getPropertyName())){
                    int value = (int) evt.getNewValue();
                    progressBar.setValue(value);
                    progressBar.setToolTipText(value + "%");
                }
            });
            processPreviewFileWorker.execute();
        } else if (processingMode==ProcessingMode.MULTIPLE_FILES) {
            this.hasManyChannels = otherChannelProjectionBatchMode.isSelected();
            this.referenceChannelIdx = comboBoxBatchMode.getSelectedIndex();
            this.dirPath= Paths.get(directoryPathTextField.getText());
            ProcessMultipleFileWorker processMultipleFileWorker = new ProcessMultipleFileWorker(
                    this.dirPath,
                    outputType,
                    (int)stiffness.getValue(),
                    (int)filterSize.getValue(),
                    zStackDirection,
                    (int) offset.getValue(),
                    (int) depth.getValue(),
                    (double) sigma.getValue(),
                    hasManyChannels,
                    referenceChannelIdx,
                    statusBar,
                    progressBar,
                    (int)mainDialogView.getComboBox4BatchFileRef().getItemCount()
            );
            processMultipleFileWorker.addPropertyChangeListener(evt ->
            {
                if ("progress".equals(evt.getPropertyName())){
                    int value = (int) evt.getNewValue();
                    progressBar.setValue(value);
                    progressBar.setToolTipText(value + "%");
                }
            });
            processMultipleFileWorker.execute();
        }
    }

    private void setzStackDirection(){
        zStackDirection = (ZStackDirection) zStackDirectionCombobox.getSelectedItem();
    }

    private void setProcessingMode(){
        String processingModeString = processingModeRadioButtonGroup.getSelection().getActionCommand();
        if(processingModeString.equals("SINGLE_FILE")){
            processingMode = ProcessingMode.SINGLE_FILE;
        } else if (processingModeString.equals("PREVIEW")) {
            processingMode = ProcessingMode.PREVIEW;
        } else if (processingModeString.equals("MULTIPLE_FILES")) {
            processingMode = ProcessingMode.MULTIPLE_FILES;
        }
    }

    private HashSet<OutputTypeName> getSelectedOutputType(){
        HashSet<OutputTypeName> outputType = new HashSet<>();
        if (mainDialogView.getCheckBoxMenuItem8MIP().isSelected()){
            outputType.add(OutputTypeName.MIP);
        }
        if (mainDialogView.getCheckBoxMenuItem9MIPzMap().isSelected()){
            outputType.add(OutputTypeName.MIP_ZMAP);
        }
        if (mainDialogView.getCheckBoxMenuItem10SMP().isSelected()){
            outputType.add(OutputTypeName.SMP);
        }
        if (mainDialogView.getCheckBoxMenuItem11SMPzMap().isSelected()){
            outputType.add(OutputTypeName.SMP_ZMAP);
        }
        return outputType;
    }

}
