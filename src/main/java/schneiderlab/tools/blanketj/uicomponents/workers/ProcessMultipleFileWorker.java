package schneiderlab.tools.blanketj.uicomponents.workers;

import ij.ImagePlus;
import ij.plugin.ChannelSplitter;
import schneiderlab.tools.blanketj.OutputTypeName;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.services.HandleSingleFileNoChannelSaveOutput;
import schneiderlab.tools.blanketj.services.HandleSingleFileWithChannelsSaveOutput;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;

public class ProcessMultipleFileWorker extends SwingWorker<Void, Void> {

    private HashMap<Path, HashSet<OutputTypeName>> hashMapInputPathsAndOutputHashSet;
    private Path directory;
    private HashSet<OutputTypeName> outputType;
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

    private JTextField statusBar;
    private JProgressBar progressBar;

    public ProcessMultipleFileWorker(Path directory,
                                     HashSet<OutputTypeName> outputType,
                                     int stiffness,
                                     int filterSize,
                                     ZStackDirection zStackDirection,
                                     int offset,
                                     int depth,
                                     double sigma,
                                     boolean hasManyChannels,
                                     int referenceChannelIdx,
                                     JTextField statusBar,
                                     JProgressBar progressBar,
                                     int numberOfChannels) {
        this.directory = directory;
        this.outputType = outputType;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.zStackDirection = zStackDirection;
        this.offset = offset;
        this.depth = depth;
        this.sigma = sigma;
        this.hasManyChannels = hasManyChannels;
        this.referenceChannelIdx = referenceChannelIdx;
        this.statusBar = statusBar;
        this.progressBar = progressBar;
        this.numberOfChannels= numberOfChannels;
        
    }
    /*
    TODO:
     handle file selection (logic: tif files only, if possible, identify result files from previous run and only use the original file)
     only pass valid file to the Multiple File service
     handle run to skip file with errors and continue to process the rest, list out the error file.
     Only tiff files with more than 1 slice
     create Path object array,
     loop through the array, each time setProgress to update the progress bar
     */
    @Override
    protected Void doInBackground() {
        if(!hasManyChannels) {
            this.hashMapInputPathsAndOutputHashSet = selectValidFilePathsAndGenerateInputHashMap(this.directory,outputType);
            // this is for tracking and update on the progress bar
            int totalNumberOfFile = hashMapInputPathsAndOutputHashSet.size();
            int current = 0;
            // Loop and process each file sequentially
            for(Path inputPath : hashMapInputPathsAndOutputHashSet.keySet()){
                ImagePlus inputImageRaw = new ImagePlus(inputPath.toAbsolutePath().toString());
                ImagePlus[] inputImageChannels = ChannelSplitter.split(inputImageRaw);
                ImagePlus inputImage = inputImageChannels[referenceChannelIdx];
                HandleSingleFileNoChannelSaveOutput hsfncso = new HandleSingleFileNoChannelSaveOutput(inputPath,
                        inputImage,
                        hashMapInputPathsAndOutputHashSet.get(inputPath),
                        stiffness,
                        filterSize,
                        zStackDirection,
                        offset,
                        depth,
                        sigma);
                hsfncso.processAndSaveOutput();
                current++;
                int progress = (int) ((current * 100.0f) / totalNumberOfFile);
                setProgress(progress);
        }
        } else {
            this.hashMapInputPathsAndOutputHashSet = selectValidFilePathsAndGenerateInputHashMap(this.directory,outputType);
            // this is for tracking and update on the progress bar
            int totalNumberOfFile = hashMapInputPathsAndOutputHashSet.size();
            int current = 0;
            // Loop and process each file sequentially
            for(Path inputPath : hashMapInputPathsAndOutputHashSet.keySet()){
                ImagePlus inputImage = new ImagePlus(inputPath.toAbsolutePath().toString());
                HandleSingleFileWithChannelsSaveOutput hsfwcso = new HandleSingleFileWithChannelsSaveOutput(inputPath,
                        inputImage,
                        hashMapInputPathsAndOutputHashSet.get(inputPath),
                        stiffness,
                        filterSize,
                        zStackDirection,
                        offset,
                        depth,
                        sigma,
                        referenceChannelIdx);
                hsfwcso.processAndSaveOutput();
                current++;
                int progress = (int) ((current * 100.0f) / totalNumberOfFile);
                setProgress(progress);
            }
            }
        return null;
    }

    @Override
    public void done(){
        statusBar.setText("Complete Processing");
        statusBar.setToolTipText("Complete Processing");
    }

    /**
     * Scans the given directory for `.tif` files and constructs a map where each valid file path
     * is associated with the provided set of output types.
     *
     * @param inputDirPath the path to the directory containing input `.tif` files
     * @param outputType a set of {@code OutputTypeName} to associate with each valid file
     * @return a {@code HashMap} mapping each `.tif` file {@code Path} in the directory
     *         to the provided {@code outputType} set; returns an empty map if no valid files found
     */
    private HashMap<Path, HashSet<OutputTypeName>> selectValidFilePathsAndGenerateInputHashMap(Path inputDirPath,HashSet<OutputTypeName> outputType){
        File dir = inputDirPath.toFile();
        File[] tifFiles = dir.listFiles(file ->
                file.isFile() && file.getName().toLowerCase().endsWith(".tif")
        );
        HashMap<Path, HashSet<OutputTypeName>> outputHashMap = new HashMap<>();
        if (tifFiles != null){
            for(File file : tifFiles){
                outputHashMap.put(file.toPath(),outputType);
            }
        }
        return outputHashMap;
    }
}
