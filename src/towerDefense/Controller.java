package towerDefense;

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

    //Default position for towers in shop
    private final double x = 21*46 - 8;  //use for all
    private final double yNorm =  46 - 23;
    private final double ySnipe = 2*46 + 5;
    private final double yMachine = 4*46 - 7;

    // position of image before being dragged
    private double initX, initY;

    private GameField gameField;

    //Constructor
    public Controller(Pane layer, GameField gf) {
        this.layer = layer;
        flag = true;

        normalImage = new Sprite("norm", new Image(Config.NORMAL_TOWER_IMAGE), x, yNorm);
        sniperImage = new Sprite("snip", new Image(Config.SNIPER_TOWER_IMAGE), x, ySnipe);
        machineImage = new Sprite("mach", new Image(Config.MACHINE_TOWER_IMAGE), x, yMachine);

        normalImage.getImageView().setMouseTransparent(true);
        sniperImage.getImageView().setMouseTransparent(true);
        machineImage.getImageView().setMouseTransparent(true);

        layer.getChildren().addAll(normalImage.getImageView(), sniperImage.getImageView(), machineImage.getImageView());

        this.gameField = gf;
        initX = 0;
        initY = 0;
    }

    //Control method, used directly in the gameloop
    public void control() {
        if(flag) {
            normalShadow = new Sprite("Normal", new Image(Config.NORMAL_SHADOW_IMAGE), x + 3, yNorm + 9);
            sniperShadow = new Sprite("Sniper", new Image(Config.SNIPER_SHADOW_IMAGE), x + 4, ySnipe + 5);
            machineShadow = new Sprite("Machine", new Image(Config.MACHINE_SHADOW_IMAGE), x + 3, yMachine + 5);

            layer.getChildren().addAll(normalShadow.getImageView(), sniperShadow.getImageView(), machineShadow.getImageView());
            flag = false;
        }

        //drag and release sniper tower
        layer.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {

            // determine which one to drag
            if (e.getX() >= x && e.getX() <= x + 40)
            {
                if (e.getY() >= yNorm && e.getY() <= yNorm + 40)
                {
                    s = normalShadow;
                }
                else if (e.getY() >= ySnipe && e.getY() <= ySnipe + 40)
                {
                    s = sniperShadow;
                }
                else if (e.getY() >= yMachine && e.getY() <= yMachine + 40)
                {
                    s = machineShadow;
                }
            }

            s.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    initX = s.getPosX();
                    initY = s.getPosY();
                }
            });

            s.getImageView().setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    s.setPosition(event.getX() - 10, event.getY() - 10);
                }
            });

            s.getImageView().setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    s.setPosition(initX, initY);
                    int inX = (int) e.getX();
                    int inY = (int) e.getY();
                    gameField.spawnTower(layer, s.getName(), 46.0 * (inX/46), 46.0 * (inY/46));
                    s = null;
                    flag = true;
                }
            });
        });
    }
}