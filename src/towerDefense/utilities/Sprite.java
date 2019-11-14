package towerDefense.utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite
 * Actually it's ImageView but enhanced :)
 */
public class Sprite {

    private ImageView imageView;
    private String name;

    // Now you can init an image anywhere without having to set it manually after init
    public Sprite(String name, Image image, double posX, double posY)
    {
        this.name = name;
        imageView = new ImageView(image);
        imageView.setX(posX);
        imageView.setY(posY);
    }

    // Just why can't the original ImageView class have this handy function? （╯‵□′）╯︵┴─┴
    public void setPosition(double posX, double posY)
    {
        imageView.setX(posX);
        imageView.setY(posY);
    }

    public double getPosX() {return this.imageView.getX();}
    public double getPosY() {return this.imageView.getY();}
    public ImageView getImageView() {return this.imageView;}
    public String getName() {return this.name;}
}