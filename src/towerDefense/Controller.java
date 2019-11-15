package towerDefense;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import towerDefense.utilities.Sprite;

public class Controller {
    private Pane layer;
    //Use for default towers in shops
    private Sprite normalImage;
    private Sprite sniperImage;
    private Sprite machineImage;

    //Use for dragging faded version of towers
    private Sprite normalShadow;
    private Sprite sniperShadow;
    private Sprite machineShadow;

    private Sprite s;
    private boolean flag;
    private boolean spawn;

    //Default position for towers in shop
    private final double x = 21*46 - 8;  //use for all
    private final double yNorm =  46 - 23;
    private final double ySnipe = 2*46 + 5;
    private final double yMachine = 4*46 - 7;

    private GameField gameField;

    //Constructor
    public Controller(Pane layer, GameField gf) {
        this.layer = layer;
        flag = true;
        spawn = false;

        normalImage = new Sprite("norm", new Image(Config.NORMAL_TOWER_IMAGE), x, yNorm);
        sniperImage = new Sprite("snip", new Image(Config.SNIPER_TOWER_IMAGE), x, ySnipe);
        machineImage = new Sprite("mach", new Image(Config.MACHINE_TOWER_IMAGE), x, yMachine);

        normalImage.getImageView().setMouseTransparent(true);
        sniperImage.getImageView().setMouseTransparent(true);
        machineImage.getImageView().setMouseTransparent(true);

        layer.getChildren().addAll(normalImage.getImageView(), sniperImage.getImageView(), machineImage.getImageView());

        this.gameField = gf;
    }

    //Control method, used directly in the gameloop
    public void control() {

        //drag and release
        layer.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {

            AnimationTimer loop = new AnimationTimer() {
                @Override
                public void handle(long now) {

                    if(flag) {
                        normalShadow = new Sprite("Normal", new Image(Config.NORMAL_SHADOW_IMAGE), x + 3, yNorm + 9);
                        sniperShadow = new Sprite("Sniper", new Image(Config.SNIPER_SHADOW_IMAGE), x + 4, ySnipe + 5);
                        machineShadow = new Sprite("Machine", new Image(Config.MACHINE_SHADOW_IMAGE), x + 3, yMachine + 5);

                        layer.getChildren().addAll(normalShadow.getImageView(), sniperShadow.getImageView(), machineShadow.getImageView());
                        flag = false;
                    }

                    // determine which one to drag
                    normalShadow.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            s = normalShadow;
                            spawn = true;
                        }
                    });

                    sniperShadow.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            s = sniperShadow;
                            spawn = true;
                        }
                    });

                    machineShadow.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            s = machineShadow;
                            spawn = true;
                        }
                    });

                    if(spawn) {
                        s.getImageView().setOnMouseDragged(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                s.setPosition(event.getX() - 10, event.getY() - 10);
                            }
                        });

                        s.getImageView().setOnMouseReleased(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                layer.getChildren().remove(s.getImageView());
                                int inX = (int) e.getX();
                                int inY = (int) e.getY();
                                gameField.spawnTower(layer, s.getName(), 46.0 * (inX / 46), 46.0 * (inY / 46));
                                flag = true;
                                spawn = false;
                            }
                        });
                    }
                }
            };
            loop.start();
        });
    }
}