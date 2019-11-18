package towerDefense;

import javafx.scene.text.Font;

public class Config {
    public static final String GAME_NAME = "Tower Defense in space";

    public static final int TILE_SIZE = 46;

    public static final int HORIZONTAL_TILES = 20;

    public static final int VERTICAL_TILES = 20;

    public static final int TILES_TOTAL = (HORIZONTAL_TILES * VERTICAL_TILES);

    public static final int SCREEN_WIDTH = 1050;

    public static final int SCREEN_HEIGHT = VERTICAL_TILES * TILE_SIZE;

    public final static Font UI_FONT = Font.loadFont("file:res/fonts/VT323-Regular.ttf", 30);

    //Spawner
    public final static double SPAWN_POS_X = 207; //5 * 46 - 23
    public final static double SPAWN_POS_Y = 46;
    public final static double SPAWN_ROTATION = 90;
    public final static String NORMAL_IMAGE = "file:res/images/enemies/Normal.png";
    public final static String SMALLER_IMAGE = "file:res/images/enemies/Smaller.png";
    public final static String TANKER_IMAGE = "file:res/images/enemies/Tanker.png";
    public final static String BOSS_IMAGE = "file:res/images/enemies/Boss.png";

    //Towers
    public final static String NORMAL_TOWER_IMAGE = "file:res/images/towers/Normal.png";
    public final static String SNIPER_TOWER_IMAGE = "file:res/images/towers/Sniper.png";
    public final static String MACHINE_TOWER_IMAGE = "file:res/images/towers/MachineGun.png";
    public final static String NORMAL_SHADOW_IMAGE = "file:res/images/towers/NormalFaded.png";
    public final static String SNIPER_SHADOW_IMAGE = "file:res/images/towers/SniperFaded.png";
    public final static String MACHINE_SHADOW_IMAGE = "file:res/images/towers/MachineGunFaded.png";
    public final static int NORMAL_TOWER_DAMAGE = 10;
    public final static int SNIPER_TOWER_DAMAGE = 65;
    public final static int MACHINE_TOWER_DAMAGE = 7;
    public final static double NORMAL_TOWER_RANGE = 136;
    public final static double SNIPER_TOWER_RANGE = 182;
    public final static double MACHINE_TOWER_RANGE = 136;
    public final static int NORMAL_TOWER_PRICE = 25;
    public final static int SNIPER_TOWER_PRICE = 75;
    public final static int MACHINE_TOWER_PRICE = 60;
    //Bullets
    public final static String NORMAL_BULLET_IMAGE = "file:res/images/bullets/Normal.png";
    public final static String SNIPER_BULLET_IMAGE = "file:res/images/bullets/Sniper.png";
    public final static String MACHINE_BULLET_IMAGE = "file:res/images/bullets/MachineGun.png";
    public final static String IMPACT_BULLET_IMAGE = "file:res/images/bullets/BulletImpact.png";

    //Explosion
    public final static String EXPLOSION1 = "file:res/images/explosions/Exp1.png";
    public final static String EXPLOSION2 = "file:res/images/explosions/Exp2.png";
    public final static String EXPLOSION3 = "file:res/images/explosions/Exp3.png";

    //Enemies
    //Number of waves
    public final static int WAVE = 3;

    //Normal Enemies
    public final static double NORMAL_HEALTH = 100;
    public final static double NORMAL_ARMOR = 0.5;
    public final static double NORMAL_SPEED = 0.5;
    public final static int NORMAL_REWARD = 25;
    //Smaller Enemies
    public final static double SMALLER_HEALTH = 75;
    public final static double SMALLER_ARMOR = 0.4;
    public final static double SMALLER_SPEED = 1;
    public final static int SMALLER_REWARD = 15;
    //Tanker Enemies
    public final static double TANKER_HEALTH = 150;
    public final static double TANKER_ARMOR = 0.65;
    public final static double TANKER_SPEED = 0.5;
    public final static int TANKER_REWARD = 40;
    //Boss
    public final static double BOSS_HEALTH = 300;
    public final static double BOSS_ARMOR = 0.75;
    public final static double BOSS_SPEED = 0.5;
    public final static int BOSS_REWARD = 100;

}
