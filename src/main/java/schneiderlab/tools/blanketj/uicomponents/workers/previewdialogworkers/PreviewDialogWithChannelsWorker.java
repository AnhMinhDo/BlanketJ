package schneiderlab.tools.blanketj.uicomponents.workers.previewdialogworkers;

import ij.ImagePlus;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.services.HandleSingleFileWithChannels;
import schneiderlab.tools.blanketj.uicomponents.PreviewDialog;

import javax.swing.*;
import java.nio.file.Path;

public class PreviewDialogWithChannelsWorker extends SwingWorker<Void, Void> {
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
    private final int referenceChannelIdx;

    private ImagePlus projectedImage;
    private ImagePlus zMap;
    private ImagePlus projectedSMPImage;
    private ImagePlus smpZmap;
    private ImagePlus projectedSMPMIPImage;
    private ImagePlus smpMipZmap;
    private ImagePlus result;

    public PreviewDialogWithChannelsWorker (JTextField statusBar,
                                              JProgressBar progressBar,
                                              PreviewDialog previewDialogView,
                                              Path filePath,
                                              int stiffness,
                                              int filterSize,
                                              ZStackDirection zStackDirection,
                                              int offset,
                                              int depth,
                                              double sigma,
                                              ImagePlus result,
                                              int refChannelIdx) {
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
        this.referenceChannelIdx=refChannelIdx;
    }

    @Override
    protected Void doInBackground()  {
        ImagePlus input = new ImagePlus(filePath.toAbsolutePath().toString());
        HandleSingleFileWithChannels handleSingleFileWithChannels = new HandleSingleFileWithChannels(input,
                zStackDirection,
                stiffness,
                filterSize,
                offset,
                depth,
                sigma,
                referenceChannelIdx);
        ImagePlus output = handleSingleFileWithChannels.process();
//        ImageProcessor outputProcessor = output.getProcessor();
//        result.setProcessor(outputProcessor);
        result.setStack(output.getStack());
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
