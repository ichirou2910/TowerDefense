package towerDefense;

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import towerDefense.entity.enemies.*;

public class GameField {
    private final double width;
    private final double height;
    private long tick;
    private final List<EntityClass> entities = new ArrayList<>(Config.TILES_TOTAL);

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

    public NormalEnemy spawnEnemies(Pane layer, Image image)
    {
        NormalEnemy e1 = new NormalEnemy(layer, image, 0, 5*46 - 23, 46, 90, 46, 46, 100, 100, 1, 100);
        entities.add(e1);
        System.out.println("Enemy created");
        return e1;
    }

    public void update()
    {
        entities.forEach(e -> e.move());
        entities.forEach(e -> e.update());
    }

    // only needed for enemies removal
    // TODO: 
    public void removeSprites()
    {
        Iterator<EntityClass> iter = entities.iterator();
        while( iter.hasNext()) {
            EntityClass en = iter.next();
            en.removeFromLayer();
            iter.remove();
        }
    }

}
