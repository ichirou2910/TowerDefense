package towerDefense;

 import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GameStage {
    private final int width;
    private final int height;
    private final List<towerDefense.EntityClass> entities;
    private final int wave;
    private final int waveNum;
    private int mapIndex[][] = new int[Config.VERTICAL_TILES][Config.HORIZONTAL_TILES];

    public GameStage(int width, int height, List<towerDefense.EntityClass> entities, int wave, int waveNum) {
        this.width = width;
        this.height = height;
        this.entities = List.copyOf(entities);
        this.wave = wave;
        this.waveNum = waveNum;
        loadMap();
    }
    //Getters
    //#region
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

    public final int[][] getMapIndex() {return mapIndex;}
    //#endregion

    private void loadMap() {
        try (FileInputStream str = new FileInputStream("res/stages/Map.txt")) {
            final Scanner sc = new Scanner(str);
            int row = Config.VERTICAL_TILES;
            int col = Config.HORIZONTAL_TILES;

            try {
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++)
                        mapIndex[i][j] = sc.nextInt();
                }
            }
            finally {
                sc.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
