package schneiderlab.tools.blanketj.services;

import ij.ImagePlus;
import schneiderlab.tools.blanketj.OutputTypeName;
import schneiderlab.tools.blanketj.ZStackDirection;

import java.nio.file.Path;
import java.util.HashMap;

public class SavingOutput {
    private HashMap<OutputTypeName, ImagePlus> inputHashMap;
    private Path originalFilePath;
    private ZStackDirection zStackDirection;
    private int stiffness;
    private int filterSize;
    private int offset;
    private int depth;


    public SavingOutput(HashMap<OutputTypeName, ImagePlus> inputHashMap, Path originalFilePath, ZStackDirection zStackDirection, int stiffness, int filterSize, int offset, int depth) {
        this.inputHashMap = inputHashMap;
        this.originalFilePath = originalFilePath;
        this.zStackDirection = zStackDirection;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.offset = offset;
        this.depth = depth;
    }

    public void saving(){

    }
}
