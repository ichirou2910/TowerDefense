package towerDefense;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import towerDefense.entity.towers.MachineGunTower;
import towerDefense.entity.towers.NormalTower;
import towerDefense.entity.towers.SniperTower;
import towerDefense.entity.towers.TowerClass;

public class Controller {
    private Pane layer;
    //Use for default towers in shops
    private TowerClass normalTower;
    private TowerClass sniperTower;
    private TowerClass machineGun;

    //Use for dragging faded version of towers
    private TowerClass normalShadow;
    private TowerClass sniperShadow;
    private TowerClass machineShadow;

    //Default position for towers in shop
    private final double x = 21*46 - 8;  //use for all
    private final double yNorm =  46 - 23;
    private final double ySnipe = 2*46 + 5;
    private final double yMachine = 4*46 - 7;

    //Constructor
    public Controller(Pane layer) {
        this.layer = layer;
        normalTower = new NormalTower(layer, new Image(Config.NORMAL_TOWER_IMAGE), x, yNorm, 0);
        sniperTower = new SniperTower(layer, new Image(Config.SNIPER_TOWER_IMAGE), x, ySnipe, 0);
        machineGun = new MachineGunTower(layer, new Image(Config.MACHINE_TOWER_IMAGE), x, yMachine, 0);
    }

    //Control method, used directly in the gameloop
    public void control() {

        //Still in test phase, only available to sniperTower
        sniperShadow = new SniperTower(layer, new Image(Config.SNIPER_SHADOW_IMAGE), x + 4, ySnipe + 5, 0);
        ImageView sT = sniperShadow.getImageView();

        //drag and release sniper tower
        layer.addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            sT.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    sT.setX(event.getX() - 10);
                    sT.setY(event.getY() - 10);
                }
            });

            sT.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    sT.setX(x);
                    sT.setY(ySnipe);
                    int inX = (int) e.getX();
                    int inY = (int) e.getY();
                    TowerClass t = new SniperTower(layer, new Image(Config.SNIPER_TOWER_IMAGE), 46 * (inX/46), 46 * (inY/46), 136);
                }
            });
        });
    }
}
