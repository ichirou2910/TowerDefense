package towerDefense;

// import java.io.IOException;
// import java.io.InputStream;
import java.util.*;

public class GameStage {
    private final int width;
    private final int height;
    private final List<towerDefense.EntityClass> entities;

    public GameStage(int width, int height, List<towerDefense.EntityClass> entities) {
        this.width = width;
        this.height = height;
        this.entities = List.copyOf(entities);
    }

    // public static GameStage load(String name) {
    //     try (final InputStream stream = GameStage.class.getResourceAsStream(name)) {
    //         if (stream == null) throw new IOException("Resource not found! Resource name: " + name);
    //         final Scanner scanner = new Scanner(stream);
    //         try {
    //             final int width = scanner.nextInt();
    //             final int height = scanner.nextInt();
    //             final int totalTiles = scanner.nextInt();
    //             final List<towerDefense.entity.GameEntity> entities = new ArrayList<>(width * height + totalTiles);
    //             // for (int y = 0; y < height; y++) {
    //             //     for (int x = 0; x < width; x++) {
    //             //         final int value = scanner.nextInt();
    //             //         if (value == 0) {
    //             //             entities.add(new Road(0, x, y));
    //             //         } else if (value == 1) {
    //             //             entities.add(new Mountain(0, x, y));
    //             //         } else {
    //             //             throw new InputMismatchException("Unexpected value! Input value: " + value);
    //             //         }
    //             //     }
    //             // }

    //         } catch (NoSuchElementException e) {
    //             throw new IOException("Resource invalid! Resource name: " + name, e);
    //         }
    //         finally {
    //             scanner.close();
    //         }

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final List<towerDefense.EntityClass> getEntities() {
        return entities;
    }
}
