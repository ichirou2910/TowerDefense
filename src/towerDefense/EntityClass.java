package towerDefense;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class EntityClass extends Node implements towerDefense.entity.GameEntity {
    
    private Image image;
    private ImageView imageView;
    private Pane layer;

    private final long tick;
    private double posX;
    private double posY;
    private double midX;
    private double midY;
    private double rotation;
    private double width;
    private double height;
    private int moveSet = 1;

    protected EntityClass(Pane layer, Image image, long tick, double posX, double posY, double rotation, double width, double height) {
        
        this.layer = layer;
        this.image = image;
        this.tick = tick;
        this.posX = posX;
        this.midX = posX + image.getWidth() / 2;
        this.posY = posY;
        this.midY = posY + image.getHeight() / 2;
        this.rotation = rotation;
        this.width = width;
        this.height = height;

        this.imageView = new ImageView(image);
        this.imageView.relocate(posX, posY);
        this.imageView.setRotate(rotation);

        addToLayer();
    }
    // Getters & Setters
    //#region
    @Override
    public final long getTick() {
        return tick;
    }

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
    public double getMidX() { return this.midX; }
    public double getMidY() { return this.midY; }

    protected final void setPosY(double posY) {
        this.posY = posY;
    }

    public double getRotation() {
        return this.rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public final double getWidth() {
        return width;
    }

    protected final void setWidth(double width) {
        this.width = width;
    }

    @Override
    public final double getHeight() {
        return height;
    }

    protected final void setHeight(double height) {
        this.height = height;
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
        imageView.setRotate(rotation);
    }

    public void move()
    {
        //Change rotation
        if(posX == 5*46 - 23 && posY == 5*46 - 23) {
            rotation = 0;
            moveSet = 2;
        }
        if(posX == 16*46 - 23 && posY == 5*46 - 23) {
            rotation = 90;
            moveSet = 1;
        }
        if(posX == 16*46 - 23 && posY == 11*46 - 23) {
            rotation = 180;
            moveSet = 3;
        }
        if(posX == 2*46 - 23 && posY == 11*46 - 23) {
            rotation = 90;
            moveSet = 1;
        }
        if(posX == 2*46 - 23 && posY == 15*46 - 23) {
            rotation = 0;
            moveSet = 2;
        }
        if(posX == 18*46 - 23 && posY == 15*46 - 23) {
            rotation = 90;
            moveSet = 1;
        }
        if(moveSet == 1) {
            posY += Config.NORMAL_SPEED;
        }
        if(moveSet == 2) {
            posX += Config.NORMAL_SPEED;
        }
        if(moveSet == 3) {
            posX -= Config.NORMAL_SPEED;
        }
        midX = posX + image.getWidth() / 2;
        midY = posY + image.getHeight() / 2;
    }

    @Override
    public final boolean overlapped(double posX, double posY, double width, double height) {
        return posX < this.posX + this.width
                && posY < this.posY + this.height
                && posX + width > this.posX
                && posY + height > this.posY;
    }

    @Override
    public Node getStyleableNode() {
        return null;
    }
}
