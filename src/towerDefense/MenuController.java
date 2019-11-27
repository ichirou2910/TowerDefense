package towerDefense;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.WindowEvent;
import towerDefense.ui.GameLog;
import towerDefense.utilities.Sprite;

import static towerDefense.Config.TILE_SIZE;

public class MenuController {

    private Pane layer;                 // reference to master Layer
    private GameField gameField;        // reference to GameField
//    private GameLog log;                // reference to GameLog
    private Player player;              // reference to Player

    // Use for default towers in shops
    private Sprite normalImage;
    private Sprite sniperImage;
    private Sprite machineImage;

    // Use for dragging faded version of towers
    private Sprite normalShadow;
    private Sprite sniperShadow;
    private Sprite machineShadow;

    // Use for drag & drop operation
    private Sprite s;       // pointer to the to be dragged sprite
    private boolean onDragged;

    // Array for mapIndex
    private final int[][] mapIndex;

    // Fixed position for towers in shop
    private final double x = 21 * TILE_SIZE - 8;
    private final double yNorm =  TILE_SIZE / 2;
    private final double ySnipe = 2*TILE_SIZE + 5;
    private final double yMachine = 4*TILE_SIZE - 7;

    // Position of sprite before dragged so we can move it back, no need to recreate it
    private double initX = 0;
    private double initY = 0;

    public MenuController(Pane layer, GameField gf, GameStage gs /*, GameLog log */, Player player) {
        
        this.layer = layer;
//        this.log = log;
        this.player = player;

        onDragged = false;
        s = null;

        normalImage = new Sprite(new Image(Config.NORMAL_TOWER_IMAGE), x, yNorm, false);
        sniperImage = new Sprite(new Image(Config.SNIPER_TOWER_IMAGE), x, ySnipe, false);
        machineImage = new Sprite(new Image(Config.MACHINE_TOWER_IMAGE), x, yMachine, false);

        normalImage.getImageView().setMouseTransparent(true);
        sniperImage.getImageView().setMouseTransparent(true);
        machineImage.getImageView().setMouseTransparent(true);

        normalShadow = new Sprite("Normal", new Image(Config.NORMAL_SHADOW_IMAGE), x, yNorm, false);
        sniperShadow = new Sprite("Sniper", new Image(Config.SNIPER_SHADOW_IMAGE), x, ySnipe, false);
        machineShadow = new Sprite("Machine", new Image(Config.MACHINE_SHADOW_IMAGE), x, yMachine, false);

        layer.getChildren().addAll(normalImage.getImageView(), sniperImage.getImageView(), machineImage.getImageView(), 
                                    normalShadow.getImageView(), sniperShadow.getImageView(), machineShadow.getImageView());

        this.gameField = gf;
        this.mapIndex = gs.getMapIndex();
    }

    // Initialize the mouse listener
    public void init() {

        // Summon Tower
        // Initialize a listener to the 3 sprites
        normalShadow.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s = normalShadow;
                onDragged = true;
                initX = x;
                initY = yNorm;
            }
        });

        sniperShadow.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s = sniperShadow;
                onDragged = true;
                initX = x;
                initY = ySnipe;
            }
        });

        machineShadow.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s = machineShadow;
                onDragged = true;
                initX = x;
                initY = yMachine;
            }
        });

        // Add a Drag Listener Filter
        layer.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {

            if(onDragged) {
                s.getImageView().setOnMouseDragged(event -> {
                    s.setPosition(event.getX() - 20, event.getY() - 20);
                });

                s.getImageView().setOnMouseReleased(event -> {

                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    s.setPosition(initX, initY);

                    // check if it's possible to spawn tower there
                    if (x <= 920 && x >= 0 && y >= 0 && y <= 920)
                        if(mapIndex[y/TILE_SIZE][x/TILE_SIZE] == 1) {
                            player.buyTower(layer, s.getName(), TILE_SIZE * (x / TILE_SIZE) + TILE_SIZE / 2, TILE_SIZE * (y / TILE_SIZE) + TILE_SIZE / 2, gameField.getTowers());
                            mapIndex[y/TILE_SIZE][x/TILE_SIZE] = 2;   // set that location as "used"
                            AudioClip sound = new AudioClip("file:res/Sound/Build.mp3");
                            sound.play();
                        }
                    onDragged = false;
                    s = null;
                });
            }
        });

    }


}