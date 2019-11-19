/** This class is the base class of every objects in the game
 *  It contains the sprite image, basic geometric info
 */

package towerDefense;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import towerDefense.entity.GameEntity;
import towerDefense.utilities.Sprite;

public abstract class EntityClass implements GameEntity {
    
    private Image image;
//    private ImageView imageView;
    private Sprite sprite;
    private Pane layer;

    private double posX;
    private double posY;
    private double midX;
    private double midY;
    private double rotation;

    private boolean destroyed = false;

    protected EntityClass(Pane layer, Image image, double midX, double midY, double rotation) {
        
        this.layer = layer;
        this.image = image;
        this.midX = midX;
        this.posX = midX - image.getWidth() / 2;
        this.midY = midY;
        this.posY = midY - image.getHeight() / 2;
        this.rotation = rotation;

        this.sprite = new Sprite(image, midX, midY, true);
        this.sprite.getImageView().setRotate(rotation);

        addToLayer();
    }
    // Getters & Setters
    //#region
    @Override
    public final double getPosX() {
        return posX;
    }

    protected final void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public final double getPosY() {
        return posY;
    }

    public void setMidX(double midX) { this.midX = midX; }

    public void setMidY(double midY) { this.midY = midY; }

    public double getMidX() {
        return midX;
    }
    
    public double getMidY() { 
        return midY;
    }

    protected final void setPosY(double posY) {
        this.posY = posY;
    }

    public double getRotation() {
        return this.rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public boolean getDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public Image getImage()
    {
        return this.image;
    }

    public ImageView getImageView()
    {
        return sprite.getImageView();
    }

    public Pane getLayer()
    {
        return this.layer;
    }

    public abstract double getSpeed();
    //#endregion

    public void addToLayer() {

        this.layer.getChildren().add(sprite.getImageView());
    }

    public void removeFromLayer() {

        this.layer.getChildren().remove(sprite.getImageView());
    }

    public void update()
    {
        sprite.setPosition(midX, midY);
        sprite.setRotation(rotation);
    }

    @Override
    public final boolean overlapped(double posX, double posY, double width, double height) {
        return posX < this.posX + this.image.getWidth()
                && posY < this.posY + this.image.getHeight()
                && posX + width > this.posX
                && posY + height > this.posY;
    }

}
