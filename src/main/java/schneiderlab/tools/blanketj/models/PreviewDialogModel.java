package schneiderlab.tools.blanketj.models;

import schneiderlab.tools.blanketj.*;

import java.nio.file.Path;

public class PreviewDialogModel implements SMPToolModel {
    private ThemeMode themeMode;
    private ZStackDirection zStackDirection;
    private int stiffness;
    private int filterSize;
    private int offset;
    private int depth;
    private Path filePath;

    public PreviewDialogModel(ZStackDirection zStackDirection, int stiffness, int filterSize, int offset, int depth, Path filePath) {
        this.zStackDirection = zStackDirection;
        this.stiffness = stiffness;
        this.filterSize = filterSize;
        this.offset = offset;
        this.depth = depth;
        this.filePath = filePath;
    }

    public ThemeMode getThemeMode() {
        return themeMode;
    }

    public void setThemeMode(ThemeMode themeMode) {
        this.themeMode = themeMode;
    }

    public ZStackDirection getzStackDirection() {
        return zStackDirection;
    }

    public void setzStackDirection(ZStackDirection zStackDirection) {
        this.zStackDirection = zStackDirection;
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

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
}
