package schneiderlab.tools.blanketj.services;


import ij.CompositeImage;
import ij.ImagePlus;
import ij.plugin.ChannelSplitter;
import ij.plugin.RGBStackMerge;
import ij.process.LUT;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.corecomputation.ManifoldBypassProjection;
import schneiderlab.tools.blanketj.corecomputation.SMP_MIP_Projection;
import schneiderlab.tools.blanketj.helpersandutils.SmpBasedMaxUtil;


public class HandleSingleFileWithChannels {
    private ImagePlus input;
    private ZStackDirection zStackDirection;
    private int stiffness;
    private int filterSize;
    private int offset;
    private int depth;
    private double sigma;
    private ImagePlus output;
    private int referenceIndx;
    private CompositeImage merged;


    public HandleSingleFileWithChannels(ImagePlus input,
                                        ZStackDirection zStackDirection,
                                        int stiffness,
                                        int filterSize,
                                        int offset,
                                        int depth,
                                        double sigma,
                                        int referenceIndx) {
        this.input = input;
        // assign other attributes
        this.zStackDirection = zStackDirection;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.offset = offset;
        this.depth = depth;
        this.sigma = sigma;
        this.referenceIndx = referenceIndx;
    }
    public ImagePlus process(){
        performProcessing();
        return merged;
    }

    private void performProcessing() {
        // split the channels and get the reference
        ImagePlus[] channels = ChannelSplitter.split(input);
        ImagePlus[] outputChannels = new ImagePlus[channels.length];
        ImagePlus referenceChannel = channels[referenceIndx];
        // process the reference channel first
        HandleSingleFile hsf = new HandleSingleFile(referenceChannel, zStackDirection, stiffness, filterSize, offset, depth, sigma);
        ImagePlus mainChannelsOutput = hsf.process();
        outputChannels[referenceIndx] = mainChannelsOutput;
        float[] envMaxzValues = hsf.getEnvMaxzValues();
        for (int i = 0; i < channels.length; i++) {
            if (i != referenceIndx) {
                ImagePlus outputChannel = processOtherChannel(channels[i],envMaxzValues,zStackDirection,stiffness,filterSize,offset,depth);
                outputChannels[i] = outputChannel;
            }
        }
        ImagePlus rawMerged = RGBStackMerge.mergeChannels(outputChannels,true);
        if (!(input instanceof CompositeImage)) {
            input = new CompositeImage(input, CompositeImage.COLOR);
        }
        LUT[] luts = ((CompositeImage) input).getLuts();

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
//                ImagePlus smpMipZmap = smpMipProjector.getZmap();
//            saveOutputOtherChannel(outputDir,
//                    originalChannel,
//                    stiffness,
//                    filterSize,
//                    offset,
//                    depth,
//                    projection,
//                    zMapProjection,
//                    projectedSMPMIPImage,
//                    smpMipZmap);
            } else {
                return projection;
            }
        }

//    private static void saveOutputOtherChannel(File outputDir,
//                                               File originalChannel,
//                                               int stiffness,
//                                               int filterSize,
//                                               int offset,
//                                               int depth,
//                                               ImagePlus projectedSMPImage,
//                                               ImagePlus smpZmap){
//        saveOutputOtherChannel(outputDir,
//                originalChannel,
//                stiffness,
//                filterSize,
//                offset,
//                depth,
//                projectedSMPImage,
//                smpZmap,
//                null,
//                null);
//    }
//
//    private static void saveOutputOtherChannel(File outputDir,
//                                               File originalChannel,
//                                               int stiffness,
//                                               int filterSize,
//                                               int offset,
//                                               int depth,
//                                               ImagePlus projectedSMPImage,
//                                               ImagePlus smpZmap,
//                                               ImagePlus projectedSMPMIPImage,
//                                               ImagePlus smpMipZmap){
//        String resultDir = outputDir.getAbsolutePath();
//        String fileName = originalChannel.getName();
//        if (projectedSMPMIPImage == null) {
//            // Save SMP projected image and zMap
//            SmpBasedMaxUtil.savePostProcessImagePlus(projectedSMPImage, OutputTypeName.SMP,resultDir,fileName, stiffness, filterSize, offset, depth,true);
//            SmpBasedMaxUtil.savePostProcessImagePlus(smpZmap, OutputTypeName.SMP_ZMAP,resultDir,fileName, stiffness, filterSize, offset, depth);
//        } else {
//            // Save the depth-adjusted output
//            SmpBasedMaxUtil.savePostProcessImagePlus(projectedSMPMIPImage, OutputTypeName.SMPbasedMIP,resultDir,fileName, stiffness, filterSize, offset, depth, true);
//            SmpBasedMaxUtil.savePostProcessImagePlus(smpMipZmap, OutputTypeName.SMPbasedMIP_ZMAP,resultDir,fileName, stiffness, filterSize, offset, depth);
//
//        }
    }

