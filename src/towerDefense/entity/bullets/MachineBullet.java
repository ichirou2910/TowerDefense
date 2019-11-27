package towerDefense.entity.bullets;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import towerDefense.Config;

public class MachineBullet extends BulletClass {
    private final double speed = 100;
    private final int rateOfFire = 1;
    AudioClip sound;

    public MachineBullet(Pane layer, Image image, double posX, double posY, double rotation, int damage)
    {
        super(layer, image, posX, posY, rotation, damage);
        sound = new AudioClip("file:res/Sound/MachineGun.mp3");
    }

    public double getSpeed() {return this.speed;}
    public int getRateOfFire() {return this.rateOfFire;}

    @Override
    public AudioClip getAudio() {
        return sound;
    }
}
