package towerDefense.entity;

public interface GameEntity {
    double getPosX();

    double getPosY();

    long getTick();

    boolean overlapped(double posX, double posY, double width, double height);
}
