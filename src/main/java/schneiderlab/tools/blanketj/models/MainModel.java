package schneiderlab.tools.blanketj.models;

import schneiderlab.tools.blanketj.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class MainModel implements SMPToolModel {
    private ProcessingMode processingMode;
    private ThemeMode themeMode;
    private ZStackDirection zStackDirection;
    private int stiffness;
    private int filterSize;
    private int offset;
    private int depth;
    private Path directoryPath;
    private Path filePath;
    private HashSet<OutputTypeName> output;

    public MainModel(ProcessingMode processingMode,
                     ThemeMode themeMode,
                     ZStackDirection zStackDirection,
                     int stiffness,
                     int filterSize,
                     int offset,
                     int depth,
                     Path directoryPath,
                     Path filePath,
                     HashSet<OutputTypeName> output) {
        this.processingMode = processingMode;
        this.themeMode = themeMode;
        this.zStackDirection = zStackDirection;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.offset = offset;
        this.depth = depth;
        this.directoryPath = directoryPath;
        this.filePath = filePath;
        this.output = output;
    }

    public ProcessingMode getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(ProcessingMode processingMode) {
        this.processingMode = processingMode;
    }

    public ThemeMode getThemeMode() {
        return themeMode;
    }

    public void setThemeMode(ThemeMode themeMode) {
        this.themeMode = themeMode;
    }

    public int getStiffness() {
        return stiffness;
    }

    public void setStiffness(int stiffness) {
        this.stiffness = stiffness;
    }

    public int getFilterSize() {
        return filterSize;
    }

    public void setFilterSize(int filterSize) {
        this.filterSize = filterSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(Path directoryPath) {
        this.directoryPath = directoryPath;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public HashSet<OutputTypeName> getOutput() {
        return output;
    }

    public void setOutput(HashSet<OutputTypeName> output) {
        this.output = output;
    }

    public boolean isValidPath (Path path){
        return path!= null && Files.exists(path);
    }
}
