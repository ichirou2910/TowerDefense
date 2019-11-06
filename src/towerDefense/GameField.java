package towerDefense;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.entity.*;
import towerDefense.entity.enemies.NormalEnemy;

public class GameField {
    private final double width;
    private final double height;
    private long tick;
    private final List<GameEntity> entities = new ArrayList<>(Config.TILES_TOTAL);

    public GameField(GameStage gameStage) {
        this.width = gameStage.getWidth();
        this.height = gameStage.getWidth();
        this.tick = 0;
        entities.addAll(gameStage.getEntities());
    }

    // Getters
    //#region
    public final double getWidth() {
        return width;
    }

    public final double getHeight() {
        return height;
    }

    public final long getTick() {
        return tick;
    }
    //#endregion

    public void spawnEnemies(Pane layer)
    {
        Image n_Enemy = new Image("/res/images/enemies/Normal.png");
        NormalEnemy e1 = new NormalEnemy(layer, n_Enemy, 0, 5*46 - 23, 0, -1, -1, 90, 46, 46, 100, 100, 1, 100);
        entities.add(e1);
    }
}
