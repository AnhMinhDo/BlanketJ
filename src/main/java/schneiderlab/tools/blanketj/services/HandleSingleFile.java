package schneiderlab.tools.blanketj.services;

import ij.ImagePlus;
import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.corecomputation.MaxIntensityProjection;
import schneiderlab.tools.blanketj.corecomputation.SMP_MIP_Projection;
import schneiderlab.tools.blanketj.corecomputation.SMProjection;
import schneiderlab.tools.blanketj.helpersandutils.SmpBasedMaxUtil;

public class HandleSingleFile {

    private ImagePlus inputImage;
    private final int stiffness;
    private final int filterSize;
    private final ZStackDirection zStackDirection;
    private final int offset;
    private final int depth;
    private final double sigma;
    private float[] envMaxzValues;

    private ImagePlus projectedImage;
    private ImagePlus zMap;
    private ImagePlus projectedSMPImage;
    private ImagePlus smpZmap;
    private ImagePlus projectedSMPMIPImage;
    private ImagePlus smpMipZmap;

    private boolean hasProcessed = false;

    public HandleSingleFile(ImagePlus inputImage,
                            ZStackDirection zStackDirection,
                            int stiffness,
                            int filterSize,
                            int offset,
                            int depth,
                            double sigma) {
        this.inputImage = inputImage;
        this.zStackDirection = zStackDirection;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.offset = offset;
        this.depth = depth;
        this.sigma = sigma;
    }

    public ImagePlus process(){
        performProcessing();
//        saveOutputToFile();
        return depth==0 ? this.projectedSMPImage : projectedSMPMIPImage;
    }

    public ImagePlus getMIPOutput() {
        if(this.projectedImage == null){
            throw new IllegalStateException("Image not processed yet.");
        } else{
            return projectedImage;
        }
    }

    public ImagePlus getMIPzMapOutput(){
        if(this.zMap == null){
            throw new IllegalStateException("Image not processed yet.");
        } else{
            return zMap;
        }
    }

    public ImagePlus getSMPOutput(){
        if(this.projectedSMPImage == null){
            throw new IllegalStateException("Image not processed yet.");
        } else{
            return depth==0 ? this.projectedSMPImage : projectedSMPMIPImage;
        }
    }

    public ImagePlus getSMPzMapOutput(){
        if(this.smpZmap == null){
            throw new IllegalStateException("Image not processed yet.");
        } else{
            return depth==0 ? this.smpZmap : this.smpMipZmap;
        }
    }

//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }

    public float[] getEnvMaxzValues() { return hasProcessed ? this.envMaxzValues : null;}

    private void performProcessing(){
        // create imagePlus object from filePath
        inputImage = SmpBasedMaxUtil.preProcessInputImage(this.inputImage);
        // MIP of original Image
        MaxIntensityProjection mipOriginalStack = new MaxIntensityProjection(inputImage);
        this.projectedImage = mipOriginalStack.doProjection();
        this.zMap = mipOriginalStack.getZmap();
        // GaussianBlur and MIP
        if(this.sigma!=0.0){
            ImagePlus blurStack = SmpBasedMaxUtil.gaussianBlurImageStack(inputImage,this.sigma);
            MaxIntensityProjection mipBlurStack = new MaxIntensityProjection(blurStack);
            mipBlurStack.doProjection();
            this.zMap = mipBlurStack.getZmap();
        }
        // ZProjecting SMP
        SMProjection smProjector = new SMProjection(inputImage, zMap, stiffness, filterSize, zStackDirection, offset);
        this.projectedSMPImage = smProjector.doSMProjection();
        this.envMaxzValues = smProjector.getEnvMax();
        hasProcessed = true;
        this.smpZmap = smProjector.getSMPZmap();
        // SMP-MIP if depth !=0
        SMP_MIP_Projection smpMipProjector = new SMP_MIP_Projection(inputImage, smpZmap, depth, zStackDirection);
        this.projectedSMPMIPImage = smpMipProjector.doProjection();
        this.smpMipZmap = smpMipProjector.getZmap();
    }

//    private void saveOutputToFile(){
//        try {
//            // prepare the directory for output
//            String resultDir = SmpBasedMaxUtil.createResultDir(filePath,
//                    zStackDirection,
//                    stiffness,
//                    filterSize,
//                    offset,
//                    depth);
//            String fileName = SmpBasedMaxUtil.extractFilename(filePath);
//            // Save MIP projected Image and zMap
//            FileSaver projectedImageTiff = new FileSaver(projectedImage);
//            FileSaver zMapTiff = new FileSaver(zMap);
//            projectedImageTiff.saveAsTiff(resultDir + File.separator +
//                    fileName + "_MIP" + ".tif");
//            zMapTiff.saveAsTiff(resultDir + File.separator +
//                    fileName + "_MIP_zmap" + ".tif");
//            // Save SMP projected image and zMap
//            SmpBasedMaxUtil.savePostProcessImagePlus(this.projectedSMPImage, OutputTypeName.SMP,resultDir,fileName, stiffness, filterSize, offset, depth,true);
//            SmpBasedMaxUtil.savePostProcessImagePlus(this.smpZmap, OutputTypeName.SMP_ZMAP,resultDir,fileName, stiffness, filterSize, offset, depth);
//            // Save SMP depth-adjusted image and zMap
//            if (depth != 0) {
//                SmpBasedMaxUtil.savePostProcessImagePlus(this.projectedSMPMIPImage, OutputTypeName.SMPbasedMIP,resultDir,fileName, stiffness, filterSize, offset, depth,true);
//                SmpBasedMaxUtil.savePostProcessImagePlus(this.smpMipZmap, OutputTypeName.SMPbasedMIP_ZMAP,resultDir,fileName, stiffness, filterSize, offset, depth);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
