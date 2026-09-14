package schneiderlab.tools.blanketj.uicomponents.workers;

import ij.ImagePlus;
import ij.plugin.ChannelSplitter;
import schneiderlab.tools.blanketj.OutputTypeName;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.helpersandutils.SmpBasedMaxUtil;
import schneiderlab.tools.blanketj.services.HandleSingleFileNoChannelSaveOutput;
import schneiderlab.tools.blanketj.services.HandleSingleFileWithChannelsSaveOutput;

import javax.swing.*;
import java.nio.file.Paths;
import java.util.HashSet;

public class ProcessSingleFileWorker extends SwingWorker<Void,Void> {
    private JTextField statusBar;
    private JProgressBar progressBar;

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
    private final int numberOfChannels;
    private HashSet<OutputTypeName> outputType;

    private ImagePlus projectedImage;
    private ImagePlus zMap;
    private ImagePlus projectedSMPImage;
    private ImagePlus smpZmap;
    private ImagePlus projectedSMPMIPImage;
    private ImagePlus smpMipZmap;
    private ImagePlus result;

    public ProcessSingleFileWorker(int stiffness,
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
                                   int numberOfChannels,
                                   HashSet<OutputTypeName> outputType) {
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
        this.numberOfChannels= numberOfChannels;
        this.outputType= outputType;
    }

    @Override
    protected Void doInBackground() throws Exception {
        if(!hasManyChannels && numberOfChannels==1) {
            // Updating progress bar
            double percentageOf1Task = SmpBasedMaxUtil.calculatePercentageForSingleTask(1);
            double percentageOfCompletedTask = 0;
            // Perform single file projection
            ImagePlus inputImageRaw = new ImagePlus(filePath);
            ImagePlus[] inputImageChannels = ChannelSplitter.split(inputImageRaw);
            ImagePlus inputImage = inputImageChannels[referenceChannelIdx];
            HandleSingleFileNoChannelSaveOutput hsfncso = new HandleSingleFileNoChannelSaveOutput(
                    Paths.get(filePath),
                    inputImage,
                    outputType,
                    stiffness,
                    filterSize,
                    zStackDirection,
                    offset,
                    depth,
                    sigma);
            hsfncso.processAndSaveOutput();
            // Update progress bar
            percentageOfCompletedTask = percentageOfCompletedTask + percentageOf1Task;
            setProgress((int) percentageOfCompletedTask);
            return null;

        } else {
            // Update progress bar
            double percentageOf1Task = SmpBasedMaxUtil.calculatePercentageForSingleTask(1);
            double percentageOfCompletedTask = 0;
            // Perform single file projection
            ImagePlus inputImage = new ImagePlus(filePath);
            HandleSingleFileWithChannelsSaveOutput hsfwcso = new HandleSingleFileWithChannelsSaveOutput(
                    Paths.get(filePath),
                    inputImage,
                    outputType,
                    stiffness,
                    filterSize,
                    zStackDirection,
                    offset,
                    depth,
                    sigma,
                    referenceChannelIdx);
            hsfwcso.processAndSaveOutput();
            // Update progress bar
            percentageOfCompletedTask = percentageOfCompletedTask + percentageOf1Task;
            setProgress((int) percentageOfCompletedTask);
            saveOutputToFile(result,filePath);
            return null;
        }
    }

    @Override
    protected void done(){
        statusBar.setText("Complete Processing");
        statusBar.setToolTipText("Complete Processing");
    }

        private void saveOutputToFile(ImagePlus output, String filePath){
            // prepare the directory for output
//            String resultDir = SmpBasedMaxUtil.createResultDir(filePath,
//                    zStackDirection,
//                    stiffness,
//                    filterSize,
//                    offset,
//                    depth);
//            String fileName = SmpBasedMaxUtil.extractFilename(filePath);

            // Save SMP projected image and zMap
            if(depth == 0 ) {
                SmpBasedMaxUtil.savePostProcessImagePlus(output, OutputTypeName.SMP, Paths.get(filePath), stiffness, filterSize, offset, depth,sigma);
//            SmpBasedMaxUtil.savePostProcessImagePlus(this.smpZmap, OutputTypeName.SMP_ZMAP,resultDir,fileName, stiffness, filterSize, offset, depth);
            }
            // Save SMP depth-adjusted image and zMap
            if (depth != 0) {
                SmpBasedMaxUtil.savePostProcessImagePlus(output, OutputTypeName.SMPbasedMIP,Paths.get(filePath), stiffness, filterSize, offset, depth, sigma);
//                SmpBasedMaxUtil.savePostProcessImagePlus(this.smpMipZmap, OutputTypeName.SMPbasedMIP_ZMAP,resultDir,fileName, stiffness, filterSize, offset, depth);
            }
        }


}
