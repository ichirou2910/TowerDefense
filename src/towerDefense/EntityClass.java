package towerDefense;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class EntityClass implements towerDefense.entity.GameEntity {
    
    private Image image;
    private ImageView imageView;
    private Pane layer;

    private final long tick;
    private double posX;
    private double posY;
    private double rotation;
    private double width;
    private double height;

    protected EntityClass(Pane layer, Image image, long tick, double posX, double posY, double rotation, double width, double height) {
        
        this.layer = layer;
        this.image = image;
        this.tick = tick;
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
        this.width = width;
        this.height = height;

        imageView = new ImageView();
        this.imageView.relocate(posX, posY);
        this.imageView.setRotate(rotation);
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

    @Override
    public final boolean overlapped(double posX, double posY, double width, double height) {
        return posX < this.posX + this.width
                && posY < this.posY + this.height
                && posX + width > this.posX
                && posY + height > this.posY;
    }
    
}
