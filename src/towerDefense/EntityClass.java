package towerDefense;

public abstract class EntityClass implements towerDefense.GameEntity {
    private final long tick;
    private double posX;
    private double posY;
    private double width;
    private double height;

    protected EntityClass(long tick, double posX, double posY, double width, double height) {
        this.tick = tick;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

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

    @Override
    public final boolean overlapped(double posX, double posY, double width, double height) {
        return posX < this.posX + this.width
                && posY < this.posY + this.height
                && posX + width > this.posX
                && posY + height > this.posY;
    }
}
