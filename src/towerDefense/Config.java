package towerDefense;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Timer;

public class Config {

    // Interface
    public static final String GAME_NAME = "Tower Defense in space";

    // TODO: change object position to TILE_SIZE based position
    public static int TILE_SIZE = 46;
    public static final int HORIZONTAL_TILES = 20;
    public static final int VERTICAL_TILES = 20;

    public static final int SCREEN_WIDTH = 1050;
    public static final int SCREEN_HEIGHT = VERTICAL_TILES * TILE_SIZE;

    public final static Font UI_FONT = Font.loadFont("file:res/fonts/VT323-Regular.ttf", 30);
    public final static Font LOG_FONT = Font.loadFont("file:res/fonts/VT323-Regular.ttf", 25);
    public final static Color LOG_COLOR = Color.WHITE;
    public final static int LOG_SIZE = 6;

    //Spawner
    public final static double SPAWN_POS_X = 5 * TILE_SIZE; //5 * 46 - 23
    public final static double SPAWN_POS_Y = TILE_SIZE;
    public final static double SPAWN_ROTATION = 90;
    public final static String NORMAL_IMAGE = "file:res/images/enemies/Normal.png";
    public final static String SMALLER_IMAGE = "file:res/images/enemies/Smaller.png";
    public final static String TANKER_IMAGE = "file:res/images/enemies/Tanker.png";
    public final static String BOSS_IMAGE = "file:res/images/enemies/Boss.png";

    //Towers
    public final static String NORMAL_TOWER_IMAGE = "file:res/images/towers/Normal1.png";
    public final static String NORMAL_TOWER2_IMAGE = "file:res/images/towers/Normal2.png";
    public final static String NORMAL_TOWER3_IMAGE = "file:res/images/towers/Normal3.png";

    public final static String SNIPER_TOWER_IMAGE = "file:res/images/towers/Sniper1.png";
    public final static String SNIPER_TOWER2_IMAGE = "file:res/images/towers/Sniper2.png";
    public final static String SNIPER_TOWER3_IMAGE = "file:res/images/towers/Sniper3.png";

    public final static String MACHINE_TOWER_IMAGE = "file:res/images/towers/MachineGun1.png";
    public final static String MACHINE_TOWER2_IMAGE = "file:res/images/towers/MachineGun2.png";
    public final static String MACHINE_TOWER3_IMAGE = "file:res/images/towers/MachineGun3.png";

    public final static String NORMAL_SHADOW_IMAGE = "file:res/images/towers/NormalFaded.png";
    public final static String SNIPER_SHADOW_IMAGE = "file:res/images/towers/SniperFaded.png";
    public final static String MACHINE_SHADOW_IMAGE = "file:res/images/towers/MachineGunFaded.png";

    public final static String MENU_BLANK = "file:res/images/TowerUI/MenuBlank.png";
    public final static String UPGRADE_IMAGE = "file:res/images/TowerUI/Upgrade.png";
    public final static String SELL_IMAGE = "file:res/images/TowerUI/Sell.png";

    public final static int NORMAL_TOWER_DAMAGE = 9;
    public final static int SNIPER_TOWER_DAMAGE = 40;
    public final static int MACHINE_TOWER_DAMAGE = 4;

    public final static double NORMAL_TOWER_RANGE = TILE_SIZE * 2;
    public final static double SNIPER_TOWER_RANGE = TILE_SIZE * 3;
    public final static double MACHINE_TOWER_RANGE = TILE_SIZE * 2;

    public final static int NORMAL_TOWER_PRICE = 30;
    public final static int SNIPER_TOWER_PRICE = 65;
    public final static int MACHINE_TOWER_PRICE = 55;

    public final static int TOWER_MAX_LEVEL = 3;

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
    public final static int WAVE = 3;

    public final static double NORMAL_HEALTH = 100;
    public final static double NORMAL_ARMOR = 0.4;
    public final static double NORMAL_SPEED = 0.5;
    public final static int NORMAL_REWARD = 15;

    public final static double SMALLER_HEALTH = 75;
    public final static double SMALLER_ARMOR = 0.2;
    public final static double SMALLER_SPEED = 1;
    public final static int SMALLER_REWARD = 20;

    public final static double TANKER_HEALTH = 175;
    public final static double TANKER_ARMOR = 0.6;
    public final static double TANKER_SPEED = 0.5;
    public final static int TANKER_REWARD = 40;

    public final static double BOSS_HEALTH = 400;
    public final static double BOSS_ARMOR = 0.7;
    public final static double BOSS_SPEED = 0.25;
    public final static int BOSS_REWARD = 100;

    //Base
    public final static String BASE_IMAGE = "file:res/images/Base/Base.png";
    public final static String HIGH = "file:res/images/Base/High.png";
    public final static String MED_HIGH = "file:res/images/Base/MedHigh.png";
    public final static String MED = "file:res/images/Base/Med.png";
    public final static String LOW_MED = "file:res/images/Base/LowMed.png";
    public final static String LOW = "file:res/images/Base/Low.png";
}
