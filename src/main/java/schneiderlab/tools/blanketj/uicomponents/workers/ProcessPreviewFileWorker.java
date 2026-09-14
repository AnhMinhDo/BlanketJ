package schneiderlab.tools.blanketj.uicomponents.workers;

import ij.ImagePlus;
import ij.plugin.ChannelSplitter;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.controllers.PreviewDialogController;
import schneiderlab.tools.blanketj.helpersandutils.SmpBasedMaxUtil;
import schneiderlab.tools.blanketj.models.PreviewDialogModel;
import schneiderlab.tools.blanketj.services.HandleSingleFile;
import schneiderlab.tools.blanketj.services.HandleSingleFileWithChannels;
import schneiderlab.tools.blanketj.uicomponents.MainDialog;
import schneiderlab.tools.blanketj.uicomponents.PreviewDialog;

import javax.swing.*;
import java.nio.file.Paths;

public class ProcessPreviewFileWorker extends SwingWorker<Void, Void> {
    private JTextField statusBar;
    private JProgressBar progressBar;
    private JFrame parent;
    private MainDialog mainDialogView;

    private String filePath;
    private final int stiffness;
    private final int filterSize;
    private final ZStackDirection zStackDirection;
    private final int offset;
    private final int depth;
    private final double sigma;
    private float[] envMaxzValues;
    private final boolean hasManyChannels;
    private final int referenceChannelIdx;

    private ImagePlus projectedImage;
    private ImagePlus zMap;
    private ImagePlus projectedSMPImage;
    private ImagePlus smpZmap;
    private ImagePlus projectedSMPMIPImage;
    private ImagePlus smpMipZmap;
    private ImagePlus result;
    private ImagePlus referenceChannel;
    private ImagePlus inputImage;

    public ProcessPreviewFileWorker(int stiffness,
                                    int filterSize,
                                    ZStackDirection zStackDirection,
                                    int offset,
                                    int depth,
                                    double sigma,
                                    String filePath,
                                    JTextField statusBar,
                                    boolean hasManyChannels,
                                    int referenceChannelIdx,
                                    JProgressBar progressBar,
                                    JFrame parent,
                                    MainDialog mainDialogView) {
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.zStackDirection = zStackDirection;
        this.offset = offset;
        this.depth = depth;
        this.sigma = sigma;
        this.filePath = filePath;
        this.statusBar = statusBar;
        this.hasManyChannels = hasManyChannels;
        this.referenceChannelIdx = referenceChannelIdx;
        this.progressBar = progressBar;
        this.parent = parent;
        this.mainDialogView = mainDialogView;
    }

    @Override
    protected Void doInBackground() throws Exception {
        if(!hasManyChannels) {
            // Updating progress bar
            double percentageOf1Task = SmpBasedMaxUtil.calculatePercentageForSingleTask(1);
            double percentageOfCompletedTask = 0;
            // Perform single file projection
            ImagePlus inputImageRaw = new ImagePlus(filePath);
            this.inputImage = inputImageRaw;
            ImagePlus[] inputImageChannels = ChannelSplitter.split(inputImageRaw);
            this.referenceChannel = inputImageChannels[referenceChannelIdx];
            HandleSingleFile handleSingleFile = new HandleSingleFile(referenceChannel,
                    zStackDirection,
                    stiffness,
                    filterSize,
                    offset,
                    depth,
                    sigma);
            this.result = handleSingleFile.process();
            // Update progress bar
            percentageOfCompletedTask = percentageOfCompletedTask + percentageOf1Task;
            setProgress((int) percentageOfCompletedTask);
            return null;
        } else {
            // Update progress bar
            double percentageOf1Task = SmpBasedMaxUtil.calculatePercentageForSingleTask(1);
            double percentageOfCompletedTask = 0;
            // Perform single file projection
            this.inputImage = new ImagePlus(filePath);
            HandleSingleFileWithChannels handleSingleFileWithChannels = new HandleSingleFileWithChannels(inputImage,zStackDirection,stiffness,filterSize,offset,depth,sigma,referenceChannelIdx);
            this.result = handleSingleFileWithChannels.process();
            // Update progress bar
            percentageOfCompletedTask = percentageOfCompletedTask + percentageOf1Task;
            setProgress((int) percentageOfCompletedTask);
            return null;
        }
    }

    @Override
    protected void done(){
        statusBar.setText("");
        statusBar.setToolTipText("");
        // create the PreviewModel
        PreviewDialogModel previewDialogModel = new PreviewDialogModel(zStackDirection,
                stiffness,
                filterSize,
                offset,
                depth,
                Paths.get(filePath));
        // create the PreviewDialogView with the current parameter
        PreviewDialog previewDialogView = new PreviewDialog(parent);
        previewDialogView.getComboBox1ZStackDirection().setSelectedItem(this.zStackDirection);
        previewDialogView.getSpinner1Stiffeness().setValue(mainDialogView.getSpinner1Stiffeness().getValue());
        previewDialogView.getSpinner2FilterSize().setValue(mainDialogView.getSpinner2FilterSize().getValue());
        previewDialogView.getSpinner3Offset().setValue(mainDialogView.getSpinner3Offset().getValue());
        previewDialogView.getSpinner4Depth().setValue(mainDialogView.getSpinner4Depth().getValue());
        // show the result image
        result.setTitle(inputImage.getTitle());
        result.show();
        // create the PreviewDialog controller
        PreviewDialogController previewDialogController = new PreviewDialogController(previewDialogView,
                previewDialogModel,
                Paths.get(filePath),
                mainDialogView,
                hasManyChannels,
                referenceChannelIdx,
                result,
                referenceChannel);
        // show the previewDialog
        previewDialogView.setTitle(inputImage.getTitle());
        previewDialogView.pack();
        previewDialogView.setVisible(true);
    }
}
