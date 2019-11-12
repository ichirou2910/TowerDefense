package towerDefense.entity;

public interface GameEntity {
    double getPosX();

    double getPosY();

    boolean overlapped(double posX, double posY, double width, double height);
}
