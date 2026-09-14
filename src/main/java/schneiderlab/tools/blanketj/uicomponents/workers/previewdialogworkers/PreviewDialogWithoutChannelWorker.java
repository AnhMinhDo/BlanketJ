package schneiderlab.tools.blanketj.uicomponents.workers.previewdialogworkers;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.services.HandleSingleFile;
import schneiderlab.tools.blanketj.uicomponents.PreviewDialog;

import javax.swing.*;
import java.nio.file.Path;

public class PreviewDialogWithoutChannelWorker extends SwingWorker<Void, Void>{
    private JTextField statusBar;
    private JProgressBar progressBar;
    private PreviewDialog previewDialogView;

    private Path filePath;
    private final int stiffness;
    private final int filterSize;
    private final ZStackDirection zStackDirection;
    private final int offset;
    private final int depth;
    private final double sigma;
    private float[] envMaxzValues;
//    private final boolean hasManyChannels;
//    private final int referenceChannelIdx;

    private ImagePlus projectedImage;
    private ImagePlus zMap;
    private ImagePlus projectedSMPImage;
    private ImagePlus smpZmap;
    private ImagePlus projectedSMPMIPImage;
    private ImagePlus smpMipZmap;
    private ImagePlus result;
    private ImagePlus inputImage;

    public PreviewDialogWithoutChannelWorker (JTextField statusBar, JProgressBar progressBar, PreviewDialog previewDialogView, Path filePath, int stiffness, int filterSize, ZStackDirection zStackDirection, int offset, int depth, double sigma, ImagePlus result, ImagePlus inputImage) {
        this.statusBar = statusBar;
        this.progressBar = progressBar;
        this.previewDialogView = previewDialogView;
        this.filePath = filePath;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.zStackDirection = zStackDirection;
        this.offset = offset;
        this.depth = depth;
        this.sigma = sigma;
        this.result = result;
        this.inputImage= inputImage;
    }

    @Override
    protected Void doInBackground() throws Exception {
//        ImagePlus input = new ImagePlus(filePath.toAbsolutePath().toString());
        HandleSingleFile handleSingleFile = new HandleSingleFile(inputImage,
                zStackDirection,
                stiffness,
                filterSize,
                offset,
                depth,
                sigma);
        ImagePlus output = handleSingleFile.process();
        ImageProcessor outputProcessor = output.getProcessor();
        result.setProcessor(outputProcessor);
        return null;
    }

    @Override
    protected void done(){
        progressBar.setValue(100);
        statusBar.setText("Complete");
        statusBar.setToolTipText("Complete");
        result.updateAndRepaintWindow();

    }
}
