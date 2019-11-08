package towerDefense;

// import java.io.IOException;
// import java.io.InputStream;
import java.util.*;

public class GameStage {
    private final int width;
    private final int height;
    private final List<towerDefense.EntityClass> entities;
    private final int wave;
    private final int waveNum;

    public GameStage(int width, int height, List<towerDefense.EntityClass> entities, int wave, int waveNum) {
        this.width = width;
        this.height = height;
        this.entities = List.copyOf(entities);
        this.wave = wave;
        this.waveNum = waveNum;
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final List<towerDefense.EntityClass> getEntities() {
        return entities;
    }

    public final int getWave() { return this.wave; }

    public final int getWaveNum() { return this.waveNum; }
}
