package towerDefense;

public class Config {
    public static final String GAME_NAME = "Tower Defense in space";

    public static final int TILE_SIZE = 46;

    public static final int HORIZONTAL_TILES = 20;

    public static final int VERTICAL_TILES = 20;

    public static final int TILES_TOTAL = (HORIZONTAL_TILES * VERTICAL_TILES);

    public static final int SCREEN_WIDTH = HORIZONTAL_TILES * TILE_SIZE;

    public static final int SCREEN_HEIGHT = VERTICAL_TILES * TILE_SIZE;

    //Enemies
    //Normal Enemies
    public final int NORMAL_HEALTH = 100;
    public final int NORMAL_ARMOR = 75;
    public final double NORMAL_SPEED = 1;
    public final int NORMAL_REWARD = 25;
    //Smaller Enemies
    public final int SMALLER_HEALTH = 75;
    public final int SMALLER_ARMOR = 50;
    public final double SMALLER_SPEED = 2;
    public final int SMALLER_REWARD = 15;
    //Tanker Enemies
    public final int TANKER_HEALTH = 150;
    public final int TANKER_ARMOR = 100;
    public final double TANKER_SPEED = 0.5;
    public final int TANKER_REWARD = 40;
    //Boss
    public final int BOSS_HEALTH = 200;
    public final int BOSS_ARMOR = 200;
    public final double BOSS_SPEED = 1;
    public final int BOSS_REWARD = 100;

}
