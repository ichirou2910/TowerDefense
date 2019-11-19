package towerDefense.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Sprite
 */
public class Sprite {

    private Image image;
    private ImageView imageView;
    private Pane layer;
    private double posX;
    private double posY;  

    public Sprite (Pane layer, Image image, double x, double y)
    {
        this.image = image;
        this.layer = layer;
        this.posX = x;
        this.posY = y;

        this.imageView = new ImageView();
        this.imageView.relocate(posX, posY);

        addToLayer();
    }

    // Getters & Setters
    //#region
    public double getPosX()
    {
        return this.posX;
    }

    public double getPosY()
    {
        return this.posY;
    }

    public Image getImage()
    {
        return this.image;
    }

    public ImageView getImageView()
    {
        return this.imageView;
    }

    public Pane getLayer()
    {
        return this.layer;
    }
    //#endregion

    public void addToLayer() {

        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {

        this.layer.getChildren().remove(this.imageView);
    }

    public void update()
    {
        imageView.relocate(posX, posY);
    }
}