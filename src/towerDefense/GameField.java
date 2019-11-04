package towerDefense;

import java.util.*;

public class GameField {
    private final double width;
    private final double height;
    private long tick;
    private final List<towerDefense.GameEntity> entities = new ArrayList<>(Config.TILES_TOTAL);

    public GameField(GameStage gameStage) {
        this.width = gameStage.getWidth();
        this.height = gameStage.getWidth();
        this.tick = 0;
        entities.addAll(gameStage.getEntities());
    }

    public final double getWidth() {
        return width;
    }

    public final double getHeight() {
        return height;
    }

    public final long getTick() {
        return tick;
    }
}
