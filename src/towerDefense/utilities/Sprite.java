package towerDefense.utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sprite
 * Actually it's ImageView but enhanced :)
 */
public class Sprite {

    private ImageView imageView;
    private Image image;
    private String name;
    private boolean midOrigin = true;

    // Now you can init an image anywhere without having to set it manually after init
    public Sprite(String name, Image image, double midX, double midY, boolean isMidOrigin)
    {
        this.name = name;
        this.image = image;
        this.midOrigin = isMidOrigin;
        imageView = new ImageView(image);
        if (midOrigin) {
            imageView.setX(midX - image.getWidth() / 2);
            imageView.setY(midY - image.getHeight() / 2);
        }
        else {
            imageView.setX(midX);
            imageView.setY(midY);
        }
    }
    // Not all needs a name so this constructor is made
    public Sprite(Image image, double midX, double midY, boolean isMidOrigin)
    {
        this.name = "";
        this.image = image;
        this.midOrigin = isMidOrigin;
        imageView = new ImageView(image);
        if (midOrigin) {
            imageView.setX(midX - image.getWidth() / 2);
            imageView.setY(midY - image.getHeight() / 2);
        }
        else {
            imageView.setX(midX);
            imageView.setY(midY);
        }
    }

    // Just why can't the original ImageView class have this handy function? （╯‵□′）╯︵┴─┴
    public void setPosition(double midX, double midY)
    {
        if (midOrigin) {
            imageView.setX(midX - image.getWidth() / 2);
            imageView.setY(midY - image.getHeight() / 2);
        }
        else {
            imageView.setX(midX);
            imageView.setY(midY);
        }
    }

    public void setRotation(double rotation)
    {
        imageView.setRotate(rotation);
    }

    public void setImageView(Image image)
    {
        imageView.setImage(image);
    }

    public double getPosX() {return this.imageView.getX();}
    public double getPosY() {return this.imageView.getY();}
    public void setVisible(boolean isVisible) {imageView.setVisible(isVisible);}
    public ImageView getImageView() {return this.imageView;}
    public String getName() {return this.name;}
}