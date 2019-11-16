package towerDefense;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import towerDefense.utilities.Sprite;

public class Controller {

    private Pane layer;                 // reference to master Layer
    private GameField gameField;        // reference to GameField

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
    private final double x = 21*46 - 8;
    private final double yNorm =  46 - 23;
    private final double ySnipe = 2*46 + 5;
    private final double yMachine = 4*46 - 7;

    // Position of sprite before dragged so we can move it back, no need to recreate it
    private double initX = 0;
    private double initY = 0;

    public Controller(Pane layer, GameField gf, GameStage gs) {
        
        this.layer = layer;

        onDragged = false;
        s = null;

        normalImage = new Sprite(new Image(Config.NORMAL_TOWER_IMAGE), x, yNorm);
        sniperImage = new Sprite(new Image(Config.SNIPER_TOWER_IMAGE), x, ySnipe);
        machineImage = new Sprite(new Image(Config.MACHINE_TOWER_IMAGE), x, yMachine);

        normalImage.getImageView().setMouseTransparent(true);
        sniperImage.getImageView().setMouseTransparent(true);
        machineImage.getImageView().setMouseTransparent(true);

        normalShadow = new Sprite("Normal", new Image(Config.NORMAL_SHADOW_IMAGE), x, yNorm);
        sniperShadow = new Sprite("Sniper", new Image(Config.SNIPER_SHADOW_IMAGE), x, ySnipe);
        machineShadow = new Sprite("Machine", new Image(Config.MACHINE_SHADOW_IMAGE), x, yMachine);

        layer.getChildren().addAll(normalImage.getImageView(), sniperImage.getImageView(), machineImage.getImageView(), 
                                    normalShadow.getImageView(), sniperShadow.getImageView(), machineShadow.getImageView());

        this.gameField = gf;
        this.mapIndex = gs.getMapIndex();
    }

    // Initialize the mouse listener
    public void init() {

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

        // Drag Listener Filter
        layer.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {

            if(onDragged) {
                s.getImageView().setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        s.setPosition(event.getX() - 20, event.getY() - 20);
                    }
                });

                s.getImageView().setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        s.setPosition(initX, initY);

                        // check if it's possible to spawn tower there
                        if(mapIndex[y/46][x/46] == 1) {
                            gameField.spawnTower(layer, s.getName(), 46.0 * (x / 46), 46.0 * (y / 46));
                            mapIndex[y/46][x/46] = 0;   // set that location as "used"
                        }                                
                        onDragged = false;
                        s = null;
                    }
                });
            }
        });
    }

    // TODO: tower upgrade function
}