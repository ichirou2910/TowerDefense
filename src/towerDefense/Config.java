package towerDefense;

public class Config {
    public static final String GAME_NAME = "Tower Defense in space";

    public static final int TILE_SIZE = 46;

    public static final int HORIZONTAL_TILES = 20;

    public static final int VERTICAL_TILES = 20;

    public static final int TILES_TOTAL = (HORIZONTAL_TILES * VERTICAL_TILES);

    public static final int SCREEN_WIDTH = HORIZONTAL_TILES * TILE_SIZE;

    public static final int SCREEN_HEIGHT = VERTICAL_TILES * TILE_SIZE;

    //Spawner
    public final static double SPAWN_POS_X = 207; //5 * 46 - 23
    public final static double SPAWN_POS_Y = 46;
    public final static double SPAWN_ROTATION = 90;
    public final static String NORMAL_IMAGE = "file:src/res/images/enemies/Normal.png";
    public final static String SMALLER_IMAGE = "file:src/res/images/enemies/Smaller.png";
    public final static String TANKER_IMAGE = "file:src/res/images/enemies/Tanker.png";
    public final static String BOSS_IMAGE = "file:src/res/images/enemies/Boss.png";

    //Towers
    public final static String NORMAL_TOWER_IMAGE = "file:src/res/images/towers/Normal.png";

    //Enemies
    //Number of waves
    public final static int WAVE = 3;
    //Enemies per wave
    public final static int WAVE_NUM = 20;
    //Normal Enemies
    public final static int NORMAL_HEALTH = 100;
    public final static int NORMAL_ARMOR = 75;
    public final static double NORMAL_SPEED = 1;
    public final static int NORMAL_REWARD = 25;
    //Smaller Enemies
    public final static int SMALLER_HEALTH = 75;
    public final static int SMALLER_ARMOR = 50;
    public final static double SMALLER_SPEED = 2;
    public final static int SMALLER_REWARD = 15;
    //Tanker Enemies
    public final static int TANKER_HEALTH = 150;
    public final static int TANKER_ARMOR = 100;
    public final static double TANKER_SPEED = 0.5;
    public final static int TANKER_REWARD = 40;
    //Boss
    public final static int BOSS_HEALTH = 200;
    public final static int BOSS_ARMOR = 200;
    public final static double BOSS_SPEED = 1;
    public final static int BOSS_REWARD = 100;

}
