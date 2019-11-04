package towerDefense;

public interface GameEntity {
    double getPosX();

    double getPosY();

    double getWidth();

    double getHeight();

    long getTick();

    boolean overlapped(double posX, double posY, double width, double height);
}
