package schneiderlab.tools.blanketj.services;

import ij.CompositeImage;
import ij.ImagePlus;
import ij.plugin.ChannelSplitter;
import ij.plugin.RGBStackMerge;
import ij.process.LUT;
import schneiderlab.tools.blanketj.OutputTypeName;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.corecomputation.ManifoldBypassProjection;
import schneiderlab.tools.blanketj.corecomputation.SMP_MIP_Projection;
import schneiderlab.tools.blanketj.helpersandutils.SmpBasedMaxUtil;

import java.nio.file.Path;
import java.util.HashSet;

public class HandleSingleFileWithChannelsSaveOutput {
    private Path inputPath;
    private ImagePlus inputImage;
    private HashSet<OutputTypeName> outputTypeSet;
    private final int stiffness;
    private final int filterSize;
    private final ZStackDirection zStackDirection;
    private final int offset;
    private final int depth;
    private final double sigma;
    private float[] envMaxzValues;
    private int refIdx;

    public ImagePlus inputImageRef;
    private ImagePlus MIPImage;
    private ImagePlus zMap;
    private CompositeImage merged;
    private ImagePlus smpZmap;


    public HandleSingleFileWithChannelsSaveOutput(Path inputPath,
                                               ImagePlus inputImage,
                                               HashSet<OutputTypeName> outputTypeSet,
                                               int stiffness, int filterSize,
                                               ZStackDirection zStackDirection,
                                               int offset,
                                               int depth,
                                              double sigma,
                                              int refIdx) {
        this.inputPath = inputPath;
        this.inputImage = inputImage;
        this.outputTypeSet = outputTypeSet;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.zStackDirection = zStackDirection;
        this.offset = offset;
        this.depth = depth;
        this.sigma = sigma;
        this.refIdx=refIdx;
    }

    public void processAndSaveOutput(){
        performProcessing();
        saveOutputToFile();
    }

    public ImagePlus process(){
        performProcessing();
        return merged;
    }

    public ImagePlus getSMPOutput(){
        if(this.merged == null){
            throw new IllegalStateException("Image not processed yet.");
        } else{
            return merged;
        }
    }

    public ImagePlus getSMPzMapOutput(){
        if(this.smpZmap == null){
            throw new IllegalStateException("Image not processed yet.");
        } else{
            return this.smpZmap;
        }
    }

    private void performProcessing(){
        // split the channels and get the reference
        ImagePlus[] channels = ChannelSplitter.split(inputImage);
        ImagePlus[] outputChannels = new ImagePlus[channels.length];
        ImagePlus referenceChannel = channels[refIdx];
        HandleSingleFileNoChannelSaveOutput hsfncso = new HandleSingleFileNoChannelSaveOutput(inputPath,
                referenceChannel,
                outputTypeSet,
                stiffness,
                filterSize,
                zStackDirection,
                offset,
                depth,
                sigma);
        ImagePlus refSMP = hsfncso.process();
        outputChannels[refIdx] = refSMP;
        this.envMaxzValues = hsfncso.getEnvMaxzValues();
        this.MIPImage = hsfncso.getMIPOutput();
        this.zMap = hsfncso.getMIPzMapOutput();
        this.smpZmap = hsfncso.getSMPzMapOutput();
        // apply map to other channels
        for (int i = 0; i < channels.length; i++) {
            if (i != refIdx) {
                ImagePlus outputChannel = processOtherChannel(channels[i],envMaxzValues,zStackDirection,stiffness,filterSize,offset,depth);
                outputChannels[i] = outputChannel;
            }
        }
        ImagePlus rawMerged = RGBStackMerge.mergeChannels(outputChannels,true);
        if (!(inputImage instanceof CompositeImage)) {
            inputImage = new CompositeImage(inputImage, CompositeImage.COLOR);
        }
        LUT[] luts = ((CompositeImage) inputImage).getLuts();

        // Ensure merged is CompositeImage to set LUTs
        if (!(rawMerged instanceof CompositeImage)) {
            rawMerged = new CompositeImage(rawMerged, CompositeImage.COLOR);
        }

        // Apply LUTs from inputImage to merged channels
        merged = (CompositeImage) rawMerged;
        for (int c = 1; c <= merged.getNChannels(); c++) {
            merged.setChannelLut(luts[c - 1], c);
        }
    }

    private static ImagePlus processOtherChannel (ImagePlus otherChannel,
                                                  float[] envMaxzValues,
                                                  ZStackDirection zStackDirection,
                                                  int stiffness,
                                                  int filterSize,
                                                  int offset,
                                                  int depth) {
        SmpBasedMaxUtil.preProcessInputImage(otherChannel);
        ManifoldBypassProjection mbProjector = new ManifoldBypassProjection(otherChannel, envMaxzValues, offset);
        ImagePlus projection = mbProjector.doManifoldBypassProjection();
        ImagePlus zMapProjection = mbProjector.getManifoldBypassZmap();
        if (depth != 0) {
            SMP_MIP_Projection smpMipProjector = new SMP_MIP_Projection(otherChannel, zMapProjection, depth, zStackDirection);
            return smpMipProjector.doProjection();
        } else {
            return projection;
        }
    }

    private void saveOutputToFile() {
        for (OutputTypeName outputType : outputTypeSet) {
            if (outputType == null) continue;

            if (outputType == OutputTypeName.MIP) {
                saveMIP();
            } else if (outputType == OutputTypeName.MIP_ZMAP) {
                saveMIPZMap();
            } else if (outputType == OutputTypeName.SMP) {
                saveSMP();
            } else if (outputType == OutputTypeName.SMP_ZMAP) {
                saveSMPZMap();
            }
        }
    }

    private void saveMIP(){
        SmpBasedMaxUtil.savePostProcessImagePlus(this.MIPImage,
                OutputTypeName.MIP,
                inputPath,
                this.stiffness,
                this.filterSize,
                this.offset,
                this.depth,
                this.sigma);
    }

    private void saveMIPZMap(){
        SmpBasedMaxUtil.savePostProcessImagePlusZmap(this.zMap,
                OutputTypeName.MIP_ZMAP,
                inputPath,
                this.stiffness,
                this.filterSize,
                this.offset,
                this.depth,
                this.sigma);
    }

    private void saveSMP(){
        if(depth == 0){
            SmpBasedMaxUtil.savePostProcessImagePlus(this.merged,
                    OutputTypeName.SMP,
                    inputPath,
                    this.stiffness,
                    this.filterSize,
                    this.offset,
                    this.depth,
                    this.sigma);
        } else {
            SmpBasedMaxUtil.savePostProcessImagePlus(this.merged,
                    OutputTypeName.SMPbasedMIP,
                    inputPath,
                    this.stiffness,
                    this.filterSize,
                    this.offset,
                    this.depth,
                    this.sigma);
        }
    }

    private void saveSMPZMap(){
        if(depth == 0){
            SmpBasedMaxUtil.savePostProcessImagePlusZmap(this.smpZmap,
                    OutputTypeName.SMP_ZMAP,
                    inputPath,
                    this.stiffness,
                    this.filterSize,
                    this.offset,
                    this.depth,
                    this.sigma);
        } else {
            SmpBasedMaxUtil.savePostProcessImagePlusZmap(this.smpZmap,
                    OutputTypeName.SMPbasedMIP_ZMAP,
                    inputPath,
                    this.stiffness,
                    this.filterSize,
                    this.offset,
                    this.depth,
                    this.sigma);
        }
    }
}
